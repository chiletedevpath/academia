package pe.edu.utp.gestionventas.factory;

import pe.edu.utp.gestionventas.modelo.producto.Categoria;
import pe.edu.utp.gestionventas.modelo.producto.Producto;

// Fabrica concreta: crea productos de la categoria construccion.
public class ConstruccionFactory extends ProductoFactory {

    // Factory Method: devuelve el producto concreto definido por esta fabrica.
    @Override
    public Producto crearProducto() {
        return construirProducto(
                "PROD004", "Barreta", "50 kg", 123.99,
                4, 1, Categoria.CONSTRUCCION
        );
    }
}
