package infraestructura;

import aplicacion.puertos.CategoriaRepositoryPort;
import dominio.CategoriaProducto;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepository implements CategoriaRepositoryPort {
    private List<CategoriaProducto> db = new ArrayList<>();

    @Override
    public CategoriaProducto save(CategoriaProducto categoria) {
        db.add(categoria);
        return categoria;
    }

    @Override
    public List<CategoriaProducto> findAll() {
        return new ArrayList<>(db);
    }
}