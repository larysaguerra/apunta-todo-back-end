package application.service.outputs;

import application.domain.ListaCompra;
import java.util.List;
import java.util.Optional;

public interface ListaCompraRepositorioPort {

    void guardar(ListaCompra listaCompra);
    Optional<ListaCompra> buscarPorId(int id);
    List<ListaCompra> obtenerTodos();
    void actualizar(ListaCompra listaCompra);
    void eliminar(int id);
}
