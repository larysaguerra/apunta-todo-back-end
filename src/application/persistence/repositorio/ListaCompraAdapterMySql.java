package application.persistence.repositorio;

import application.domain.ListaCompra;
import application.persistence.database.DataBaseConnectionMySql;
import application.persistence.mapper.ListaCompraRowMapper;
import application.service.outputs.ListaCompraRepositorioPort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListaCompraAdapterMySql implements ListaCompraRepositorioPort {

    private final ListaCompraRowMapper mapper = new ListaCompraRowMapper();

    // JOIN con tbl_usuarios y tbl_roles para traer el Usuario completo dentro de la lista
    private static final String SELECT_BASE =
            "SELECT l.id_lista, l.nombre, l.fecha_creacion, l.estado, " +
            "       u.id_usuario, u.nombre AS nombre_usuario, u.apellido, u.telefono, u.email, u.contrasena, " +
            "       r.id_rol, r.nombre AS nombre_rol, r.descripcion AS desc_rol " +
            "FROM tbl_listas l " +
            "INNER JOIN tbl_usuarios u ON l.id_usuario = u.id_usuario " +
            "INNER JOIN tbl_roles r ON u.id_rol = r.id_rol ";

    private Connection getConexion() throws SQLException {
        return DataBaseConnectionMySql.getInstance().getConexion();
    }

    @Override
    public void guardar(ListaCompra listaCompra) {
        String sql = "INSERT INTO tbl_listas (id_usuario, nombre, fecha_creacion, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, listaCompra.getUsuario().getId());
            ps.setString(2, listaCompra.getNombre());
            ps.setString(3, listaCompra.getFecha());
            ps.setString(4, listaCompra.getEstado().name());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar lista: " + e.getMessage());
        }
    }

    @Override
    public Optional<ListaCompra> buscarPorId(int id) {
        String sql = SELECT_BASE + "WHERE l.id_lista = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar lista: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<ListaCompra> obtenerTodos() {
        List<ListaCompra> resultado = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(SELECT_BASE);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar listas: " + e.getMessage());
        }
        return resultado;
    }

    @Override
    public void actualizar(ListaCompra listaCompra) {
        String sql = "UPDATE tbl_listas SET id_usuario=?, nombre=?, fecha_creacion=?, estado=? WHERE id_lista=?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, listaCompra.getUsuario().getId());
            ps.setString(2, listaCompra.getNombre());
            ps.setString(3, listaCompra.getFecha());
            ps.setString(4, listaCompra.getEstado().name());
            ps.setInt(5, listaCompra.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar lista: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM tbl_listas WHERE id_lista = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar lista: " + e.getMessage());
        }
    }
}
