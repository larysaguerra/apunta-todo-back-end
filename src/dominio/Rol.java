package dominio;

public class Rol {

    // Atributos
    private int id;
    private String nombre;
    private String descripcion;

    // Constructores
    public Rol() {
    }

    public Rol(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Metodos de la clase
    public Rol crearRol(int id, String nombre, String descripcion) {
        return new Rol(id, nombre, descripcion);
    }

    public void mostrarRol() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Descripción: " + descripcion);
    }

}