package application.persistence.mapper;

import application.domain.CategoriaProducto;
import application.domain.DetalleLista;
import application.domain.Producto;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Lee una fila del JOIN entre tbl_items, tbl_productos y tbl_categorias.
 * Construye CategoriaProducto → Producto → DetalleLista de adentro hacia afuera.
 */
public class DetalleListaRowMapper implements RowMapper<DetalleLista> {

    @Override
    public DetalleLista mapRow(ResultSet rs) throws SQLException {
        CategoriaProducto categoria = new CategoriaProducto(
                rs.getInt("id_categoria"),
                rs.getString("nombre_categoria"),
                rs.getString("desc_categoria")
        );

        Producto producto = new Producto(
                rs.getInt("id_producto"),
                rs.getString("nombre_producto"),
                rs.getString("unidad_de_medida"),
                categoria
        );

        return new DetalleLista(
                rs.getInt("id_item"),
                producto,
                rs.getInt("cantidad"),
                rs.getInt("id_lista"),
                rs.getBoolean("comprado")
        );
    }
}
