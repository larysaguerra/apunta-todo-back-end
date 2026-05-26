package application.domain;

public class Usuario extends Persona {

    private String telefono;
    private Rol rol;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String correo, String contrasena, String telefono, Rol rol) {
        super(id, nombre, apellido, correo, contrasena);
        this.telefono = telefono;
        this.rol = rol;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return super.toString() + " - Tel: " + telefono + " - Rol: " + (rol != null ? rol.getNombre() : "Sin rol");
    }

}