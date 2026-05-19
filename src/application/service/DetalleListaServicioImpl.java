package application.service;

import application.service.outputs.DesalleListaServicio;
import application.service.ports.DetalleListaRepositorioPort;
import application.domain.DetalleLista;

import java.util.List;

public class DetalleListaServicioImpl implements DesalleListaServicio {

    private final DetalleListaRepositorioPort repositorio;

    public DetalleListaServicioImpl(DetalleListaRepositorioPort repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void crear(DetalleLista detalleLista) {
        repositorio.guardar(detalleLista);
    }

    @Override
    public DetalleLista leerPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    @Override
    public List<DetalleLista> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    @Override
    public void actualizar(DetalleLista detalleLista) {
        repositorio.actualizar(detalleLista);
    }

    @Override
    public void eliminar(int id) {
        repositorio.eliminar(id);
    }
}
