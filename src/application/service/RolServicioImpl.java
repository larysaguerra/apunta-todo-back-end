package application.service;

import application.domain.Rol;
import application.service.inputs.RolServicio;
import application.service.outputs.RolRepositorioPort;

import java.util.List;
import java.util.Optional;

public class RolServicioImpl implements RolServicio {

    private final RolRepositorioPort repositorio;

    public RolServicioImpl(RolRepositorioPort repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void crear(Rol rol) {
        repositorio.guardar(rol);
    }

    @Override
    public Optional<Rol> buscarPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    @Override
    public List<Rol> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    @Override
    public void eliminar(int id) {
        repositorio.eliminar(id);
    }
}