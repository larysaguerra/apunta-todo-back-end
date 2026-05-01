package application.service;

import dominio.Rol;
import application.ports.RolRepositorioPort;

import java.util.List;

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
    public List<Rol> listar() {
        return repositorio.listar();
    }

    @Override
    public void eliminar(int id) {
        repositorio.eliminar(id);
    }
}