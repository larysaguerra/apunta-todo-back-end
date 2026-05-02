package application.repositorio;

import application.service.ports.ListaCompraRepositorioPort;
import application.domain.ListaCompra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaCompraRepositorio implements ListaCompraRepositorioPort {

    private final List<ListaCompra> lista = new ArrayList<>(
            Arrays.asList(
                    new ListaCompra(1, "Mercado semanal", "2026-02-11", 2),
                    new ListaCompra(2, "Fruver", "2026-03-02", 2),
                    new ListaCompra(3, "Aseo", "2026-04-15", 2)
            )
    );

    @Override
    public void guardar(ListaCompra listaCompra) {
        lista.add(listaCompra);
    }

    @Override
    public ListaCompra buscarPorId(int id) {
        for (ListaCompra listaCompra : lista) {
            if (listaCompra.getId() == id) {
                return listaCompra;
            }
        }
        return null;
    }

    @Override
    public List<ListaCompra> obtenerTodos() {
        return lista;
    }

    @Override
    public void actualizar(ListaCompra listaCompra) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == listaCompra.getId()) {
                lista.set(i, listaCompra);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        lista.removeIf(listaCompra -> listaCompra.getId() == id);
    }
}
