package aplicacion;

import aplicacion.puertos.CategoriaRepositoryPort;
import dominio.CategoriaProducto;
import java.util.List;

public class CategoriaService {
    private final CategoriaRepositoryPort repository;

    public CategoriaService(CategoriaRepositoryPort repository) {
        this.repository = repository;
    }

    public CategoriaProducto crearCategoria(CategoriaProducto categoria) {
        return repository.save(categoria);
    }

    public List<CategoriaProducto> obtenerTodos() {
        return repository.findAll();
    }
}