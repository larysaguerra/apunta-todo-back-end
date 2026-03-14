package dominio;

public class CategoriaProducto {

    private int id;
    private String nombre;
    private String descripcion;

    // Constructores

    public CategoriaProducto() {
    }

    public CategoriaProducto(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters and setters

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

    public CategoriaProducto crearCategoria(int id, String nombre, String descripcion){
        CategoriaProducto categoria = new CategoriaProducto();
        categoria.setId(id);
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
        return categoria;
    }

    public void mostrarCategoria() {
        System.out.println("Categoria: " + nombre);
        System.out.println("Descripcion: " + descripcion);
    }
}