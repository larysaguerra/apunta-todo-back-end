package infraestructura.repositorio;

import dominio.CategoriaProducto;
import application.ports.CategoriaRepositorioPort;

import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositorio implements CategoriaRepositorioPort {

    private List<CategoriaProducto> lista = new ArrayList<>();

    @Override
    public void guardar(CategoriaProducto categoria) {
        lista.add(categoria);
    }

    @Override
    public List<CategoriaProducto> listar() {
        return lista;
    }

    @Override
    public void eliminar(int id) {
        lista.removeIf(c -> c.getId() == id);
    }
}