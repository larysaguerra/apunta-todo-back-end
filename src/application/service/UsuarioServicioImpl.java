package application.service;

import application.service.outputs.UsuarioServicio;
import application.service.ports.UsuarioRepositorioPort;
import application.domain.Usuario;

import java.util.List;
import java.util.Optional;

public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepositorioPort repositorio;

    public UsuarioServicioImpl(UsuarioRepositorioPort repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void crear(Usuario usuario) {
        repositorio.guardar(usuario);
    }

    @Override
    public Optional<Usuario> leerPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    @Override
    public Usuario iniciarSesion(String correo, String contrasena) {
        for (Usuario usuario : repositorio.obtenerTodos()) {
            if (usuario.getCorreo().equalsIgnoreCase(correo) &&
                    usuario.getContrasena().equals(contrasena)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    @Override
    public void actualizar(Usuario usuario) {
        repositorio.actualizar(usuario);
    }

    @Override
    public void eliminar(int id) {
        repositorio.eliminar(id);
    }
}
