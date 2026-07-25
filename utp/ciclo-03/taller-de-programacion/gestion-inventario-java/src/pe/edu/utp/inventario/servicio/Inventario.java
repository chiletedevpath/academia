package pe.edu.utp.inventario.servicio;

import pe.edu.utp.inventario.excepciones.DatoInvalidoException;
import pe.edu.utp.inventario.modelo.Producto;

public class Inventario {

    private static final int CAPACIDAD = 20;
    // El arreglo aplica el almacenamiento de tamaño fijo estudiado en el curso.
    private final Producto[] productos = new Producto[CAPACIDAD];
    // cantidad indica cuántas posiciones del arreglo están ocupadas.
    private int cantidad;

    public Inventario() {
        cargarDatosIniciales();
    }

    public void agregar(Producto producto) throws DatoInvalidoException {
        if (cantidad == CAPACIDAD) {
            throw new DatoInvalidoException("El inventario alcanzó su capacidad máxima.");
        }
        if (buscarPorCodigo(producto.getCodigo()) != null) {
            throw new DatoInvalidoException("Ya existe un producto con ese código.");
        }

        // El siguiente producto se guarda en la primera posición libre.
        productos[cantidad] = producto;
        cantidad++;
    }

    public Producto buscarPorCodigo(String codigo) {
        for (int i = 0; i < cantidad; i++) {
            if (productos[i].getCodigo().equalsIgnoreCase(codigo.trim())) {
                return productos[i];
            }
        }
        return null;
    }

    public void eliminar(String codigo) throws DatoInvalidoException {
        int posicion = buscarPosicion(codigo);
        if (posicion == -1) {
            throw new DatoInvalidoException("No se encontró el producto.");
        }

        // Desplaza los elementos para evitar espacios vacíos entre productos.
        for (int i = posicion; i < cantidad - 1; i++) {
            productos[i] = productos[i + 1];
        }
        cantidad--;
        // Libera la última referencia que quedó repetida después del desplazamiento.
        productos[cantidad] = null;
    }

    public double vender(String codigo, int unidades) throws DatoInvalidoException {
        Producto producto = buscarPorCodigo(codigo);
        if (producto == null) {
            throw new DatoInvalidoException("No se encontró el producto.");
        }
        return producto.vender(unidades);
    }

    public String generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("CÓDIGO | PRODUCTO                 | CATEGORÍA        | PRECIO       | STOCK\n");
        reporte.append("-------+--------------------------+------------------+--------------+------\n");

        for (int i = 0; i < cantidad; i++) {
            reporte.append(productos[i]).append(System.lineSeparator());
        }

        reporte.append(System.lineSeparator())
                .append("Productos registrados: ")
                .append(cantidad)
                .append(" de ")
                .append(CAPACIDAD);
        return reporte.toString();
    }

    public String generarResumenPorCategoria() {
        String[] categorias = obtenerCategorias();
        int[][] resumen = calcularResumen(productos, cantidad, categorias);
        StringBuilder salida = new StringBuilder("RESUMEN POR CATEGORÍA\n");

        for (int i = 0; i < categorias.length; i++) {
            salida.append(String.format(
                    "%-16s | Productos: %2d | Unidades: %3d%n",
                    categorias[i], resumen[i][0], resumen[i][1]));
        }
        return salida.toString();
    }

    private int[][] calcularResumen(Producto[] datos, int total, String[] categorias) {
        // Cada fila representa una categoría: columna cero productos y columna uno unidades.
        int[][] resumen = new int[categorias.length][2];

        for (int i = 0; i < total; i++) {
            for (int j = 0; j < categorias.length; j++) {
                if (datos[i].getCategoria().equalsIgnoreCase(categorias[j])) {
                    resumen[j][0]++;
                    resumen[j][1] += datos[i].getStock();
                    break;
                }
            }
        }
        return resumen;
    }

    private String[] obtenerCategorias() {
        // El arreglo temporal puede llegar a necesitar una categoría por producto.
        String[] temporales = new String[cantidad];
        int totalCategorias = 0;

        for (int i = 0; i < cantidad; i++) {
            String categoria = productos[i].getCategoria();
            boolean existe = false;

            for (int j = 0; j < totalCategorias; j++) {
                if (temporales[j].equalsIgnoreCase(categoria)) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                temporales[totalCategorias] = categoria;
                totalCategorias++;
            }
        }

        // Copia solo las categorías encontradas y elimina posiciones sin usar.
        String[] categorias = new String[totalCategorias];
        for (int i = 0; i < totalCategorias; i++) {
            categorias[i] = temporales[i];
        }
        return categorias;
    }

    private int buscarPosicion(String codigo) {
        for (int i = 0; i < cantidad; i++) {
            if (productos[i].getCodigo().equalsIgnoreCase(codigo.trim())) {
                return i;
            }
        }
        return -1;
    }

    private void cargarDatosIniciales() {
        try {
            agregar(new Producto("P001", "Teclado mecánico", "Periféricos", 169.90, 8));
            agregar(new Producto("P002", "Mouse inalámbrico", "Periféricos", 79.90, 12));
            agregar(new Producto("P003", "Base para portátil", "Accesorios", 89.50, 6));
            agregar(new Producto("P004", "Cable USB-C", "Accesorios", 29.90, 20));
        } catch (DatoInvalidoException e) {
            // Un dato fijo inválido impide construir un inventario confiable.
            throw new IllegalStateException("Los datos iniciales son inválidos.", e);
        }
    }
}
