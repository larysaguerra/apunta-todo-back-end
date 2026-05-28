package application.service.outputs;

import application.domain.DetalleLista;
import java.util.List;
import java.util.Optional;

public interface DesalleListaServicio {

    void crear(DetalleLista detalleLista);
    Optional<DetalleLista> leerPorId(int id);
    List<DetalleLista> obtenerTodos();
    void actualizar(DetalleLista detalleLista);
    void eliminar(int id);
}
