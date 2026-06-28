package pe.edu.utp.fersys.factory;

import pe.edu.utp.fersys.modelo.producto.Categoria;
import pe.edu.utp.fersys.modelo.producto.Producto;

// Patron Factory: centraliza la creacion de productos y desacopla al cliente.
public class ProductoFactory {

    // Encapsula datos iniciales de inventario para productos de herramientas.
    public Producto crearProductoHerramienta() {
        return new Producto(
                "PROD001", "Martillo de acero",
                "Herramienta manual para trabajos de construccion", 25.00,
                10, 2, Categoria.HERRAMIENTAS
        );
    }

    // Define una pintura con stock controlado para validar reglas de reposicion.
    public Producto crearProductoPintura() {
        return new Producto(
                "PROD002", "Pintura blanca 1 galon",
                "Pintura latex para interiores", 38.00, 5, 1, Categoria.PINTURAS
        );

    }

    // Construye un producto electrico sin exponer el constructor al Main.
    public Producto crearProductoElectrico() {
        return new Producto(
                "PROD003", "Multimetro", "Color rojo-negro", 256.00, 2, 1,
                Categoria.ELECTRICIDAD
        );
    }

    // Construye un producto de construccion con precio y stock inicial definidos.
    public Producto crearProductoConstruccion() {
        return new Producto(
                "PROD004", "Barreta", "50 kg", 123.99, 4, 1,
                Categoria.CONSTRUCCION
        );

    }
}
