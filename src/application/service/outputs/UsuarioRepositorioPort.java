package application.service.outputs;

import application.domain.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepositorioPort {

    void guardar(Usuario usuario);
    Optional<Usuario> buscarPorId(int id);
    List<Usuario> obtenerTodos();
    void actualizar(Usuario usuario);
    void eliminar(int id);
}
