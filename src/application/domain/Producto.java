package application.domain;

public class Producto {

    private int id;
    private String nombre;
    private String unidadMedida;
    private CategoriaProducto categoria;  // objeto real, no solo el ID

    public Producto() {
    }

    public Producto(int id, String nombre, String unidadMedida, CategoriaProducto categoria) {
        this.id = id;
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.categoria = categoria;
    }

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

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        String nombreCategoria = (categoria != null) ? categoria.getNombre() : "Sin categoria";
        return id + " - " + nombre + " (" + unidadMedida + ") - Categoria: " + nombreCategoria;
    }
}
