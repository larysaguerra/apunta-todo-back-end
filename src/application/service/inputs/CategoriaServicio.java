package application.service.inputs;

import application.domain.CategoriaProducto;
import java.util.List;
import java.util.Optional;

public interface CategoriaServicio {

    void crear(CategoriaProducto categoria);
    Optional<CategoriaProducto> buscarPorId(int id);
    List<CategoriaProducto> obtenerTodos();
    void eliminar(int id);
}