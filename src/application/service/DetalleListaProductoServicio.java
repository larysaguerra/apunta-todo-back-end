package application.service;

import application.domain.DetalleLista;
import application.domain.Producto;
import application.service.outputs.ProductoServicio;

public class DetalleListaProductoServicio {

    private final ProductoServicio productoServicio;

    public DetalleListaProductoServicio(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    public String obtenerNombreProductoPorId(int productoId) {
        for (Producto producto : productoServicio.obtenerTodos()) {
            if (producto.getId() == productoId) {
                return producto.getNombre();
            }
        }
        return "No encontrado (id=" + productoId + ")";
    }

    public String describirDetalle(DetalleLista detalle) {
        String check = detalle.isComprado() ? "[✓]" : "[ ]";
        return check + " Detalle #" + detalle.getId()
                + " | Producto: " + obtenerNombreProductoPorId(detalle.getProductoId())
                + " | Cantidad: " + detalle.getCantidad();
    }

    public String describirDetalleConLista(DetalleLista detalle) {
        String check = detalle.isComprado() ? "[✓]" : "[ ]";
        return check + " " + detalle.getId()
                + " - Producto: " + obtenerNombreProductoPorId(detalle.getProductoId())
                + ", Cantidad: " + detalle.getCantidad()
                + ", Lista: " + detalle.getListaId();
    }
}
