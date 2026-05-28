package application.persistence.repositorio;

import application.domain.Rol;
import application.persistence.database.DataBaseConnectionMySql;
import application.persistence.mapper.RolRowMapper;
import application.service.ports.RolRepositorioPort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolAdapterMySql implements RolRepositorioPort {

    private final RolRowMapper mapper = new RolRowMapper();

    private Connection getConexion() throws SQLException {
        return DataBaseConnectionMySql.getInstance().getConexion();
    }

    @Override
    public void guardar(Rol rol) {
        String sql = "INSERT INTO tbl_roles (nombre, descripcion) VALUES (?, ?)";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setString(1, rol.getNombre());
            ps.setString(2, rol.getDescripcion());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar rol: " + e.getMessage());
        }
    }

    @Override
    public Rol buscarPorId(int id) {
        String sql = "SELECT id_rol, nombre, descripcion FROM tbl_roles WHERE id_rol = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar rol: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Rol> obtenerTodos() {
        String sql = "SELECT id_rol, nombre, descripcion FROM tbl_roles";
        List<Rol> resultado = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar roles: " + e.getMessage());
        }
        return resultado;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM tbl_roles WHERE id_rol = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar rol: " + e.getMessage());
        }
    }
}
