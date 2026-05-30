package application.persistence.repositorio;

import application.domain.Rol;
import application.persistence.database.DataBaseConnectionMySql;
import application.persistence.mapper.RolRowMapper;
import application.service.outputs.RolRepositorioPort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Rol> buscarPorId(int id) {
        String sql = "SELECT id_rol, nombre, descripcion FROM tbl_roles WHERE id_rol = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar rol: " + e.getMessage());
        }
        return Optional.empty();
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
