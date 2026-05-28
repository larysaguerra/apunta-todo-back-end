package application.persistence.mapper;

import application.domain.Rol;
import application.domain.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Lee una fila del JOIN entre tbl_usuarios y tbl_roles.
 * Construye primero el Rol y luego el Usuario que lo contiene.
 */
public class UsuarioRowMapper implements RowMapper<Usuario> {

    @Override
    public Usuario mapRow(ResultSet rs) throws SQLException {
        Rol rol = new Rol(
                rs.getInt("id_rol"),
                rs.getString("nombre_rol"),
                rs.getString("desc_rol")
        );

        return new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("email"),
                rs.getString("contrasena"),
                rs.getString("telefono"),
                rol
        );
    }
}
