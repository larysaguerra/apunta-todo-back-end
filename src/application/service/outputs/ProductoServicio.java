package application.service.outputs;

import application.domain.Producto;
import java.util.List;

public interface ProductoServicio {

    void crear(Producto producto);
    Producto buscarPorId(int id);
    List<Producto> obtenerTodos();
    void eliminar(int id);
}