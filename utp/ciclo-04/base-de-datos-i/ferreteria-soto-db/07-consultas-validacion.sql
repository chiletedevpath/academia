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
