package infraestructura.repositorio;

import dominio.Rol;
import application.ports.RolRepositorioPort;

import java.util.ArrayList;
import java.util.List;

public class RolRepositorio implements RolRepositorioPort {

    private List<Rol> lista = new ArrayList<>();

    @Override
    public void guardar(Rol rol) {
        lista.add(rol);
    }

    @Override
    public List<Rol> listar() {
        return lista;
    }

    @Override
    public void eliminar(int id) {
        lista.removeIf(r -> r.getId() == id);
    }
}