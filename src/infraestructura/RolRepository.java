package infraestructura;

import aplicacion.puertos.RolRepositoryPort;
import dominio.Rol;
import java.util.ArrayList;
import java.util.List;

public class RolRepository implements RolRepositoryPort {
    private List<Rol> db = new ArrayList<>();

    @Override
    public Rol save(Rol rol) {
        db.add(rol);
        return rol;
    }

    @Override
    public List<Rol> findAll() {
        return new ArrayList<>(db);
    }
}