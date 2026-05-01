package aplicacion;

import aplicacion.puertos.RolRepositoryPort;
import dominio.Rol;
import java.util.List;

public class RolService {
    private final RolRepositoryPort repository;

    public RolService(RolRepositoryPort repository) {
        this.repository = repository;
    }

    public Rol crearRol(Rol rol) {
        return repository.save(rol);
    }

    public List<Rol> obtenerTodos() {
        return repository.findAll();
    }
}