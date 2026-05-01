package infraestructura.repositorio;

import dominio.Producto;
import application.ports.ProductoRepositorioPort;

import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorio implements ProductoRepositorioPort {

    private List<Producto> lista = new ArrayList<>();

    @Override
    public void guardar(Producto producto) {
        lista.add(producto);
    }

    @Override
    public List<Producto> listar() {
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