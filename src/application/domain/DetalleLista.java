package application.domain;

public class DetalleLista {

    private int id;
    private int productoId;
    private int cantidad;
    private int listaId;
    private boolean comprado;

    public DetalleLista() {
    }

    // Constructor sin comprado: por defecto el ítem no está comprado
    public DetalleLista(int id, int productoId, int cantidad, int listaId) {
        this.id = id;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.listaId = listaId;
        this.comprado = false;
    }

    // Constructor completo: permite indicar el estado explícitamente
    public DetalleLista(int id, int productoId, int cantidad, int listaId, boolean comprado) {
        this.id = id;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.listaId = listaId;
        this.comprado = comprado;
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
        return listaId;
    }

    public void setListaId(int listaId) {
        this.listaId = listaId;
    }

    public boolean isComprado() {
        return comprado;
    }

    public void setComprado(boolean comprado) {
        this.comprado = comprado;
    }
}
