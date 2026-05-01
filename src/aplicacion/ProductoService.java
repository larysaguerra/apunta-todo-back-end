package aplicacion;

import aplicacion.puertos.ProductoRepositoryPort;
import dominio.Producto;

import java.util.List;

public class ProductoService {
    private final ProductoRepositoryPort repository;

    public ProductoService(ProductoRepositoryPort repository) {
        this.repository = repository;
    }

    public Producto crearProducto(Producto producto) {
        return repository.save(producto);
    }

    public List<Producto> obtenerTodos() {
        return repository.findAll();
    }
}