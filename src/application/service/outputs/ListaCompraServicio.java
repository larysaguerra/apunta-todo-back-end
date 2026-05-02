package application.service.outputs;

import application.domain.ListaCompra;
import java.util.List;

public interface ListaCompraServicio {

    void crear(ListaCompra listaCompra);
    ListaCompra leerPorId(int id);
    List<ListaCompra> obtenerTodos();
    void actualizar(ListaCompra listaCompra);
    void eliminar(int id);
}
