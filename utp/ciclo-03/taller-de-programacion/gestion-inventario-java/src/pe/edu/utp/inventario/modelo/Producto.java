package pe.edu.utp.inventario.modelo;

import pe.edu.utp.inventario.excepciones.DatoInvalidoException;

public class Producto {

    // Los atributos privados protegen el estado interno del producto.
    private String codigo;
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;

    public Producto(String codigo, String nombre, String categoria, double precio, int stock)
            throws DatoInvalidoException {
        // Los setters reutilizan las mismas validaciones del resto del sistema.
        setCodigo(codigo);
        setNombre(nombre);
        setCategoria(categoria);
        setPrecio(precio);
        setStock(stock);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) throws DatoInvalidoException {
        this.codigo = validarTexto(codigo, "El código");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws DatoInvalidoException {
        this.nombre = validarTexto(nombre, "El nombre");
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) throws DatoInvalidoException {
        this.categoria = validarTexto(categoria, "La categoría");
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) throws DatoInvalidoException {
        if (precio <= 0) {
            throw new DatoInvalidoException("El precio debe ser mayor que cero.");
        }
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) throws DatoInvalidoException {
        if (stock < 0) {
            throw new DatoInvalidoException("El stock no puede ser negativo.");
        }
        this.stock = stock;
    }

    public double vender(int cantidad) throws DatoInvalidoException {
        if (cantidad <= 0) {
            throw new DatoInvalidoException("La cantidad debe ser mayor que cero.");
        }
        if (cantidad > stock) {
            throw new DatoInvalidoException("No existe stock suficiente.");
        }

        // La venta modifica el stock solo después de validar la cantidad.
        stock -= cantidad;
        return precio * cantidad;
    }

    private String validarTexto(String valor, String campo) throws DatoInvalidoException {
        // isBlank también detecta cadenas formadas únicamente por espacios.
        if (valor == null || valor.isBlank()) {
            throw new DatoInvalidoException(campo + " no puede estar vacío.");
        }
        return valor.trim();
    }

    @Override
    public String toString() {
        // Los anchos fijos mantienen alineadas las columnas del reporte.
        return String.format(
                "%-6s | %-24s | %-16s | S/ %8.2f | %4d",
                codigo, nombre, categoria, precio, stock);
    }
}
