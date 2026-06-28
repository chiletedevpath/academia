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
