package application.repositorio;

import application.service.ports.DetalleListaRepositorioPort;
import application.domain.DetalleLista;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetalleListaRepositorio implements DetalleListaRepositorioPort {

    private final List<DetalleLista> lista = new ArrayList<>(
            Arrays.asList(
                    new DetalleLista(1, 1, 2, 1),
                    new DetalleLista(2, 2, 2, 2)
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