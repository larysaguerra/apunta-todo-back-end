package application.persistence.mapper;

import application.domain.CategoriaProducto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaRowMapper implements RowMapper<CategoriaProducto> {

    @Override
    public CategoriaProducto mapRow(ResultSet rs) throws SQLException {
        return new CategoriaProducto(
                rs.getInt("id_categoria"),
                rs.getString("nombre"),
                rs.getString("descripcion")
        );
    }
}
