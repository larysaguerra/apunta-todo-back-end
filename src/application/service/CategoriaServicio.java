package application.service;

import dominio.CategoriaProducto;
import java.util.List;

public interface CategoriaServicio {

    void crear(CategoriaProducto categoria);
    List<CategoriaProducto> listar();
    void eliminar(int id);
}