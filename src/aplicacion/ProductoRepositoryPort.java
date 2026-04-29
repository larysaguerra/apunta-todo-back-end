package aplicacion.puertos;

import dominio.Producto;
import java.util.List;

public interface ProductoRepositoryPort {
    Producto save(Producto producto);
    List<Producto> findAll();
}