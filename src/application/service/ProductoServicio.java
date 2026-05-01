package application.service;

import dominio.Producto;
import java.util.List;

public interface ProductoServicio {

    void crear(Producto producto);
    List<Producto> listar();
    void eliminar(int id);
}