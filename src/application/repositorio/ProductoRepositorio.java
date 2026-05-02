package application.repositorio;

import application.domain.Producto;
import application.service.ports.ProductoRepositorioPort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductoRepositorio implements ProductoRepositorioPort {

    private List<Producto> lista = new ArrayList<>(
            Arrays.asList(
                    new Producto(1, "Leche", "Litros", 1),
                    new Producto(2, "Manzana", "Kilos", 2)
            )
    );

    @Override
    public void guardar(Producto producto) {
        lista.add(producto);
    }

    @Override
    public List<Producto> obtenerTodos() {
        return lista;
    }

    @Override
    public Producto buscar(int id) {
        for (Producto p : lista) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    @Override
    public void eliminar(int id) {
        lista.removeIf(p -> p.getId() == id);
    }

}