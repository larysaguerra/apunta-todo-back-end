package aplicacion.puertos;

import dominio.Rol;
import java.util.List;

public interface RolRepositoryPort {
    Rol save(Rol rol);
    List<Rol> findAll();
}