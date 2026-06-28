/********************************************************************************************
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
