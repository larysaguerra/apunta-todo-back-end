package application.service.ports;

import application.domain.DetalleLista;
import java.util.List;

public interface DetalleListaRepositorioPort {

    void guardar(DetalleLista detalleLista);
    DetalleLista buscarPorId(int id);
    List<DetalleLista> obtenerTodos();
    void actualizar(DetalleLista detalleLista);
    void eliminar(int id);
}
