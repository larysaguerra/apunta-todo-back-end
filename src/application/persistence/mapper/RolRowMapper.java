package application.persistence.mapper;

import application.domain.Rol;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RolRowMapper implements RowMapper<Rol> {

    @Override
    public Rol mapRow(ResultSet rs) throws SQLException {
        return new Rol(
                rs.getInt("id_rol"),
                rs.getString("nombre"),
                rs.getString("descripcion")
        );
    }
}
