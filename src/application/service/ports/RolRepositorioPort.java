package application.service.ports;

import application.domain.Rol;
import java.util.List;

public interface RolRepositorioPort {

    void guardar(Rol rol);
    Rol buscarPorId(int id);
    List<Rol> obtenerTodos();
    void eliminar(int id);
}