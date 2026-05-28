package application.persistence.repositorio;

import application.domain.DetalleLista;
import application.persistence.database.DataBaseConnectionMySql;
import application.persistence.mapper.DetalleListaRowMapper;
import application.service.ports.DetalleListaRepositorioPort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleListaAdapterMySql implements DetalleListaRepositorioPort {

    private final DetalleListaRowMapper mapper = new DetalleListaRowMapper();

    // JOIN con tbl_productos y tbl_categorias para traer el Producto completo dentro del ítem
    private static final String SELECT_BASE =
            "SELECT i.id_item, i.id_lista, i.cantidad, i.comprado, " +
            "       p.id_producto, p.nombre AS nombre_producto, p.unidad_de_medida, " +
            "       c.id_categoria, c.nombre AS nombre_categoria, c.descripcion AS desc_categoria " +
            "FROM tbl_items i " +
            "INNER JOIN tbl_productos p ON i.id_producto = p.id_producto " +
            "INNER JOIN tbl_categorias c ON p.id_categoria = c.id_categoria ";

    private Connection getConexion() throws SQLException {
        return DataBaseConnectionMySql.getInstance().getConexion();
    }

    @Override
    public void guardar(DetalleLista detalle) {
        String sql = "INSERT INTO tbl_items (id_lista, id_producto, cantidad, comprado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, detalle.getListaId());
            ps.setInt(2, detalle.getProducto().getId());
            ps.setInt(3, detalle.getCantidad());
            ps.setBoolean(4, detalle.isComprado());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar detalle: " + e.getMessage());
        }
    }

    @Override
    public DetalleLista buscarPorId(int id) {
        String sql = SELECT_BASE + "WHERE i.id_item = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar detalle: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<DetalleLista> obtenerTodos() {
        List<DetalleLista> resultado = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(SELECT_BASE);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar detalles: " + e.getMessage());
        }
        return resultado;
    }

    @Override
    public void actualizar(DetalleLista detalle) {
        String sql = "UPDATE tbl_items SET id_lista=?, id_producto=?, cantidad=?, comprado=? WHERE id_item=?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, detalle.getListaId());
            ps.setInt(2, detalle.getProducto().getId());
            ps.setInt(3, detalle.getCantidad());
            ps.setBoolean(4, detalle.isComprado());
            ps.setInt(5, detalle.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar detalle: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM tbl_items WHERE id_item = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar detalle: " + e.getMessage());
        }
    }
}
