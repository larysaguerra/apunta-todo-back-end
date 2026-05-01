package application.ports;

import dominio.Producto;
import java.util.List;

public interface ProductoRepositorioPort {

    void guardar(Producto producto);
    List<Producto> listar();
    Producto buscar(int id);
    void eliminar(int id);
}