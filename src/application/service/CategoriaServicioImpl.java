package application.service;

import application.domain.CategoriaProducto;
import application.service.inputs.CategoriaServicio;
import application.service.outputs.CategoriaRepositorioPort;

import java.util.List;
import java.util.Optional;

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
    public Optional<CategoriaProducto> buscarPorId(int id) {
        return repositorio.buscarPorId(id);
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