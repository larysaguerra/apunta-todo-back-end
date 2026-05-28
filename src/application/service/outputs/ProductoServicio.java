package application.service.outputs;

import application.domain.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoServicio {

    void crear(Producto producto);
    Optional<Producto> buscarPorId(int id);
    List<Producto> obtenerTodos();
    void eliminar(int id);
}