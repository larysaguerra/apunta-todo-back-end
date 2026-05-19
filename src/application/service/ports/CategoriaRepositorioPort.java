package application.service.ports;

import application.domain.CategoriaProducto;
import java.util.List;

public interface CategoriaRepositorioPort {

    void guardar(CategoriaProducto categoria);
    List<CategoriaProducto> obtenerTodos();
    void eliminar(int id);
}