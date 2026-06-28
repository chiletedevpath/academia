/********************************************************************************************
    ARCHIVO: 00-create-database.sql
********************************************************************************************/

/********************************************************************************************
    SISTEMA DE BASE DE DATOS: FERRETERÍA SOTO

    AUTOR: ADRIAN PISCO SOTO
********************************************************************************************/


/********************************************************************************************
    0. CREACIÓN DE LA BASE DE DATOS
********************************************************************************************/
IF DB_ID('ferreteriaSotoBD') IS NULL
BEGIN
    CREATE DATABASE ferreteriaSotoBD;
END;
GO

USE ferreteriaSotoBD;
GO

/********************************************************************************************
    ARCHIVO: 01-usuarios.sql
********************************************************************************************/

/********************************************************************************************
    1. MODULO DE USUARIOS Y AUDITORIA
    INCLUYE:
        • TABLAS DE CATALOGO PARA ROLES Y ESTADOS
        • TABLA PRINCIPAL USUARIOS
        • VALIDACION DE CORREO
        • INDICES PARA OPTIMIZAR LOGIN
        • TABLA DE AUDITORIA DE USUARIOS
        • TRIGGER CORPORATIVO DE AUDITORIA
        • PROCEDIMIENTO ALMACENADO PARA CREAR USUARIOS
        • CARGA INICIAL DE CUENTAS
********************************************************************************************/


/********************************************************************************************
    1.1 TABLAS DE CATALOGO (ROLES Y ESTADOS)
********************************************************************************************/

IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name='Roles')
BEGIN
    CREATE TABLE dbo.Roles (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        Nombre NVARCHAR(50) NOT NULL CONSTRAINT UQ_Roles_Nombre UNIQUE
    );

    INSERT INTO dbo.Roles (Nombre)
    VALUES ('Administrador'), ('Usuario');
END
GO


IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name='EstadosUsuario')
BEGIN
    CREATE TABLE dbo.EstadosUsuario (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        Nombre NVARCHAR(20) NOT NULL CONSTRAINT UQ_EstadosUsuario_Nombre UNIQUE
    );

    INSERT INTO dbo.EstadosUsuario (Nombre)
    VALUES ('Activo'), ('Inactivo');
END
GO



/********************************************************************************************
    1.2 TABLA PRINCIPAL: USUARIOS
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name='Usuarios')
BEGIN
    CREATE TABLE dbo.Usuarios (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        Usuario NVARCHAR(50) NOT NULL CONSTRAINT UQ_Usuarios_Usuario UNIQUE,
        ContrasenaHash BINARY(32) NOT NULL,
        RolID INT NOT NULL CONSTRAINT FK_Usuarios_RolID REFERENCES dbo.Roles(ID),
        Correo NVARCHAR(150) NOT NULL CONSTRAINT UQ_Usuarios_Correo UNIQUE,
        NombreReal NVARCHAR(150) NOT NULL DEFAULT 'SIN NOMBRE',
        EstadoID INT NOT NULL CONSTRAINT FK_Usuarios_EstadoID REFERENCES dbo.EstadosUsuario(ID),
        FechaCreacion DATETIMEOFFSET NOT NULL DEFAULT(SYSDATETIMEOFFSET()),
        FechaActualizacion DATETIMEOFFSET NULL
    );
END
GO



/********************************************************************************************
    1.3 VALIDACION DE CORREO
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.check_constraints WHERE name='CK_Usuarios_Correo_Valido')
BEGIN
    ALTER TABLE dbo.Usuarios
    ADD CONSTRAINT CK_Usuarios_Correo_Valido
    CHECK (Correo LIKE '%_@_%._%');
END
GO



/********************************************************************************************
    1.4 INDICES
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name='IX_Usuarios_Usuario')
    CREATE INDEX IX_Usuarios_Usuario ON dbo.Usuarios(Usuario);

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name='IX_Usuarios_EstadoID')
    CREATE INDEX IX_Usuarios_EstadoID ON dbo.Usuarios(EstadoID);
GO



/********************************************************************************************
    1.5 AUDITORIA DE USUARIOS
********************************************************************************************/

IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name='AuditoriaUsuarios')
BEGIN
    CREATE TABLE dbo.AuditoriaUsuarios (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        UsuarioID INT NOT NULL,
        UsuarioAdminID INT NULL,
        Accion NVARCHAR(200) NOT NULL,
        Fecha DATETIMEOFFSET NOT NULL DEFAULT(SYSDATETIMEOFFSET()),
        IP NVARCHAR(50) NULL,
        FOREIGN KEY (UsuarioID) REFERENCES dbo.Usuarios(ID),
        FOREIGN KEY (UsuarioAdminID) REFERENCES dbo.Usuarios(ID)
    );
END
GO



/********************************************************************************************
    1.6 TRIGGER DE AUDITORIA — VERSION CORREGIDA
********************************************************************************************/

CREATE OR ALTER TRIGGER TR_Usuarios_Acciones
ON dbo.Usuarios
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @AdminID INT = TRY_CAST(SESSION_CONTEXT(N'AdminID') AS INT);
    DECLARE @IP NVARCHAR(50) = CONVERT(NVARCHAR(50), SESSION_CONTEXT(N'IP'));

    -----------------------------------------------------------------
    -- EVITAR ERRORES SI EL USUARIO YA NO EXISTE POR OPERACIONES MANUALES
    -----------------------------------------------------------------
    IF EXISTS (SELECT 1 FROM deleted) AND NOT EXISTS (SELECT 1 FROM dbo.Usuarios WHERE ID IN (SELECT ID FROM deleted))
    BEGIN
        RETURN;
    END

    -- CREACION
    IF EXISTS (SELECT 1 FROM inserted) AND NOT EXISTS (SELECT 1 FROM deleted)
    BEGIN
        INSERT INTO dbo.AuditoriaUsuarios (UsuarioID, UsuarioAdminID, Accion, IP)
        SELECT ID, @AdminID, 'USUARIO CREADO', @IP
        FROM inserted;
    END

    -- ELIMINACION
    IF EXISTS (SELECT 1 FROM deleted) AND NOT EXISTS (SELECT 1 FROM inserted)
    BEGIN
        INSERT INTO dbo.AuditoriaUsuarios (UsuarioID, UsuarioAdminID, Accion, IP)
        SELECT ID, @AdminID, 'USUARIO ELIMINADO', @IP
        FROM deleted;
    END

    -- MODIFICACION
    IF EXISTS (SELECT 1 FROM inserted) AND EXISTS (SELECT 1 FROM deleted)
    BEGIN
        INSERT INTO dbo.AuditoriaUsuarios (UsuarioID, UsuarioAdminID, Accion, IP)
        SELECT ID, @AdminID, 'USUARIO MODIFICADO', @IP
        FROM inserted;

        UPDATE dbo.Usuarios
        SET FechaActualizacion = SYSDATETIMEOFFSET()
        WHERE ID IN (SELECT ID FROM inserted);
    END
END
GO



/********************************************************************************************
    1.7 PROCEDIMIENTO ALMACENADO PARA CREAR USUARIOS
********************************************************************************************/
CREATE OR ALTER PROCEDURE dbo.CrearUsuario
(
    @Usuario NVARCHAR(50),
    @Contrasena NVARCHAR(200),
    @Rol NVARCHAR(50),
    @Correo NVARCHAR(150),
    @NombreReal NVARCHAR(150)
)
AS
BEGIN
    DECLARE @RolID INT = (SELECT ID FROM dbo.Roles WHERE Nombre=@Rol);
    DECLARE @EstadoID INT = (SELECT ID FROM dbo.EstadosUsuario WHERE Nombre='Activo');

    IF @RolID IS NULL
    BEGIN
        RAISERROR('ROL INVALIDO', 16, 1);
        RETURN;
    END

    INSERT INTO dbo.Usuarios (Usuario, ContrasenaHash, RolID, Correo, NombreReal, EstadoID)
    VALUES (
        @Usuario,
        HASHBYTES('SHA2_256', @Contrasena),
        @RolID,
        @Correo,
        @NombreReal,
        @EstadoID
    );
END
GO

-- EJEMPLO DE USO DEL PROCEDIMIENTO
-- Usuario de demostracion. No usar estas credenciales en produccion.
EXEC dbo.CrearUsuario 
    @Usuario = 'usuario_demo',
    @Contrasena = 'ClaveDemoUsuario2026!',
    @Rol = 'Usuario',
    @Correo = 'usuario.demo@ferreteriasoto.test',
    @NombreReal = 'Usuario Demo';
    GO

-- VERIFICACION DEL REGISTRO CREADO
SELECT * FROM dbo.Usuarios;
GO

/********************************************************************************************
    1.8 USUARIOS INICIALES
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM dbo.Usuarios WHERE Usuario='Admin')
BEGIN
    EXEC dbo.CrearUsuario 
        @Usuario = 'Admin',
        @Contrasena = 'ClaveDemoAdmin2026!',
        @Rol = 'Administrador',
        @Correo = 'admin.demo@ferreteriasoto.test',
        @NombreReal = 'Administrador del Sistema';
END

IF NOT EXISTS (SELECT 1 FROM dbo.Usuarios WHERE Usuario='OperadorDemo')
BEGIN
    EXEC dbo.CrearUsuario 
        @Usuario = 'OperadorDemo',
        @Contrasena = 'ClaveDemoOperador2026!',
        @Rol = 'Usuario',
        @Correo = 'operador.demo@ferreteriasoto.test',
        @NombreReal = 'Operador Demo Soto';
END
GO

/********************************************************************************************
    1.9 CONSULTA GENERAL DE AUDITORIA
********************************************************************************************/
SELECT 
    au.ID,
    au.UsuarioID,
    u.Usuario AS UsuarioAfectado,
    u.NombreReal AS NombreAfectado,
    au.Accion,
    au.Fecha,
    au.IP,
    au.UsuarioAdminID,
    admin.Usuario AS UsuarioAdministrador,
    admin.NombreReal AS NombreAdministrador
FROM dbo.AuditoriaUsuarios au
LEFT JOIN dbo.Usuarios u     ON u.ID     = au.UsuarioID
LEFT JOIN dbo.Usuarios admin ON admin.ID = au.UsuarioAdminID
ORDER BY au.Fecha DESC;
GO


SELECT * FROM dbo.AuditoriaUsuarios;
GO

/********************************************************************************************
    ARCHIVO: 02-clientes.sql
********************************************************************************************/

/********************************************************************************************
    2. MODULO CLIENTES (PERSONAS Y EMPRESAS)
    OBJETIVO:
        - ADMINISTRAR CLIENTES PARA BOLETAS Y FACTURAS
        - USAR DNI COMO IDENTIFICADOR OBLIGATORIO
        - PERMITIR RUC SOLO PARA EMPRESAS
        - APLICAR VALIDACIONES DE FORMATO PARA DNI Y RUC
        - PROCEDIMIENTO CONTROLADO DE REGISTRO
********************************************************************************************/


/********************************************************************************************
    2.0 LIMPIEZA DE CONSTRAINTS DUPLICADOS
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Clientes' AND schema_id = SCHEMA_ID('dbo'))
BEGIN
    CREATE TABLE dbo.Clientes (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        Nombres NVARCHAR(150) NOT NULL,
        Apellidos NVARCHAR(150) NOT NULL,
        DNI NVARCHAR(15) NOT NULL,
        Direccion NVARCHAR(300) NULL,
        RUC NVARCHAR(20) NULL,
        RazonSocial NVARCHAR(200) NULL,
        FechaCreacion DATETIMEOFFSET NOT NULL DEFAULT(SYSDATETIMEOFFSET())
    );
END;
GO

DECLARE @sql NVARCHAR(MAX) = N'';

SELECT @sql = @sql + N'ALTER TABLE dbo.Clientes DROP CONSTRAINT ' + QUOTENAME(name) + N';'
FROM sys.objects
WHERE type = 'C' AND (
    name LIKE 'CK_Clientes_DNI_%' OR
    name LIKE 'CK_Clientes_RUC_%'
);

IF @sql <> N'' EXEC(@sql);

IF EXISTS (SELECT 1 FROM sys.objects WHERE name = 'UQ_Clientes_DNI')
    ALTER TABLE dbo.Clientes DROP CONSTRAINT UQ_Clientes_DNI;

IF EXISTS (SELECT 1 FROM sys.objects WHERE name = 'UQ_Clientes_RUC')
    ALTER TABLE dbo.Clientes DROP CONSTRAINT UQ_Clientes_RUC;
GO


/********************************************************************************************
    2.1 REGLA DE DNI (OBLIGATORIO + UNICO)
********************************************************************************************/
ALTER TABLE Clientes
ALTER COLUMN DNI NVARCHAR(15) NOT NULL;
GO

ALTER TABLE Clientes
ADD CONSTRAINT UQ_Clientes_DNI UNIQUE (DNI);
GO


/********************************************************************************************
    2.2 REGLA DE RUC (OPCIONAL + UNICO SI EXISTE)
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name='IX_Clientes_RUC_NoNull')
BEGIN
    CREATE UNIQUE NONCLUSTERED INDEX IX_Clientes_RUC_NoNull
    ON Clientes(RUC)
    WHERE RUC IS NOT NULL;
END;
GO


/********************************************************************************************
    2.3 VALIDACIONES DE FORMATO (DNI Y RUC)
********************************************************************************************/
ALTER TABLE Clientes
ADD CONSTRAINT CK_Clientes_DNI_8Digitos
CHECK (LEN(DNI) = 8 AND DNI NOT LIKE '%[^0-9]%');
GO

ALTER TABLE Clientes
ADD CONSTRAINT CK_Clientes_RUC_11Digitos
CHECK (RUC IS NULL OR (LEN(RUC) = 11 AND RUC NOT LIKE '%[^0-9]%'));
GO

ALTER TABLE Clientes
ADD CONSTRAINT CK_Clientes_RUC_Prefijo
CHECK (
    RUC IS NULL OR 
    LEFT(RUC, 2) IN ('10','20','15','17','16','21','22','23')
);
GO


/********************************************************************************************
    2.4 PROCEDIMIENTO ALMACENADO: CargarCliente
********************************************************************************************/
IF EXISTS (SELECT * FROM sys.objects WHERE name='CargarCliente' AND type='P')
    DROP PROCEDURE CargarCliente;
GO

CREATE PROCEDURE dbo.CargarCliente
(
    @Nombres      NVARCHAR(150),
    @Apellidos    NVARCHAR(150),
    @DNI          NVARCHAR(15),
    @Direccion    NVARCHAR(300)=NULL,
    @RUC          NVARCHAR(20)=NULL,
    @RazonSocial  NVARCHAR(200)=NULL
)
AS
BEGIN
    IF EXISTS(SELECT 1 FROM Clientes WHERE DNI = @DNI)
    BEGIN
        RAISERROR('DNI YA EXISTE', 16, 1);
        RETURN;
    END

    IF @RUC IS NOT NULL AND EXISTS(SELECT 1 FROM Clientes WHERE RUC = @RUC)
    BEGIN
        RAISERROR('RUC YA EXISTE', 16, 1);
        RETURN;
    END

    IF @RUC IS NOT NULL AND (@RazonSocial IS NULL OR @Direccion IS NULL)
    BEGIN
        RAISERROR('CLIENTE CON RUC DEBE TENER RAZON SOCIAL Y DIRECCION', 16, 1);
        RETURN;
    END

    INSERT INTO Clientes (Nombres,Apellidos,DNI,Direccion,RUC,RazonSocial)
    VALUES (@Nombres,@Apellidos,@DNI,@Direccion,@RUC,@RazonSocial);
END;
GO


/********************************************************************************************
    2.5 VERIFICACION FINAL DE LA ESTRUCTURA DEL MODULO
********************************************************************************************/
SELECT TOP 50 * FROM Clientes;
GO

SELECT name, type_desc
FROM sys.objects
WHERE name LIKE '%Clientes%';
GO

SELECT *
FROM sys.indexes
WHERE object_id = OBJECT_ID('Clientes');
GO


/********************************************************************************************
    ARCHIVO: 03-clasificacion-productos.sql
********************************************************************************************/

/********************************************************************************************
    3. MODULO DE CLASIFICACION DE PRODUCTOS
    OBJETIVO:
        • DEFINIR FAMILIAS, GRUPOS, MARCAS, UNIDADES Y LINEAS
        • EVITAR DUPLICADOS LOGICOS EN CATALOGOS
        • MEJORAR RENDIMIENTO DE CONSULTAS Y JOINS
        • PREPARAR LA BASE PARA CRECIMIENTO DEL INVENTARIO
********************************************************************************************/

USE ferreteriaSotoBD;
GO

/********************************************************************************************
    3.1 TABLAS PRINCIPALES DE CLASIFICACION (SI YA EXISTEN NO SE RECREAN)
********************************************************************************************/

-- TABLA FAMILIAS
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Familias')
BEGIN
    CREATE TABLE dbo.Familias (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        Nombre NVARCHAR(120) NOT NULL
            CONSTRAINT UQ_Familias_Nombre UNIQUE,
        Estado NVARCHAR(20) NOT NULL DEFAULT 'Activo'
            CONSTRAINT CK_Familias_Estado CHECK (Estado IN ('Activo','Inactivo'))
    );
END;
GO

-- TABLA GRUPOS
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Grupos')
BEGIN
    CREATE TABLE dbo.Grupos (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        FamiliaID INT NOT NULL,
        Nombre NVARCHAR(120) NOT NULL,
        Estado NVARCHAR(20) NOT NULL DEFAULT 'Activo'
            CONSTRAINT CK_Grupos_Estado CHECK (Estado IN ('Activo','Inactivo')),
        FOREIGN KEY (FamiliaID) REFERENCES dbo.Familias(ID)
    );
END;
GO

-- TABLA MARCAS
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Marcas')
BEGIN
    CREATE TABLE dbo.Marcas (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        Nombre NVARCHAR(120) NOT NULL
            CONSTRAINT UQ_Marcas_Nombre UNIQUE,
        Estado NVARCHAR(20) NOT NULL DEFAULT 'Activo'
            CONSTRAINT CK_Marcas_Estado CHECK (Estado IN ('Activo','Inactivo'))
    );
END;
GO

-- TABLA UNIDADES DE MEDIDA
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'UnidadesMedida')
BEGIN
    CREATE TABLE dbo.UnidadesMedida (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        Nombre NVARCHAR(80) NOT NULL,
        Abreviatura NVARCHAR(10) NOT NULL,
        Estado NVARCHAR(20) NOT NULL DEFAULT 'Activo'
            CONSTRAINT CK_UnidadesMedida_Estado CHECK (Estado IN ('Activo','Inactivo'))
    );
END;
GO

-- TABLA LINEAS
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Lineas')
BEGIN
    CREATE TABLE dbo.Lineas (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        FamiliaID INT NOT NULL,
        Nombre NVARCHAR(150) NOT NULL,
        Estado NVARCHAR(20) NOT NULL DEFAULT 'Activo'
            CONSTRAINT CK_Lineas_Estado CHECK (Estado IN ('Activo','Inactivo')),
        CONSTRAINT FK_Lineas_Familia FOREIGN KEY (FamiliaID) REFERENCES dbo.Familias(ID)
    );
END;
GO



/********************************************************************************************
    3.2 UNICIDAD LOGICA – SE EVITAN DUPLICADOS
********************************************************************************************/

-- UNICIDAD EN UNIDADES DE MEDIDA (NOMBRE)
IF NOT EXISTS (
    SELECT 1 FROM sys.objects 
    WHERE name = 'UQ_UnidadesMedida_Nombre' AND type = 'UQ'
)
BEGIN
    ALTER TABLE dbo.UnidadesMedida
    ADD CONSTRAINT UQ_UnidadesMedida_Nombre UNIQUE (Nombre);
END;
GO

-- UNICIDAD EN UNIDADES DE MEDIDA (ABREVIATURA)
IF NOT EXISTS (
    SELECT 1 FROM sys.objects 
    WHERE name = 'UQ_UnidadesMedida_Abreviatura' AND type = 'UQ'
)
BEGIN
    ALTER TABLE dbo.UnidadesMedida
    ADD CONSTRAINT UQ_UnidadesMedida_Abreviatura UNIQUE (Abreviatura);
END;
GO

-- GRUPOS ÚNICOS POR FAMILIA
IF NOT EXISTS (
    SELECT 1 FROM sys.objects 
    WHERE name = 'UQ_Grupos_Familia_Nombre' AND type = 'UQ'
)
BEGIN
    ALTER TABLE dbo.Grupos
    ADD CONSTRAINT UQ_Grupos_Familia_Nombre UNIQUE (FamiliaID, Nombre);
END;
GO

-- LINEAS ÚNICAS POR FAMILIA
IF NOT EXISTS (
    SELECT 1 FROM sys.objects 
    WHERE name = 'UQ_Lineas_Familia_Nombre' AND type = 'UQ'
)
BEGIN
    ALTER TABLE dbo.Lineas
    ADD CONSTRAINT UQ_Lineas_Familia_Nombre UNIQUE (FamiliaID, Nombre);
END;
GO



/********************************************************************************************
    3.3 INDICES PARA MEJORAR RENDIMIENTO EN JOINS
********************************************************************************************/

-- INDICE EN GRUPOS POR FAMILIA
IF NOT EXISTS (
    SELECT 1 FROM sys.indexes 
    WHERE name = 'IX_Grupos_FamiliaID'
)
BEGIN
    CREATE INDEX IX_Grupos_FamiliaID ON dbo.Grupos(FamiliaID);
END;
GO

-- INDICE EN LINEAS POR FAMILIA
IF NOT EXISTS (
    SELECT 1 FROM sys.indexes 
    WHERE name = 'IX_Lineas_FamiliaID'
)
BEGIN
    CREATE INDEX IX_Lineas_FamiliaID ON dbo.Lineas(FamiliaID);
END;
GO



/********************************************************************************************
    3.4 CARGA INICIAL DE UNIDADES DE MEDIDA (SE AGREGA SOLO SI AUN NO EXISTEN)
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM dbo.UnidadesMedida WHERE Nombre = 'Unidad')
BEGIN
    INSERT INTO dbo.UnidadesMedida (Nombre, Abreviatura, Estado)
    VALUES 
        ('Unidad','UND','Activo'),
        ('Galón','GLN','Activo'),
        ('Juego','JGO','Activo'),
        ('Kilogramo','KG','Activo'),
        ('Par','PAR','Activo');
END;
GO



/********************************************************************************************
    3.5 VERIFICACION FINAL DEL MODULO
********************************************************************************************/
SELECT 'FAMILIAS' AS Tabla, COUNT(*) AS Registros FROM dbo.Familias
UNION ALL SELECT 'GRUPOS', COUNT(*) FROM dbo.Grupos
UNION ALL SELECT 'MARCAS', COUNT(*) FROM dbo.Marcas
UNION ALL SELECT 'UNIDADESMEDIDA', COUNT(*) FROM dbo.UnidadesMedida
UNION ALL SELECT 'LINEAS', COUNT(*) FROM dbo.Lineas;
GO

/********************************************************************************************
    ARCHIVO: 04-productos.sql
********************************************************************************************/

/********************************************************************************************
    4. MODULO DE PRODUCTOS E INVENTARIO DE ENTRADA
    OBJETIVO:
        - CREAR EL CATALOGO PRINCIPAL DE PRODUCTOS.
        - RELACIONAR PRODUCTOS CON CATALOGOS DE CLASIFICACION.
        - REGISTRAR ENTRADAS DE INVENTARIO POR LOTES.
        - PREPARAR EL STOCK PARA EL CONSUMO PEPS EN VENTAS.
********************************************************************************************/
USE ferreteriaSotoBD;
GO

/********************************************************************************************
    4.1 TABLA PRINCIPAL DE PRODUCTOS
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Productos' AND schema_id = SCHEMA_ID('dbo'))
BEGIN
    CREATE TABLE dbo.Productos (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        Codigo NVARCHAR(30) NOT NULL CONSTRAINT UQ_Productos_Codigo UNIQUE,
        Nombre NVARCHAR(150) NOT NULL,
        FamiliaID INT NULL,
        GrupoID INT NULL,
        MarcaID INT NULL,
        UnidadMedidaID INT NULL,
        LineaID INT NULL,
        PrecioCompra DECIMAL(10,2) NULL,
        PrecioVenta DECIMAL(10,2) NULL,
        StockMinimo INT NOT NULL DEFAULT 0,
        Estado NVARCHAR(20) NOT NULL DEFAULT 'Activo'
            CONSTRAINT CK_Productos_Estado CHECK (Estado IN ('Activo','Inactivo')),
        FechaCreacion DATETIMEOFFSET NOT NULL DEFAULT(SYSDATETIMEOFFSET()),
        FechaActualizacion DATETIMEOFFSET NULL,
        CONSTRAINT FK_Productos_Familia FOREIGN KEY (FamiliaID) REFERENCES dbo.Familias(ID),
        CONSTRAINT FK_Productos_Grupo FOREIGN KEY (GrupoID) REFERENCES dbo.Grupos(ID),
        CONSTRAINT FK_Productos_Marca FOREIGN KEY (MarcaID) REFERENCES dbo.Marcas(ID),
        CONSTRAINT FK_Productos_UnidadMedida FOREIGN KEY (UnidadMedidaID) REFERENCES dbo.UnidadesMedida(ID),
        CONSTRAINT FK_Productos_Linea FOREIGN KEY (LineaID) REFERENCES dbo.Lineas(ID)
    );
END;
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_Productos_Nombre')
BEGIN
    CREATE INDEX IX_Productos_Nombre ON dbo.Productos(Nombre);
END;
GO

IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_Productos_Estado')
BEGIN
    CREATE INDEX IX_Productos_Estado ON dbo.Productos(Estado);
END;
GO

/********************************************************************************************
    4.2 TABLA DE ENTRADAS DE INVENTARIO
    CADA REGISTRO REPRESENTA UNA COMPRA O INGRESO DE STOCK.
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'EntradasInventario' AND schema_id = SCHEMA_ID('dbo'))
BEGIN
    CREATE TABLE dbo.EntradasInventario (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        ProductoID INT NOT NULL,
        Cantidad INT NOT NULL,
        StockDisponible INT NOT NULL,
        PrecioCompra DECIMAL(10,2) NOT NULL,
        Moneda NVARCHAR(10) NOT NULL
            CONSTRAINT CK_EntradasInventario_Moneda CHECK (Moneda IN ('PEN','USD')),
        TipoCambio DECIMAL(10,4) NOT NULL,
        CostoUnitarioEnSoles AS (
            CASE
                WHEN Moneda = 'USD' THEN PrecioCompra * TipoCambio
                ELSE PrecioCompra
            END
        ),
        FechaCompra DATETIME NOT NULL DEFAULT(GETDATE()),
        CONSTRAINT FK_EntradasInventario_Producto
            FOREIGN KEY (ProductoID) REFERENCES dbo.Productos(ID)
    );
END;
GO

/********************************************************************************************
    4.3 PROCEDIMIENTO PARA REGISTRAR ENTRADA DE INVENTARIO
********************************************************************************************/
CREATE OR ALTER PROCEDURE dbo.RegistrarEntradaInventario
(
    @ProductoID INT,
    @Cantidad INT,
    @PrecioCompra DECIMAL(10,2),
    @Moneda NVARCHAR(10),
    @TipoCambio DECIMAL(10,4)
)
AS
BEGIN
    SET NOCOUNT ON;

    IF NOT EXISTS (SELECT 1 FROM dbo.Productos WHERE ID = @ProductoID)
    BEGIN
        RAISERROR('PRODUCTO NO ENCONTRADO', 16, 1);
        RETURN;
    END

    IF @Cantidad <= 0
    BEGIN
        RAISERROR('LA CANTIDAD DEBE SER MAYOR A CERO', 16, 1);
        RETURN;
    END

    IF @Moneda NOT IN ('PEN','USD')
    BEGIN
        RAISERROR('MONEDA INVALIDA. USE PEN O USD', 16, 1);
        RETURN;
    END

    IF @TipoCambio <= 0
    BEGIN
        RAISERROR('TIPO DE CAMBIO INVALIDO', 16, 1);
        RETURN;
    END

    INSERT INTO dbo.EntradasInventario
        (ProductoID, Cantidad, StockDisponible, PrecioCompra, Moneda, TipoCambio)
    VALUES
        (@ProductoID, @Cantidad, @Cantidad, @PrecioCompra, @Moneda, @TipoCambio);
END;
GO

/********************************************************************************************
    4.4 CONSULTA DE STOCK DISPONIBLE POR PRODUCTO
********************************************************************************************/
CREATE OR ALTER VIEW dbo.vwStockDisponibleProductos
AS
SELECT
    p.ID AS ProductoID,
    p.Codigo,
    p.Nombre AS Producto,
    ISNULL(SUM(ei.StockDisponible), 0) AS StockDisponible
FROM dbo.Productos p
LEFT JOIN dbo.EntradasInventario ei ON ei.ProductoID = p.ID
GROUP BY p.ID, p.Codigo, p.Nombre;
GO


/********************************************************************************************
    ARCHIVO: 05-ventas-detalle-auditoria.sql
********************************************************************************************/

﻿/********************************************************************************************
    5. MODULO DE VENTAS, DETALLE Y AUDITORIA
    OBJETIVO:
        • REGISTRAR VENTAS (BOLETA / FACTURA / NOTA DE VENTA)
        • ADMINISTRAR DETALLES POR PRODUCTO
        • SOPORTAR COSTO POR PEPS (FIFO) MEDIANTE PROCEDIMIENTOS
        • AUDITAR CUALQUIER CAMBIO EN EL REGISTRO DE VENTAS
********************************************************************************************/
USE ferreteriaSotoBD;
GO


/********************************************************************************************
    5.1 TABLA PRINCIPAL DE VENTAS
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Ventas')
BEGIN
CREATE TABLE dbo.Ventas (
    ID INT IDENTITY(1,1) PRIMARY KEY,

    ClienteID INT NULL,

    -- DATOS DEL CLIENTE GRABADOS EN LA VENTA
    Nombres        NVARCHAR(200) NULL,
    Apellidos      NVARCHAR(200) NULL,
    DNI            NVARCHAR(20)  NULL,
    Direccion      NVARCHAR(300) NULL,
    RUC            NVARCHAR(20)  NULL,
    RazonSocial    NVARCHAR(200) NULL,

    TipoComprobante NVARCHAR(30) NOT NULL
        CONSTRAINT CK_Ventas_Comprobante CHECK
        (TipoComprobante IN ('Boleta', 'Factura', 'Nota de Venta')),

    SubTotal DECIMAL(10,2) NOT NULL,
    IGV      DECIMAL(10,2) NOT NULL,
    Total    DECIMAL(10,2) NOT NULL,

    MetodoPago NVARCHAR(30) NOT NULL DEFAULT 'Efectivo'
        CONSTRAINT CK_Ventas_MetodoPago CHECK
        (MetodoPago IN ('Efectivo', 'Tarjeta', 'Yape/Plin')),

    Estado NVARCHAR(20) NOT NULL DEFAULT 'Vendido'
        CONSTRAINT CK_Ventas_Estado CHECK (Estado IN ('Vendido', 'Cancelado')),

    FechaCreacion      DATETIMEOFFSET NOT NULL DEFAULT(SYSDATETIMEOFFSET()),
    FechaActualizacion DATETIMEOFFSET NULL,

    FOREIGN KEY (ClienteID) REFERENCES dbo.Clientes(ID)
);
END;
GO



/********************************************************************************************
    5.2 TABLA DETALLEVENTAS
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'DetalleVentas')
BEGIN
CREATE TABLE dbo.DetalleVentas (
    ID INT IDENTITY(1,1) PRIMARY KEY,

    VentaID    INT NOT NULL,
    ProductoID INT NOT NULL,

    Cantidad INT NOT NULL,
    PrecioUnitario DECIMAL(10,2) NOT NULL,

    Importe AS (Cantidad * PrecioUnitario) PERSISTED,

    -- CAMPOS PEPS
    CostoUnitario DECIMAL(10,2) NULL,
    CostoTotal    DECIMAL(10,2) NULL,

    FOREIGN KEY (VentaID)    REFERENCES dbo.Ventas(ID),
    FOREIGN KEY (ProductoID) REFERENCES dbo.Productos(ID)
);
END;
GO



/********************************************************************************************
    5.3 TABLA AUDITORIA DE VENTAS
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'AuditoriaVentas')
BEGIN
CREATE TABLE dbo.AuditoriaVentas (
    ID INT IDENTITY(1,1) PRIMARY KEY,

    VentaID INT NOT NULL,
    Accion NVARCHAR(200) NOT NULL,
    Fecha DATETIMEOFFSET NOT NULL DEFAULT(SYSDATETIMEOFFSET()),
    IP NVARCHAR(50) NULL,
    UsuarioAdminID INT NULL,

    FOREIGN KEY (UsuarioAdminID) REFERENCES dbo.Usuarios(ID)
);
END;
GO

DECLARE @fkAuditoriaVenta NVARCHAR(200);

SELECT @fkAuditoriaVenta = fk.name
FROM sys.foreign_keys fk
WHERE fk.parent_object_id = OBJECT_ID('dbo.AuditoriaVentas')
  AND fk.referenced_object_id = OBJECT_ID('dbo.Ventas');

IF @fkAuditoriaVenta IS NOT NULL
BEGIN
    EXEC(N'ALTER TABLE dbo.AuditoriaVentas DROP CONSTRAINT ' + QUOTENAME(@fkAuditoriaVenta));
END;
GO



/********************************************************************************************
    5.4 TRIGGER DE AUDITORIA DE VENTAS
********************************************************************************************/
CREATE OR ALTER TRIGGER TR_Ventas_Acciones
ON dbo.Ventas
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @AdminID INT = TRY_CAST(SESSION_CONTEXT(N'AdminID') AS INT);
    DECLARE @IP NVARCHAR(50) = TRY_CAST(SESSION_CONTEXT(N'IP') AS NVARCHAR(50));

    -- VENTA CREADA
    IF EXISTS (SELECT 1 FROM inserted) AND NOT EXISTS (SELECT 1 FROM deleted)
    BEGIN
        INSERT INTO dbo.AuditoriaVentas (VentaID, Accion, IP, UsuarioAdminID)
        SELECT ID, 'VENTA CREADA', @IP, @AdminID
        FROM inserted;
    END

    -- VENTA ELIMINADA
    IF EXISTS (SELECT 1 FROM deleted) AND NOT EXISTS (SELECT 1 FROM inserted)
    BEGIN
        INSERT INTO dbo.AuditoriaVentas (VentaID, Accion, IP, UsuarioAdminID)
        SELECT ID, 'VENTA ELIMINADA', @IP, @AdminID
        FROM deleted;
    END

    -- VENTA MODIFICADA
    IF EXISTS (SELECT 1 FROM inserted) AND EXISTS (SELECT 1 FROM deleted)
    BEGIN
        INSERT INTO dbo.AuditoriaVentas (VentaID, Accion, IP, UsuarioAdminID)
        SELECT ID, 'VENTA MODIFICADA', @IP, @AdminID
        FROM inserted;

        UPDATE dbo.Ventas
        SET FechaActualizacion = SYSDATETIMEOFFSET()
        WHERE ID IN (SELECT ID FROM inserted);
    END
END;
GO



/********************************************************************************************
    5.5 VERIFICACION DE COLUMNA UsuarioAdminID (SE AGREGA SOLO SI NO EXISTE)
********************************************************************************************/
IF NOT EXISTS (
    SELECT 1 FROM sys.columns 
    WHERE Name = 'UsuarioAdminID' AND Object_ID = Object_ID('dbo.AuditoriaVentas')
)
BEGIN
    ALTER TABLE dbo.AuditoriaVentas
    ADD UsuarioAdminID INT NULL;

    ALTER TABLE dbo.AuditoriaVentas
    ADD CONSTRAINT FK_AuditoriaVentas_UsuarioAdminID
        FOREIGN KEY (UsuarioAdminID) REFERENCES dbo.Usuarios(ID);
END;
GO


/********************************************************************************************
    5.6 VISTA DE CONSULTA DETALLADA DE VENTAS
    OBJETIVO:
        • MOSTRAR INFORMACION COMPLETA DE UNA VENTA
        • INCLUYE CABECERA, CLIENTE Y DETALLE DE PRODUCTOS
        • PREPARADO PARA REPORTES Y CONSULTAS DESDE JAVA
********************************************************************************************/
CREATE OR ALTER VIEW vwVentasDetalladas AS
SELECT
    V.ID AS IDVenta,
    V.Nombres,
    V.Apellidos,
    V.DNI,
    V.TipoComprobante,
    V.SubTotal,
    V.IGV,
    V.Total,
    V.MetodoPago,
    V.Estado,
    V.FechaCreacion,

    P.Nombre AS Producto,
    DV.Cantidad,
    DV.PrecioUnitario,
    DV.Importe

FROM dbo.Ventas V
LEFT JOIN dbo.DetalleVentas DV ON DV.VentaID = V.ID
LEFT JOIN dbo.Productos P      ON P.ID       = DV.ProductoID;
GO

/********************************************************************************************
    5.7 TABLA DE SALIDAS DE INVENTARIO
    VINCULA CADA DETALLE DE VENTA CON UNO O VARIOS LOTES APLICANDO PEPS.
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'SalidasInventario' AND schema_id = SCHEMA_ID('dbo'))
BEGIN
    CREATE TABLE dbo.SalidasInventario (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        DetalleVentaID INT NOT NULL,
        LoteID INT NOT NULL,
        ProductoID INT NOT NULL,
        Cantidad INT NOT NULL,
        CostoUnitarioEnSoles DECIMAL(10,2) NOT NULL,
        CostoTotalEnSoles AS (Cantidad * CostoUnitarioEnSoles) PERSISTED,
        FechaSalida DATETIME NOT NULL DEFAULT(GETDATE()),
        CONSTRAINT FK_SalidasInventario_DetalleVenta
            FOREIGN KEY (DetalleVentaID) REFERENCES dbo.DetalleVentas(ID),
        CONSTRAINT FK_SalidasInventario_Lote
            FOREIGN KEY (LoteID) REFERENCES dbo.EntradasInventario(ID),
        CONSTRAINT FK_SalidasInventario_Producto
            FOREIGN KEY (ProductoID) REFERENCES dbo.Productos(ID)
    );
END;
GO

/********************************************************************************************
    5.8 PROCEDIMIENTO PARA APLICAR PEPS A UN DETALLE DE VENTA
********************************************************************************************/
CREATE OR ALTER PROCEDURE dbo.AplicarPEPS_DetalleVenta
(
    @DetalleVentaID INT
)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE
        @ProductoID INT,
        @CantidadPendiente INT,
        @CantidadConsumir INT,
        @LoteID INT,
        @CostoUnitarioLote DECIMAL(10,2);

    SELECT
        @ProductoID = ProductoID,
        @CantidadPendiente = Cantidad
    FROM dbo.DetalleVentas
    WHERE ID = @DetalleVentaID;

    IF @ProductoID IS NULL
    BEGIN
        RAISERROR('DETALLE DE VENTA NO ENCONTRADO', 16, 1);
        RETURN;
    END

    DELETE FROM dbo.SalidasInventario
    WHERE DetalleVentaID = @DetalleVentaID;

    DECLARE @StockTotalDisponible INT;

    SELECT @StockTotalDisponible = ISNULL(SUM(StockDisponible), 0)
    FROM dbo.EntradasInventario
    WHERE ProductoID = @ProductoID;

    IF @StockTotalDisponible < @CantidadPendiente
    BEGIN
        RAISERROR('STOCK INSUFICIENTE PARA APLICAR PEPS', 16, 1);
        RETURN;
    END

    WHILE @CantidadPendiente > 0
    BEGIN
        SELECT TOP 1
            @LoteID = ID,
            @CostoUnitarioLote = CostoUnitarioEnSoles,
            @CantidadConsumir =
                CASE
                    WHEN StockDisponible >= @CantidadPendiente THEN @CantidadPendiente
                    ELSE StockDisponible
                END
        FROM dbo.EntradasInventario
        WHERE ProductoID = @ProductoID
          AND StockDisponible > 0
        ORDER BY FechaCompra ASC, ID ASC;

        IF @LoteID IS NULL
        BEGIN
            RAISERROR('NO SE ENCONTRO LOTE DISPONIBLE PARA PEPS', 16, 1);
            RETURN;
        END

        INSERT INTO dbo.SalidasInventario
            (DetalleVentaID, LoteID, ProductoID, Cantidad, CostoUnitarioEnSoles)
        VALUES
            (@DetalleVentaID, @LoteID, @ProductoID, @CantidadConsumir, @CostoUnitarioLote);

        UPDATE dbo.EntradasInventario
        SET StockDisponible = StockDisponible - @CantidadConsumir
        WHERE ID = @LoteID;

        SET @CantidadPendiente = @CantidadPendiente - @CantidadConsumir;
    END

    DECLARE
        @CostoTotal DECIMAL(18,2),
        @CantidadTotal INT;

    SELECT
        @CantidadTotal = SUM(Cantidad),
        @CostoTotal = SUM(CostoTotalEnSoles)
    FROM dbo.SalidasInventario
    WHERE DetalleVentaID = @DetalleVentaID;

    UPDATE dbo.DetalleVentas
    SET
        CostoTotal = @CostoTotal,
        CostoUnitario = @CostoTotal / NULLIF(@CantidadTotal, 0)
    WHERE ID = @DetalleVentaID;
END;
GO

/********************************************************************************************
    5.9 VISTA RESUMEN DE COSTO POR DETALLE DE VENTA
********************************************************************************************/
CREATE OR ALTER VIEW dbo.vwDetalleVentasCostoPEPS
AS
SELECT
    dv.ID AS DetalleVentaID,
    dv.VentaID,
    dv.ProductoID,
    p.Nombre AS Producto,
    dv.Cantidad,
    dv.PrecioUnitario,
    dv.Importe AS ImporteVenta,
    dv.CostoUnitario,
    dv.CostoTotal,
    (dv.Importe - ISNULL(dv.CostoTotal, 0)) AS Margen
FROM dbo.DetalleVentas dv
INNER JOIN dbo.Productos p ON p.ID = dv.ProductoID;
GO


/********************************************************************************************
    ARCHIVO: 06-politicas-precios.sql
********************************************************************************************/

/********************************************************************************************
    6. MODULO DE POLITICAS DE PRECIOS (EMPRESARIAL)
    OBJETIVO:
        • PERMITIR QUE EL GERENTE MODIFIQUE MARGENES DESDE LA APLICACION JAVA
        • EVITAR MODIFICACION DIRECTA DE SQL
        • ELIMINAR LOGICA DE PRECIOS FIJA EN EL SISTEMA
        • APLICAR PRECIOS DE VENTA A PARTIR DE POLITICAS ACTIVAS
********************************************************************************************/
USE ferreteriaSotoBD;
GO


/********************************************************************************************
    6.1 TABLA PRINCIPAL DE POLITICAS DE PRECIOS
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'PoliticaPrecios')
BEGIN
    CREATE TABLE dbo.PoliticaPrecios (
        ID INT IDENTITY(1,1) PRIMARY KEY,

        RangoMin DECIMAL(10,2) NOT NULL,
        RangoMax DECIMAL(10,2) NULL,              -- NULL = SIN LIMITE SUPERIOR

        MargenPorcentaje DECIMAL(5,2) NOT NULL,   -- EJEMPLO: 60 = 60%

        Estado NVARCHAR(20) NOT NULL DEFAULT 'Activo'
            CONSTRAINT CK_PoliticaPrecios_Estado CHECK (Estado IN ('Activo','Inactivo')),

        FechaCreacion DATETIMEOFFSET NOT NULL DEFAULT SYSDATETIMEOFFSET(),
        FechaActualizacion DATETIMEOFFSET NULL
    );
END;
GO


/********************************************************************************************
    6.2 POLITICAS INICIALES (REEMPLAZAN LA LOGICA FIJA DEL SISTEMA)
********************************************************************************************/
IF NOT EXISTS (SELECT 1 FROM dbo.PoliticaPrecios)
BEGIN
    INSERT INTO dbo.PoliticaPrecios (RangoMin, RangoMax, MargenPorcentaje)
    VALUES
    (0.00, 10.00, 60.00),
    (10.00, 50.00, 40.00),
    (50.00, NULL, 25.00);
END;
GO


/********************************************************************************************
    6.3 VISTA PARA CONSULTA EN LA APLICACION JAVA
    LA APLICACION SE ENCARGA DEL ORDENAMIENTO
********************************************************************************************/
CREATE OR ALTER VIEW vwPoliticaPrecios AS
SELECT
    ID,
    RangoMin,
    RangoMax,
    MargenPorcentaje,
    Estado,
    FechaCreacion,
    FechaActualizacion
FROM dbo.PoliticaPrecios;
GO


/********************************************************************************************
    6.4 PROCEDIMIENTO PARA APLICAR POLITICAS A PRODUCTOS
    SE EJECUTA DESDE JAVA CUANDO SE DESEA ACTUALIZAR PRECIOS DE VENTA
********************************************************************************************/
CREATE OR ALTER PROCEDURE dbo.AplicarPoliticasDePrecio
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @RangoMin DECIMAL(10,2),
            @RangoMax DECIMAL(10,2),
            @Margen DECIMAL(5,2);

    DECLARE Politicas CURSOR FOR
        SELECT RangoMin, RangoMax, MargenPorcentaje
        FROM dbo.PoliticaPrecios
        WHERE Estado = 'Activo'
        ORDER BY RangoMin ASC;

    OPEN Politicas;
    FETCH NEXT FROM Politicas INTO @RangoMin, @RangoMax, @Margen;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        UPDATE dbo.Productos
        SET PrecioVenta = ROUND(PrecioCompra * (1 + @Margen / 100), 2),
            FechaActualizacion = SYSDATETIMEOFFSET()
        WHERE PrecioCompra IS NOT NULL
          AND (
                (@RangoMax IS NULL AND PrecioCompra >= @RangoMin)
                OR
                (PrecioCompra >= @RangoMin AND PrecioCompra < @RangoMax)
              );

        FETCH NEXT FROM Politicas INTO @RangoMin, @RangoMax, @Margen;
    END;

    CLOSE Politicas;
    DEALLOCATE Politicas;
END;
GO


/********************************************************************************************
    6.5 PROCEDIMIENTO PARA ACTUALIZAR POLITICAS DESDE LA APLICACION JAVA
********************************************************************************************/
CREATE OR ALTER PROCEDURE dbo.ActualizarPoliticaPrecio
(
    @ID INT,
    @RangoMin DECIMAL(10,2),
    @RangoMax DECIMAL(10,2),
    @MargenPorcentaje DECIMAL(5,2),
    @Estado NVARCHAR(20)
)
AS
BEGIN
    UPDATE dbo.PoliticaPrecios
    SET 
        RangoMin = @RangoMin,
        RangoMax = @RangoMax,
        MargenPorcentaje = @MargenPorcentaje,
        Estado = @Estado,
        FechaActualizacion = SYSDATETIMEOFFSET()
    WHERE ID = @ID;
END;
GO


/********************************************************************************************
    6.6 PROCEDIMIENTO PARA CREAR NUEVAS POLITICAS DESDE LA APLICACION JAVA
********************************************************************************************/
CREATE OR ALTER PROCEDURE dbo.CrearPoliticaPrecio
(
    @RangoMin DECIMAL(10,2),
    @RangoMax DECIMAL(10,2),
    @MargenPorcentaje DECIMAL(5,2)
)
AS
BEGIN
    INSERT INTO dbo.PoliticaPrecios (RangoMin, RangoMax, MargenPorcentaje)
    VALUES (@RangoMin, @RangoMax, @MargenPorcentaje);
END;
GO


/********************************************************************************************
    6.7 PROCEDIMIENTO PARA DESACTIVAR UNA POLITICA DE PRECIO
    NO SE ELIMINA, SOLO CAMBIA A ESTADO INACTIVO
********************************************************************************************/
CREATE OR ALTER PROCEDURE dbo.DesactivarPoliticaPrecio
(
    @ID INT
)
AS
BEGIN
    UPDATE dbo.PoliticaPrecios
    SET Estado = 'Inactivo',
        FechaActualizacion = SYSDATETIMEOFFSET()
    WHERE ID = @ID;
END;
GO

/********************************************************************************************
    ARCHIVO: 07-consultas-validacion.sql
********************************************************************************************/

/********************************************************************************************
    7. CONSULTAS DE VALIDACION DEL PROYECTO
    OBJETIVO:
        - REVISAR QUE LOS MODULOS PRINCIPALES EXISTAN.
        - VALIDAR REGISTROS BASE Y RELACIONES PRINCIPALES.
        - CONSULTAR INVENTARIO, VENTAS, AUDITORIA Y POLITICAS DE PRECIOS.
********************************************************************************************/

USE ferreteriaSotoBD;
GO

/********************************************************************************************
    7.1 RESUMEN DE TABLAS PRINCIPALES
********************************************************************************************/
SELECT 'Roles' AS Tabla, COUNT(*) AS Registros FROM dbo.Roles
UNION ALL SELECT 'EstadosUsuario', COUNT(*) FROM dbo.EstadosUsuario
UNION ALL SELECT 'Usuarios', COUNT(*) FROM dbo.Usuarios
UNION ALL SELECT 'Clientes', COUNT(*) FROM dbo.Clientes
UNION ALL SELECT 'Familias', COUNT(*) FROM dbo.Familias
UNION ALL SELECT 'Grupos', COUNT(*) FROM dbo.Grupos
UNION ALL SELECT 'Marcas', COUNT(*) FROM dbo.Marcas
UNION ALL SELECT 'UnidadesMedida', COUNT(*) FROM dbo.UnidadesMedida
UNION ALL SELECT 'Lineas', COUNT(*) FROM dbo.Lineas
UNION ALL SELECT 'Productos', COUNT(*) FROM dbo.Productos
UNION ALL SELECT 'EntradasInventario', COUNT(*) FROM dbo.EntradasInventario
UNION ALL SELECT 'SalidasInventario', COUNT(*) FROM dbo.SalidasInventario
UNION ALL SELECT 'Ventas', COUNT(*) FROM dbo.Ventas
UNION ALL SELECT 'DetalleVentas', COUNT(*) FROM dbo.DetalleVentas
UNION ALL SELECT 'PoliticaPrecios', COUNT(*) FROM dbo.PoliticaPrecios;
GO

/********************************************************************************************
    7.2 USUARIOS Y AUDITORIA
********************************************************************************************/
SELECT
    u.ID,
    u.Usuario,
    u.Correo,
    r.Nombre AS Rol,
    eu.Nombre AS Estado,
    u.FechaCreacion
FROM dbo.Usuarios u
INNER JOIN dbo.Roles r ON r.ID = u.RolID
INNER JOIN dbo.EstadosUsuario eu ON eu.ID = u.EstadoID
ORDER BY u.ID;
GO

SELECT TOP 20
    au.ID,
    u.Usuario AS UsuarioAfectado,
    au.Accion,
    au.Fecha
FROM dbo.AuditoriaUsuarios au
LEFT JOIN dbo.Usuarios u ON u.ID = au.UsuarioID
ORDER BY au.Fecha DESC;
GO

/********************************************************************************************
    7.3 CLIENTES
********************************************************************************************/
SELECT TOP 20
    ID,
    Nombres,
    Apellidos,
    DNI,
    RUC,
    RazonSocial,
    FechaCreacion
FROM dbo.Clientes
ORDER BY ID;
GO

/********************************************************************************************
    7.4 PRODUCTOS, CATALOGOS E INVENTARIO
********************************************************************************************/
SELECT TOP 20
    p.ID,
    p.Codigo,
    p.Nombre,
    f.Nombre AS Familia,
    g.Nombre AS Grupo,
    m.Nombre AS Marca,
    um.Abreviatura AS Unidad,
    l.Nombre AS Linea,
    p.PrecioCompra,
    p.PrecioVenta,
    p.StockMinimo,
    p.Estado
FROM dbo.Productos p
LEFT JOIN dbo.Familias f ON f.ID = p.FamiliaID
LEFT JOIN dbo.Grupos g ON g.ID = p.GrupoID
LEFT JOIN dbo.Marcas m ON m.ID = p.MarcaID
LEFT JOIN dbo.UnidadesMedida um ON um.ID = p.UnidadMedidaID
LEFT JOIN dbo.Lineas l ON l.ID = p.LineaID
ORDER BY p.ID;
GO

SELECT
    p.ID AS ProductoID,
    p.Nombre AS Producto,
    SUM(ei.StockDisponible) AS StockDisponible
FROM dbo.Productos p
LEFT JOIN dbo.EntradasInventario ei ON ei.ProductoID = p.ID
GROUP BY p.ID, p.Nombre
ORDER BY p.ID;
GO

/********************************************************************************************
    7.5 VENTAS Y COSTO PEPS
********************************************************************************************/
SELECT TOP 30 *
FROM dbo.vwVentasDetalladas
ORDER BY IDVenta DESC;
GO

SELECT TOP 30 *
FROM dbo.vwDetalleVentasCostoPEPS
ORDER BY DetalleVentaID DESC;
GO

SELECT TOP 20
    av.ID,
    av.VentaID,
    av.Accion,
    av.Fecha
FROM dbo.AuditoriaVentas av
ORDER BY av.Fecha DESC;
GO

/********************************************************************************************
    7.6 POLITICAS DE PRECIOS
********************************************************************************************/
SELECT *
FROM dbo.vwPoliticaPrecios
ORDER BY RangoMin;
GO
