package dominio;

public class Usuario {

    //Atributos
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String contraseña;
    private Rol rol;

    //constructores
public Usuario(){
}
public Usuario(int id,String nombre, String apellido,String correo,String telefono,String contraseña, Rol rol ){
    this.id = id;
    this.nombre = nombre;
    this.correo = correo;
    this.telefono = telefono;
    this.contraseña = contraseña;
    this.rol = rol;
}

//getters setters


    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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

    public void mostrarUsuario(){
        System.out.println("ID: "+ id);
        System.out.println("NOMBRE: "+ nombre);
        System.out.println("APELLIDO: "+ apellido);
        System.out.println("CORREO: "+ correo);
        System.out.println("TELEFONO: "+ telefono);
        System.out.println("CONTRASEÑA: "+ contraseña);
        System.out.println("ROL: "+ rol);
    }
}
