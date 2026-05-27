package application.service;

import application.service.outputs.ListaCompraServicio;
import application.service.ports.ListaCompraRepositorioPort;
import application.domain.ListaCompra;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListaCompraServicioImpl implements ListaCompraServicio {

    private final ListaCompraRepositorioPort repositorio;

    public ListaCompraServicioImpl(ListaCompraRepositorioPort repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void crear(ListaCompra listaCompra) {
        repositorio.guardar(listaCompra);
    }

    @Override
    public ListaCompra leerPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    @Override
    public List<ListaCompra> obtenerTodos() {
        return repositorio.obtenerTodos().stream()
                .sorted(Comparator.comparing(ListaCompra::getEstado))
                .collect(Collectors.toList());
    }

    @Override
    public void actualizar(ListaCompra listaCompra) {
        repositorio.actualizar(listaCompra);
    }

    @Override
    public void eliminar(int id) {
        repositorio.eliminar(id);
    }
}
