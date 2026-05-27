package application.service;

import application.domain.DetalleLista;

// Este servicio describe un DetalleLista de forma legible para el usuario.
// Ya no necesita buscar el producto porque DetalleLista lleva el objeto Producto adentro.
public class DetalleListaProductoServicio {

    public String describirDetalle(DetalleLista detalle) {
        String check = detalle.isComprado() ? "[✓]" : "[ ]";
        String nombreProducto = detalle.getProducto() != null
                ? detalle.getProducto().getNombre()
                : "Sin producto";

        return check + " Detalle #" + detalle.getId()
                + " | Producto: " + nombreProducto
                + " | Cantidad: " + detalle.getCantidad();
    }

    public String describirDetalleConLista(DetalleLista detalle) {
        String check = detalle.isComprado() ? "[✓]" : "[ ]";
        String nombreProducto = detalle.getProducto() != null
                ? detalle.getProducto().getNombre()
                : "Sin producto";

        return check + " " + detalle.getId()
                + " - Producto: " + nombreProducto
                + ", Cantidad: " + detalle.getCantidad()
                + ", Lista: " + detalle.getListaId();
    }
}
