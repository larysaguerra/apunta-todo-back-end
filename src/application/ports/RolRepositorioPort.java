package application.ports;

import dominio.Rol;
import java.util.List;

public interface RolRepositorioPort {

    void guardar(Rol rol);
    List<Rol> listar();
    void eliminar(int id);
}