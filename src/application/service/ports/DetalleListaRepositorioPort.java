package application.service.ports;

import application.domain.DetalleLista;
import java.util.List;
import java.util.Optional;

public interface DetalleListaRepositorioPort {

    void guardar(DetalleLista detalleLista);
    Optional<DetalleLista> buscarPorId(int id);
    List<DetalleLista> obtenerTodos();
    void actualizar(DetalleLista detalleLista);
    void eliminar(int id);
}
