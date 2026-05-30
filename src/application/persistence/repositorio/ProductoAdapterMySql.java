package application.persistence.repositorio;

import application.domain.Producto;
import application.persistence.database.DataBaseConnectionMySql;
import application.persistence.mapper.ProductoRowMapper;
import application.service.outputs.ProductoRepositorioPort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoAdapterMySql implements ProductoRepositorioPort {

    private final ProductoRowMapper mapper = new ProductoRowMapper();

    // Query base con JOIN para traer la CategoriaProducto completa dentro del Producto
    private static final String SELECT_BASE =
            "SELECT p.id_producto, p.nombre, p.unidad_de_medida, " +
            "       c.id_categoria, c.nombre AS nombre_categoria, c.descripcion AS desc_categoria " +
            "FROM tbl_productos p " +
            "INNER JOIN tbl_categorias c ON p.id_categoria = c.id_categoria ";

    private Connection getConexion() throws SQLException {
        return DataBaseConnectionMySql.getInstance().getConexion();
    }

    @Override
    public void guardar(Producto producto) {
        String sql = "INSERT INTO tbl_productos (nombre, unidad_de_medida, id_categoria) VALUES (?, ?, ?)";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getUnidadMedida());
            ps.setInt(3, producto.getCategoria().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar producto: " + e.getMessage());
        }
    }

    @Override
    public Optional<Producto> buscar(int id) {
        String sql = SELECT_BASE + "WHERE p.id_producto = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Producto> obtenerTodos() {
        List<Producto> resultado = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(SELECT_BASE);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
        return resultado;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM tbl_productos WHERE id_producto = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }
}
