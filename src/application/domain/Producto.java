package application.domain;

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

}