package application.persistence.repositorio;

import application.domain.CategoriaProducto;
import application.persistence.database.DataBaseConnectionMySql;
import application.persistence.mapper.CategoriaRowMapper;
import application.service.ports.CategoriaRepositorioPort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaAdapterMySql implements CategoriaRepositorioPort {

    private final CategoriaRowMapper mapper = new CategoriaRowMapper();

    private Connection getConexion() throws SQLException {
        return DataBaseConnectionMySql.getInstance().getConexion();
    }

    @Override
    public void guardar(CategoriaProducto categoria) {
        String sql = "INSERT INTO tbl_categorias (nombre, descripcion) VALUES (?, ?)";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar categoria: " + e.getMessage());
        }
    }

    @Override
    public CategoriaProducto buscarPorId(int id) {
        String sql = "SELECT id_categoria, nombre, descripcion FROM tbl_categorias WHERE id_categoria = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar categoria: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<CategoriaProducto> obtenerTodos() {
        String sql = "SELECT id_categoria, nombre, descripcion FROM tbl_categorias";
        List<CategoriaProducto> resultado = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar categorias: " + e.getMessage());
        }
        return resultado;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM tbl_categorias WHERE id_categoria = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar categoria: " + e.getMessage());
        }
    }
}
