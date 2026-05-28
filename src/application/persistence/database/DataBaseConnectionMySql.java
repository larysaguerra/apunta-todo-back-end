package application.persistence.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Conexión a MySQL — Patrón Singleton.
 *
 * Singleton garantiza que toda la aplicación use UNA SOLA conexión.
 * Si no fuera Singleton, cada repositorio abriría su propia conexión
 * y desperdiciaría recursos.
 *
 * Cómo usarla desde un repositorio:
 *   Connection conn = DataBaseConnectionMySql.getInstance().getConexion();
 */
public class DataBaseConnectionMySql {

    // ----------------------------------------------------------------
    // Configuración de conexión
    // ----------------------------------------------------------------
    private static final String URL      = "jdbc:mysql://localhost:3306/bd_apunta_todo"
                                         + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USUARIO  = "root";
    private static final String PASSWORD = "root.1234";

    // ----------------------------------------------------------------
    // Singleton: la única instancia de esta clase en toda la app
    // ----------------------------------------------------------------
    private static DataBaseConnectionMySql instancia;

    // La conexión JDBC activa
    private final Connection conexion;

    // Constructor PRIVADO: nadie puede hacer "new DataBaseConnectionMySql()"
    private DataBaseConnectionMySql() throws SQLException {
        this.conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
        System.out.println("Conexion a MySQL establecida correctamente.");
    }

    /**
     * Devuelve siempre la misma instancia.
     * Si la conexión se cerró, la vuelve a abrir automáticamente.
     */
    public static DataBaseConnectionMySql getInstance() throws SQLException {
        if (instancia == null || instancia.conexion.isClosed()) {
            instancia = new DataBaseConnectionMySql();
        }
        return instancia;
    }

    /** Retorna la conexión para usarla en los repositorios. */
    public Connection getConexion() {
        return conexion;
    }
}
