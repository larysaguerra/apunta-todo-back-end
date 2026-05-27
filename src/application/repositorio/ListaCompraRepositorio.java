package application.repositorio;

import application.service.ports.ListaCompraRepositorioPort;
import application.domain.ListaCompra;
import application.domain.Rol;
import application.domain.Usuario;
import application.domain.enums.EstadoLista;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaCompraRepositorio implements ListaCompraRepositorioPort {

    // Usuarios que las listas de prueba van a referenciar
    private final Rol rolUsuario = new Rol(2, "Usuario", "Rol con permisos para listas");

    private final Usuario larysa = new Usuario(2, "Larysa", "Guerra",
            "larysa@email.com", "1234", "333123456", rolUsuario);

    private final List<ListaCompra> lista = new ArrayList<>(
            Arrays.asList(
                    new ListaCompra(1, "Mercado semanal", "2026-02-11", larysa, EstadoLista.FAVORITA),
                    new ListaCompra(2, "Fruver",          "2026-03-02", larysa, EstadoLista.CERRADA),
                    new ListaCompra(3, "Aseo",            "2026-04-15", larysa, EstadoLista.ABIERTA)
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
