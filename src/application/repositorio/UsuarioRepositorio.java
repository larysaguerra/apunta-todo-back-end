package application.repositorio;

import application.service.ports.UsuarioRepositorioPort;
import application.domain.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsuarioRepositorio implements UsuarioRepositorioPort {

    private final List<Usuario> lista = new ArrayList<>(
            Arrays.asList(
                    new Usuario(1, "Yetty", "Sanz", "yetty@email.com", "300123456", "1234", 1),
                    new Usuario(2, "Larysa", "Guerra", "larysa@email.com", "333123456", "1234", 2)
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