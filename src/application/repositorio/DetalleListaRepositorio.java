package application.repositorio;

import application.domain.CategoriaProducto;
import application.domain.DetalleLista;
import application.domain.Producto;
import application.service.ports.DetalleListaRepositorioPort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetalleListaRepositorio implements DetalleListaRepositorioPort {

    // Productos que los detalles de prueba van a referenciar
    private final CategoriaProducto lacteos  = new CategoriaProducto(1, "Lacteos", "Productos derivados de la leche");
    private final CategoriaProducto frutas   = new CategoriaProducto(2, "Frutas", "Comestibles obtenidos de plantas");

    private final Producto leche   = new Producto(1, "Leche",   "Litros", lacteos);
    private final Producto manzana = new Producto(2, "Manzana", "Kilos",  frutas);

    private final List<DetalleLista> lista = new ArrayList<>(
            Arrays.asList(
                    new DetalleLista(1, leche,   2, 1),
                    new DetalleLista(2, manzana, 3, 2)
            )
    );

    @Override
    public void guardar(DetalleLista detalleLista) {
        lista.add(detalleLista);
    }

    @Override
    public DetalleLista buscarPorId(int id) {
        for (DetalleLista detalle : lista) {
            if (detalle.getId() == id) {
                return detalle;
            }
        }
        return null;
    }

    @Override
    public List<DetalleLista> obtenerTodos() {
        return lista;
    }

    @Override
    public void actualizar(DetalleLista detalleLista) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == detalleLista.getId()) {
                lista.set(i, detalleLista);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        lista.removeIf(detalle -> detalle.getId() == id);
    }
}
