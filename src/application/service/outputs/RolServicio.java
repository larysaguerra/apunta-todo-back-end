package application.service.outputs;

import application.domain.Rol;
import java.util.List;
import java.util.Optional;

public interface RolServicio {

    void crear(Rol rol);
    Optional<Rol> buscarPorId(int id);
    List<Rol> obtenerTodos();
    void eliminar(int id);
}