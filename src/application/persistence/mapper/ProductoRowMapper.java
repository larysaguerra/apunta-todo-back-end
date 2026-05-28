package application.persistence.mapper;

import application.domain.CategoriaProducto;
import application.domain.Producto;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Lee una fila del JOIN entre tbl_productos y tbl_categorias.
 * Construye primero la CategoriaProducto y luego el Producto que la contiene.
 */
public class ProductoRowMapper implements RowMapper<Producto> {

    @Override
    public Producto mapRow(ResultSet rs) throws SQLException {
        CategoriaProducto categoria = new CategoriaProducto(
                rs.getInt("id_categoria"),
                rs.getString("nombre_categoria"),
                rs.getString("desc_categoria")
        );

        return new Producto(
                rs.getInt("id_producto"),
                rs.getString("nombre"),
                rs.getString("unidad_de_medida"),
                categoria
        );
    }
}
