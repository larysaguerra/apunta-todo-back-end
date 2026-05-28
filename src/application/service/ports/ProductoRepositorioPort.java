package application.service.ports;

import application.domain.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoRepositorioPort {

    void guardar(Producto producto);
    List<Producto> obtenerTodos();
    Optional<Producto> buscar(int id);
    void eliminar(int id);
}