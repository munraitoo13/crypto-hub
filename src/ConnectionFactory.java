import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {

    private static final String URL  = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "";
    private static final String PASS = "";

    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver"); // funciona com ojdbc11 também
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver Oracle JDBC não encontrado no classpath.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        conn.setAutoCommit(false); // usaremos commit/rollback explícitos
        return conn;
    }

    private ConnectionFactory() {}
}
