package application.repositorio;

import application.domain.CategoriaProducto;
import application.service.ports.CategoriaRepositorioPort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoriaRepositorio implements CategoriaRepositorioPort {

    private List<CategoriaProducto> lista = new ArrayList<>(
            Arrays.asList(
                    new CategoriaProducto(1, "Lacteos", "Productos derivados de la leche"),
                    new CategoriaProducto(2, "Frutas", "Comestibles obtenidos de plantas")
            )
    );

    @Override
    public void guardar(CategoriaProducto categoria) {
        lista.add(categoria);
    }

    @Override
    public CategoriaProducto buscarPorId(int id) {
        for (CategoriaProducto categoria : lista) {
            if (categoria.getId() == id) {
                return categoria;
            }
        }
        return null;
    }

    @Override
    public List<CategoriaProducto> obtenerTodos() {
        return lista;
    }

    @Override
    public void eliminar(int id) {
        lista.removeIf(c -> c.getId() == id);
    }
}