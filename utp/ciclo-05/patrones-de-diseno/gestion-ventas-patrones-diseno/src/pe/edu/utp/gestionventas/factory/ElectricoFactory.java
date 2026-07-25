package pe.edu.utp.gestionventas.factory;

import pe.edu.utp.gestionventas.modelo.producto.Categoria;
import pe.edu.utp.gestionventas.modelo.producto.Producto;

// Fabrica concreta: crea productos de la categoria electricidad.
public class ElectricoFactory extends ProductoFactory {

    // Factory Method: devuelve el producto concreto definido por esta fabrica.
    @Override
    public Producto crearProducto() {
        return construirProducto(
                "PROD003", "Multimetro", "Color rojo-negro", 256.00,
                2, 1, Categoria.ELECTRICIDAD
        );
    }
}
