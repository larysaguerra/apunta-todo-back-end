package application.repositorio;

import application.domain.CategoriaProducto;
import application.domain.Producto;
import application.service.ports.ProductoRepositorioPort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductoRepositorio implements ProductoRepositorioPort {

    // Categorías que los productos de prueba van a referenciar
    private final CategoriaProducto lacteos  = new CategoriaProducto(1, "Lacteos", "Productos derivados de la leche");
    private final CategoriaProducto frutas   = new CategoriaProducto(2, "Frutas", "Comestibles obtenidos de plantas");

    private List<Producto> lista = new ArrayList<>(
            Arrays.asList(
                    new Producto(1, "Leche",   "Litros",   lacteos),
                    new Producto(2, "Manzana", "Kilos",    frutas)
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
