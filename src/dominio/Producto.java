package dominio;

public class Producto {

    // Atributos:
    private int id;
    private String nombre;
    private String unidadMedida;
    private int categoriaId;

    //constructores
    public Producto() {
    }

    public Producto(int id, String nombre, String unidadMedida, int categoriaId) {
        this.id = id;
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.categoriaId = categoriaId;
    }

    //Getters Setters
    public int getCategoriaId() {
        return this.categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
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

    public Producto crearProducto(int id, String nombre, String unidadMedida, int categoriaId) {
        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setUnidadMedida(unidadMedida);
        producto.setCategoriaId(categoriaId);
        return producto;
    }

    public void mostrarProducto() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("UnidadMedidad: " + unidadMedida);
        System.out.println("Categoria: " + categoriaId);
    }

}