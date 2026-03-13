package dominio;

public class Producto {
    // Atributos:
    private int id;
    private String nombre;
    private String unidadMedida;
    private CategoriaProducto categoria;

    //constructores
    public Producto(){
    }
    public Producto(int id,String nombre, String unidadMedida,CategoriaProducto categoria){
        this.id=id;
        this.nombre=nombre;
        this.unidadMedida=unidadMedida;
        this.categoria=categoria;
    }

    //Getters Setters


    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
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
    //metodos de la clase
    public Producto crearProducto(int id,String nombre,String unidadMedida,CategoriaProducto categoria){return new Producto(id,nombre,unidadMedida,categoria);}
    public void mostrarProducto(){
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("UnidadMedidad: " + unidadMedida);
        System.out.println("Categoria: " + categoria);


    }


}


