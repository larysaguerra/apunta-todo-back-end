package application.domain;


public class ListaCompra {

    //Atributos
    private int id;
    private String nombre;
    private String fecha;
    private int usuarioId;

    //Constructores
    public ListaCompra() {

    }

    public ListaCompra(int id, String nombre, String fecha, int usuarioId) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.usuarioId = usuarioId;
    }

    //Getters y Setters
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuario(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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