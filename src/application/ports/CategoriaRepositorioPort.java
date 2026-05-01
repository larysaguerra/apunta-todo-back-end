package application.ports;

import dominio.CategoriaProducto;
import java.util.List;

public interface CategoriaRepositorioPort {

    void guardar(CategoriaProducto categoria);
    List<CategoriaProducto> listar();
    void eliminar(int id);
}