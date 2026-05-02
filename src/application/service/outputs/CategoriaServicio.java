package application.service.outputs;

import application.domain.CategoriaProducto;
import java.util.List;

public interface CategoriaServicio {

    void crear(CategoriaProducto categoria);
    List<CategoriaProducto> obtenerTodos();
    void eliminar(int id);
}