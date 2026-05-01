package aplicacion.puertos;

import dominio.CategoriaProducto;
import java.util.List;

public interface CategoriaRepositoryPort {
    CategoriaProducto save(CategoriaProducto categoria);
    List<CategoriaProducto> findAll();
}
