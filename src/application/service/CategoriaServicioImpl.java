package application.service;

import dominio.CategoriaProducto;
import application.ports.CategoriaRepositorioPort;

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
    public List<CategoriaProducto> listar() {
        return repositorio.listar();
    }

    @Override
    public void eliminar(int id) {
        repositorio.eliminar(id);
    }
}