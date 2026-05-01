package application.service;

import dominio.Producto;
import application.ports.ProductoRepositorioPort;

import java.util.List;

public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepositorioPort repositorio;

    public ProductoServicioImpl(ProductoRepositorioPort repositorio) {
        this.repositorio = repositorio;
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
    public List<Producto> listar() {
        return repositorio.listar();
    }

    @Override
    public void eliminar(int id) {
        repositorio.eliminar(id);
    }
}