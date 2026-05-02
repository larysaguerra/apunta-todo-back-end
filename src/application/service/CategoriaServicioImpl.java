package application.service;

import application.domain.CategoriaProducto;
import application.service.outputs.CategoriaServicio;
import application.service.ports.CategoriaRepositorioPort;

import java.util.List;

public class CategoriaServicioImpl implements CategoriaServicio {

    private final CategoriaRepositorioPort repositorio;

    public CategoriaServicioImpl(CategoriaRepositorioPort repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void crear(CategoriaProducto categoria) {
        repositorio.guardar(categoria);
    }

    @Override
    public List<CategoriaProducto> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    @Override
    public void eliminar(int id) {
        repositorio.eliminar(id);
    }
}