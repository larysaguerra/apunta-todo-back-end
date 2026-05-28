package application.service.outputs;

import application.domain.ListaCompra;
import java.util.List;
import java.util.Optional;

public interface ListaCompraServicio {

    void crear(ListaCompra listaCompra);
    Optional<ListaCompra> leerPorId(int id);
    List<ListaCompra> obtenerTodos();
    void actualizar(ListaCompra listaCompra);
    void eliminar(int id);
}
