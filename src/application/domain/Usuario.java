package application.domain;

public class Usuario {

    //Atributos

    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String contrasena;
    private int rolId;

    // Constructores

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String correo, String telefono, String contrasena, int rolId) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.rolId = rolId;
    }

    // Getters y setters

    public int getRolId() {
        return rolId;
    }

    public void setRol(int rol) {
        this.rolId = rol;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}