package pe.edu.utp.gestionventas.factory;

import pe.edu.utp.gestionventas.modelo.producto.Categoria;
import pe.edu.utp.gestionventas.modelo.producto.Producto;

// Factory Method: define el metodo fabrica que implementan las fabricas concretas.
public abstract class ProductoFactory {

    // Metodo fabrica que cada subclase redefine segun la categoria del producto.
    public abstract Producto crearProducto();

    // Centraliza la construccion comun para evitar duplicar validaciones.
    protected Producto construirProducto(
            String idProducto,
            String nombre,
            String descripcion,
            double precio,
            int stock,
            int stockMinimo,
            Categoria categoria
    ) {
        return new Producto(
                idProducto, nombre, descripcion, precio, stock, stockMinimo, categoria
        );
    }
}
