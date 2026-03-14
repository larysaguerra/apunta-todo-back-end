package dominio;


public class ListaCompra {

//Atributos
    private int id;
    private String nombre;
    private  String fecha;
    private Usuario usuario;


//Constructores

    public ListaCompra() {

    }
    public ListaCompra(int id, String nombre, String fecha, Usuario usuario ){
        this.id= id;
        this.nombre= nombre;
        this.fecha= fecha;
        this.usuario= usuario;
    }

    //Guetters Setters


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    // metodos
    public void mostrarListaCompra(){
        System.out.println("ID: "+id);
        System.out.println("NOMBRE: "+nombre);
        System.out.println("FECHA: "+fecha);
        System.out.println("USUARIO: "+usuario);

    }

}