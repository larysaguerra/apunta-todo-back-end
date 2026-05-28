package application.persistence.mapper;

import application.domain.ListaCompra;
import application.domain.Rol;
import application.domain.Usuario;
import application.domain.enums.EstadoLista;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Lee una fila del JOIN entre tbl_listas, tbl_usuarios y tbl_roles.
 * Construye Rol → Usuario → ListaCompra de adentro hacia afuera.
 */
public class ListaCompraRowMapper implements RowMapper<ListaCompra> {

    @Override
    public ListaCompra mapRow(ResultSet rs) throws SQLException {
        Rol rol = new Rol(
                rs.getInt("id_rol"),
                rs.getString("nombre_rol"),
                rs.getString("desc_rol")
        );

        Usuario usuario = new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("nombre_usuario"),
                rs.getString("apellido"),
                rs.getString("email"),
                rs.getString("contrasena"),
                rs.getString("telefono"),
                rol
        );

        EstadoLista estado = EstadoLista.valueOf(rs.getString("estado"));

        return new ListaCompra(
                rs.getInt("id_lista"),
                rs.getString("nombre"),
                rs.getString("fecha_creacion"),
                usuario,
                estado
        );
    }
}
