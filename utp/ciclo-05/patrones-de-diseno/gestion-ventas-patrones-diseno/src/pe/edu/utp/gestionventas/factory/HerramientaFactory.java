package pe.edu.utp.gestionventas.factory;

import pe.edu.utp.gestionventas.modelo.producto.Categoria;
import pe.edu.utp.gestionventas.modelo.producto.Producto;

// Fabrica concreta: crea productos de la categoria herramientas.
public class HerramientaFactory extends ProductoFactory {

    // Factory Method: devuelve el producto concreto definido por esta fabrica.
    @Override
    public Producto crearProducto() {
        return construirProducto(
                "PROD001", "Martillo de acero",
                "Herramienta manual para trabajos de construccion", 25.00,
                10, 2, Categoria.HERRAMIENTAS
        );
    }
}
