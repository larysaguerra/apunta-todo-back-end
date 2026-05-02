package application.service.outputs;

import application.domain.Producto;
import java.util.List;

public interface ProductoServicio {

    void crear(Producto producto);
    List<Producto> obtenerTodos();
    void eliminar(int id);
}