package pe.edu.utp.gestionventas.modelo.producto;

// GRASP Experto: Producto controla stock porque posee esos datos.
public class Producto {
    private String idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private int stockMinimo;
    private Categoria categoria;

    // Crea un producto validando datos minimos de inventario.
    public Producto(
            String idProducto,
            String nombre,
            String descripcion,
            double precio,
            int stock,
            int stockMinimo,
            Categoria categoria
    ) {
        if (idProducto == null || idProducto.isBlank()) {
            throw new IllegalArgumentException("El id del producto es obligatorio.");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }

        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }

        if (stock < 0 || stockMinimo < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }

        if (categoria == null) {
            throw new IllegalArgumentException("La categoria es obligatoria.");
        }

        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
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

    // Revierte una salida de inventario cuando una venta es anulada.
    public void aumentarStock(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a reponer debe ser mayor a cero.");
        }

        this.stock += cantidad;
    }

    // Soporta Observer indicando si corresponde emitir alerta de reposicion.
    public boolean estaBajoStock() {
        return this.stock <= this.stockMinimo;
    }
}
