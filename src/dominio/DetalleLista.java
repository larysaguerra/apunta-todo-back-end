package dominio;

public class DetalleLista {

    private int id;
    private Producto producto;
    private int cantidad;
    private ListaCompra lista;

    public DetalleLista() {
    }

    public DetalleLista(int id, Producto producto, int cantidad, ListaCompra lista) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.lista = lista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ListaCompra getLista() {
        return lista;
    }

    public void setLista(ListaCompra lista) {
        this.lista = lista;
    }
    public DetalleLista crearDetallelista(int id,Producto producto,int cantidad,ListaCompra lista){return new DetalleLista(id,producto,cantidad,lista);}

    public void mostrarDetalleLista() {

        System.out.println("ID: "+ id);
        System.out.println("Producto: " + producto);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Lista: " + lista);
    }
}