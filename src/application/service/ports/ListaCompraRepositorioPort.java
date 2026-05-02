package application.service.ports;

import application.domain.ListaCompra;
import java.util.List;

public interface ListaCompraRepositorioPort {

    void guardar(ListaCompra listaCompra);
    ListaCompra buscarPorId(int id);
    List<ListaCompra> obtenerTodos();
    void actualizar(ListaCompra listaCompra);
    void eliminar(int id);
}
