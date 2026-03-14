package dominio;


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

    // metodos de la clase
    public ListaCompra crearListaDeCompra(int id, String nombre, String fecha, int usuarioId){
        ListaCompra listaCompra = new ListaCompra();
        listaCompra.setId(id);
        listaCompra.setNombre(nombre);
        listaCompra.setFecha(fecha);
        listaCompra.setUsuario(usuarioId);
        return listaCompra;
    }

    public void mostrarListaCompra() {
        System.out.println("ID: " + id);
        System.out.println("NOMBRE: " + nombre);
        System.out.println("FECHA: " + fecha);
        System.out.println("USUARIO: " + usuarioId);
    }

}