package application.repositorio;

import application.domain.Rol;
import application.service.ports.RolRepositorioPort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RolRepositorio implements RolRepositorioPort {

    private final List<Rol> lista = new ArrayList<>(
            Arrays.asList(
                    new Rol(1, "Administrador", "Rol con todos los permisos"),
                    new Rol(2, "Usuario", "Rol con permisos para listas")
            )
    );

    @Override
    public void guardar(Rol rol) {
        lista.add(rol);
    }

    @Override
    public Rol buscarPorId(int id) {
        for (Rol rol : lista) {
            if (rol.getId() == id) {
                return rol;
            }
        }
        return null;
    }

    @Override
    public List<Rol> obtenerTodos() {
        return lista;
    }

    @Override
    public void eliminar(int id) {
        lista.removeIf(r -> r.getId() == id);
    }

}