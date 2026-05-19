package application.domain;

public class DetalleLista {

    private int id;
    private int productoId;
    private int cantidad;
    private int listaId;

    public DetalleLista() {
    }

    public DetalleLista(int id, int productoId, int cantidad, int listaId) {
        this.id = id;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.listaId = listaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getListaId() {
        return this.listaId;
    }

    public void setListaId(int lista) {
        this.listaId = lista;
    }

}