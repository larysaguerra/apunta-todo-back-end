package application.service;

import application.domain.Producto;
import application.service.inputs.ProductoServicio;
import application.service.outputs.ProductoRepositorioPort;

import java.util.List;
import java.util.Optional;

public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepositorioPort repositorio;

    public ProductoServicioImpl(ProductoRepositorioPort repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Optional<Producto> buscarPorId(int id) {
        return repositorio.buscar(id);
    }

    @Override
    public void crear(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            System.out.println("Nombre inválido");
            return;
        }
        repositorio.guardar(producto);
    }

    @Override
    public List<Producto> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    @Override
    public void eliminar(int id) {
        repositorio.eliminar(id);
    }
}