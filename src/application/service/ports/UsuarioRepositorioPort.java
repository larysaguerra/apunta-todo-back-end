package application.service.ports;

import application.domain.Usuario;
import java.util.List;

public interface UsuarioRepositorioPort {

    void guardar(Usuario usuario);
    Usuario buscarPorId(int id);
    List<Usuario> obtenerTodos();
    void actualizar(Usuario usuario);
    void eliminar(int id);
}
