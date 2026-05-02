package application.service.ports;

import application.domain.Producto;
import java.util.List;

public interface ProductoRepositorioPort {

    void guardar(Producto producto);
    List<Producto> obtenerTodos();
    Producto buscar(int id);
    void eliminar(int id);
}