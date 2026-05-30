package application.service.outputs;

import application.domain.CategoriaProducto;
import java.util.List;
import java.util.Optional;

public interface CategoriaRepositorioPort {

    void guardar(CategoriaProducto categoria);
    Optional<CategoriaProducto> buscarPorId(int id);
    List<CategoriaProducto> obtenerTodos();
    void eliminar(int id);
}