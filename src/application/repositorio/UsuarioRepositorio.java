package application.repositorio;

import application.service.ports.UsuarioRepositorioPort;
import application.domain.Rol;
import application.domain.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsuarioRepositorio implements UsuarioRepositorioPort {

    private final Rol rolAdmin = new Rol(1, "Administrador", "Rol con todos los permisos");
    private final Rol rolUsuario = new Rol(2, "Usuario", "Rol con permisos para listas");

    private final List<Usuario> lista = new ArrayList<>(
            Arrays.asList(
                    new Usuario(1, "Yetty", "Sanz", "yetty@email.com", "1234", "300123456", rolAdmin),
                    new Usuario(2, "Larysa", "Guerra", "larysa@email.com", "1234", "333123456", rolUsuario)
            )
    );

    @Override
    public void guardar(Usuario usuario) {
        lista.add(usuario);
    }

    @Override
    public Usuario buscarPorId(int id) {
        for (Usuario usuario : lista) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return lista;
    }

    @Override
    public void actualizar(Usuario usuario) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == usuario.getId()) {
                lista.set(i, usuario);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        lista.removeIf(usuario -> usuario.getId() == id);
    }
}
