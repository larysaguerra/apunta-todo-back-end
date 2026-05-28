package application.persistence.repositorio;

import application.domain.Usuario;
import application.persistence.database.DataBaseConnectionMySql;
import application.persistence.mapper.UsuarioRowMapper;
import application.service.ports.UsuarioRepositorioPort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioAdapterMySql implements UsuarioRepositorioPort {

    private final UsuarioRowMapper mapper = new UsuarioRowMapper();

    // Query base con JOIN para traer el Rol completo dentro del Usuario
    private static final String SELECT_BASE =
            "SELECT u.id_usuario, u.nombre, u.apellido, u.telefono, u.email, u.contrasena, " +
            "       r.id_rol, r.nombre AS nombre_rol, r.descripcion AS desc_rol " +
            "FROM tbl_usuarios u " +
            "INNER JOIN tbl_roles r ON u.id_rol = r.id_rol ";

    private Connection getConexion() throws SQLException {
        return DataBaseConnectionMySql.getInstance().getConexion();
    }

    @Override
    public void guardar(Usuario usuario) {
        String sql = "INSERT INTO tbl_usuarios (id_rol, nombre, apellido, telefono, email, contrasena) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, usuario.getRol().getId());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getTelefono());
            ps.setString(5, usuario.getCorreo());
            ps.setString(6, usuario.getContrasena());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar usuario: " + e.getMessage());
        }
    }

    @Override
    public Optional<Usuario> buscarPorId(int id) {
        String sql = SELECT_BASE + "WHERE u.id_usuario = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Usuario> obtenerTodos() {
        List<Usuario> resultado = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(SELECT_BASE);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
        return resultado;
    }

    @Override
    public void actualizar(Usuario usuario) {
        String sql = "UPDATE tbl_usuarios " +
                     "SET id_rol=?, nombre=?, apellido=?, telefono=?, email=?, contrasena=? " +
                     "WHERE id_usuario=?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, usuario.getRol().getId());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getTelefono());
            ps.setString(5, usuario.getCorreo());
            ps.setString(6, usuario.getContrasena());
            ps.setInt(7, usuario.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM tbl_usuarios WHERE id_usuario = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }
    }
}
