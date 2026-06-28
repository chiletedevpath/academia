package pe.edu.utp.fersys.modelo.cliente;

import pe.edu.utp.fersys.modelo.persona.Persona;

// Herencia POO: Cliente especializa la informacion comun de Persona.
public class Cliente extends Persona {
    private String codigoCliente;
    private TipoCliente tipoCliente;

    // Crea un cliente con identificacion interna y clasificacion comercial.
    public Cliente(int idPersona, String dni, String nombres, String apellidos,
                   String telefono, String correo, String direccion,
                   String codigoCliente, TipoCliente tipoCliente) {
        super(idPersona, dni, nombres, apellidos, telefono, correo, direccion);
        this.codigoCliente = codigoCliente;
        this.tipoCliente = tipoCliente;
    }

    // Muestra datos clave del cliente para validacion por consola.
    @Override
    public void mostrarInformacionBasica() {
        System.out.println("Cliente: " + obtenerNombreCompleto());
        System.out.println("Codigo: " + codigoCliente);
        System.out.println("Tipo: " + tipoCliente);
    }
}
