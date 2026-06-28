package pe.edu.utp.fersys.modelo.producto;

// GRASP Experto: Producto controla stock porque posee esos datos.
public class Producto {
    private String idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private int stockMinimo;
    private Categoria categoria;

    // Crea un producto con precio y stock no negativos.
    public Producto(
            String idProducto,
            String nombre,
            String descripcion,
            double precio,
            int stock,
            int stockMinimo,
            Categoria categoria
    ) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = Math.max(precio, 0);
        this.stock = Math.max(stock, 0);
        this.stockMinimo = Math.max(stockMinimo, 0);
        this.categoria = categoria;
    }

    // Devuelve el precio vigente usado al crear detalles de venta.
    public double obtenerPrecio() {
        return this.precio;
    }

    // Devuelve el nombre comercial del producto para reportes simples.
    public String obtenerNombre() {
        return this.nombre;
    }

    // Devuelve las unidades disponibles en inventario.
    public int obtenerStock() {
        return this.stock;
    }

    // Regla de negocio: valida disponibilidad antes de permitir una venta.
    public boolean tieneStockDisponible(int cantidad) {
        return cantidad > 0 && this.stock >= cantidad;
    }

    // Protege la invariante de inventario: el stock nunca debe ser negativo.
    public boolean reducirStock(int cantidad) {
        if (tieneStockDisponible(cantidad)) {
            this.stock -= cantidad;
            return true;
        }
        return false;
    }

    // Soporta Observer indicando si corresponde emitir alerta de reposicion.
    public boolean estaBajoStock() {
        return this.stock <= this.stockMinimo;
    }
}
