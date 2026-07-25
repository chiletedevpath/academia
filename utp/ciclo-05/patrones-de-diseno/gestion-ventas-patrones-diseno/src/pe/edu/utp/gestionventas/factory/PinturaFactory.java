package pe.edu.utp.gestionventas.factory;

import pe.edu.utp.gestionventas.modelo.producto.Categoria;
import pe.edu.utp.gestionventas.modelo.producto.Producto;

// Fabrica concreta: crea productos de la categoria pinturas.
public class PinturaFactory extends ProductoFactory {

    // Factory Method: devuelve el producto concreto definido por esta fabrica.
    @Override
    public Producto crearProducto() {
        return construirProducto(
                "PROD002", "Pintura blanca 1 galon",
                "Pintura latex para interiores", 38.00,
                5, 1, Categoria.PINTURAS
        );
    }
}
