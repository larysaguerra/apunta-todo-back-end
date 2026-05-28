package application.service.outputs;

import application.domain.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioServicio {

    void crear(Usuario usuario);
    Optional<Usuario> leerPorId(int id);
    Usuario iniciarSesion(String correo, String contrasena);
    List<Usuario> obtenerTodos();
    void actualizar(Usuario usuario);
    void eliminar(int id);
}
