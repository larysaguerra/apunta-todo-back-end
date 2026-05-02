package application.service.outputs;

import application.domain.Rol;
import java.util.List;

public interface RolServicio {

    void crear(Rol rol);
    List<Rol> obtenerTodos();
    void eliminar(int id);
}