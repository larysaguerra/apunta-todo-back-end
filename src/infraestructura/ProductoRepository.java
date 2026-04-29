package infraestructura;

import aplicacion.puertos.ProductoRepositoryPort;
import dominio.Producto;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepository implements ProductoRepositoryPort {
    private List<Producto> db = new ArrayList<>();

    @Override
    public Producto save(Producto producto) {
        db.add(producto); // Esta es la única línea necesaria
        return producto;
    }

    @Override
    public List<Producto> findAll() {
        return new ArrayList<>(db);
    }
}