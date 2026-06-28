package pe.edu.utp.fersys.modelo.usuario;

import pe.edu.utp.fersys.modelo.persona.Persona;

// Herencia POO: Usuario especializa Persona con credenciales y rol.
public class Usuario extends Persona {
    private String username;
    private String password;
    private RolUsuario rolUsuario;

    // Crea un usuario con credenciales y rol para controlar acceso futuro.
    public Usuario(int idPersona, String dni, String nombres, String apellidos,
                   String telefono, String correo, String direccion,
                   String username, String password, RolUsuario rolUsuario) {
        super(idPersona, dni, nombres, apellidos, telefono, correo, direccion);
        this.username = username;
        this.password = password;
        this.rolUsuario = rolUsuario;
    }

    // Muestra datos clave del usuario sin exponer su contrasena.
    @Override
    public void mostrarInformacionBasica() {
        System.out.println("Usuario: " + obtenerNombreCompleto());
        System.out.println("Username: " + username);
        System.out.println("Rol: " + rolUsuario);
    }
}
