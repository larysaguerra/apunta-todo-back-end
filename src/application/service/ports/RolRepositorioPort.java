package application.service.ports;

import application.domain.Rol;
import java.util.List;
import java.util.Optional;

public interface RolRepositorioPort {

    void guardar(Rol rol);
    Optional<Rol> buscarPorId(int id);
    List<Rol> obtenerTodos();
    void eliminar(int id);
}