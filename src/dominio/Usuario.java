package dominio;

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
        this.rolId = rolId;
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

    // Metodos de la clase

    public Usuario crearUsuario(int id, String nombre, String apellido, String correo, String telefono, String contrasena, int rolId) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreo(correo);
        usuario.setTelefono(telefono);
        usuario.setContrasena(contrasena);
        usuario.setRol(rolId);
        return usuario;
    }

    public void mostrarUsuario() {
        System.out.println("ID: " + id);
        System.out.println("NOMBRE: " + nombre);
        System.out.println("APELLIDO: " + apellido);
        System.out.println("CORREO: " + correo);
        System.out.println("TELEFONO: " + telefono);
        System.out.println("CONTRASEÑA: " + contrasena);
        System.out.println("ROL: " + rolId);
    }

}
