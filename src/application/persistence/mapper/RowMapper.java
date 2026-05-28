package application.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contrato genérico para convertir una fila de ResultSet en un objeto de dominio.
 *
 * <T> es el tipo de objeto que produce el mapper.
 * Ejemplo: RowMapper<Rol>, RowMapper<Usuario>, etc.
 */
public interface RowMapper<T> {

    T mapRow(ResultSet rs) throws SQLException;
}
