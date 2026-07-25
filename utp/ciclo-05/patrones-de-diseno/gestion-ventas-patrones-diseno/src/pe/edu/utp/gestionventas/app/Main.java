package pe.edu.utp.gestionventas.app;

import pe.edu.utp.gestionventas.adapter.FacturacionExternaAdapter;
import pe.edu.utp.gestionventas.adapter.ServicioFacturacionExterna;
import pe.edu.utp.gestionventas.builder.ComprobanteVenta;
import pe.edu.utp.gestionventas.command.ConfirmarVentaCommand;
import pe.edu.utp.gestionventas.config.ConfiguracionSistema;
import pe.edu.utp.gestionventas.controller.VentaController;
import pe.edu.utp.gestionventas.factory.ConstruccionFactory;
import pe.edu.utp.gestionventas.factory.HerramientaFactory;
import pe.edu.utp.gestionventas.factory.PinturaFactory;
import pe.edu.utp.gestionventas.factory.ProductoFactory;
import pe.edu.utp.gestionventas.modelo.cliente.Cliente;
import pe.edu.utp.gestionventas.modelo.cliente.TipoCliente;
import pe.edu.utp.gestionventas.modelo.producto.Producto;
import pe.edu.utp.gestionventas.modelo.usuario.RolUsuario;
import pe.edu.utp.gestionventas.modelo.usuario.Usuario;
import pe.edu.utp.gestionventas.modelo.venta.Venta;
import pe.edu.utp.gestionventas.observer.AlertaStockObserver;

// Punto de entrada para validar por consola el modelo de gestion de ventas.
public class Main {
    // Crea objetos de prueba y muestra su informacion basica.
    public static void main(String[] args) {

        ConfiguracionSistema configuracionSistema = ConfiguracionSistema.obtenerInstancia();

        System.out.println("=== CONFIGURACION ===");
        System.out.println("Sistema: " + configuracionSistema.obtenerNombreSistema());
        System.out.println("Version: " + configuracionSistema.obtenerVersion());
        System.out.println("Moneda: " + configuracionSistema.obtenerMoneda());
        System.out.printf("IGV: %.1f%%%n", configuracionSistema.obtenerIgv() * 100);
        System.out.println();

        Cliente cliente = new Cliente(
                1, "00000001", "Cliente", "DevPath Demo", "900000001", "cliente.demo@chiletedevpath.test",
                "Direccion de ejemplo", "COD001", TipoCliente.NORMAL);

        Usuario usuario = new Usuario(
                1, "00000002", "Usuario", "DevPath Operador", "900000002", "usuario.demo@chiletedevpath.test",
                "Direccion administrativa de ejemplo", "admin.demo", "CLAVE_DEMO", RolUsuario.ADMINISTRADOR);

        System.out.println("=== CLIENTE ===");
        cliente.mostrarInformacionBasica();

        System.out.println();

        System.out.println("=== USUARIO ===");
        usuario.mostrarInformacionBasica();

        System.out.println();

        ProductoFactory construccionFactory = new ConstruccionFactory();
        ProductoFactory pinturaFactory = new PinturaFactory();
        ProductoFactory herramientaFactory = new HerramientaFactory();
        Producto barreta = construccionFactory.crearProducto();
        Producto pintura = pinturaFactory.crearProducto();
        Producto martillo = herramientaFactory.crearProducto();

        Venta venta = new Venta("VENTA001", cliente, usuario);
        AlertaStockObserver alertaStockObserver = new AlertaStockObserver();
        venta.agregarObservador(alertaStockObserver);

        ServicioFacturacionExterna servicioExterno = new ServicioFacturacionExterna();
        FacturacionExternaAdapter facturacionAdapter = new FacturacionExternaAdapter(servicioExterno);
        VentaController ventaController = new VentaController(venta, facturacionAdapter);

        if (ventaController.registrarProducto(barreta, 2)) {
            System.out.println("Producto de construccion agregado a la venta.");
        }

        if (ventaController.registrarProducto(pintura, 4)) {
            System.out.println("Pintura agregada a la venta.");
        } else {
            System.out.println("No se pudo agregar pintura: stock insuficiente.");
        }

        if (ventaController.registrarProducto(martillo, 3)) {
            System.out.println("Herramienta agregada a la venta.");
        }

        System.out.println();

        System.out.println("=== VENTA ===");
        System.out.println("Estado inicial: " + venta.obtenerEstado());
        ventaController.ejecutarComando(new ConfirmarVentaCommand(venta));
        System.out.println("Estado actual: " + venta.obtenerEstado());
        ventaController.mostrarResumen();

        System.out.println();
        System.out.println("=== COMPROBANTE BUILDER ===");
        ComprobanteVenta comprobanteVenta = ventaController.generarComprobante();
        comprobanteVenta.mostrar();

        System.out.println();
        System.out.println("=== ADAPTER FACTURACION ===");
        ventaController.exportarVenta();
    }
}
