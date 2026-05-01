package application.service;

import dominio.Rol;
import java.util.List;

public interface RolServicio {

    void crear(Rol rol);
    List<Rol> listar();
    void eliminar(int id);
}