package umg.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mikesb
 */
public class Conexion {

    private Connection con = null;
    private static Conexion instancia;
    private static final String USER_ADMIN = "administrador";
    private static final String PASS_ADMIN = "admin";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

    public Conexion() {

    }

    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection Conectar() {

        cargarDriver();
        try {
            con = DriverManager.getConnection(URL, USER_ADMIN, PASS_ADMIN);
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return con;
    }

    public Connection Conectar(int userID) {

        cargarDriver();
        try {
            con = DriverManager.getConnection(URL, USER_ADMIN, PASS_ADMIN);
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return con;
    }

    public void cargarDriver() {

        try {
            Class.forName(DRIVER).newInstance();
        } catch (Exception e) {
            System.out.println("JAVA: Class.forName() error");
            e.printStackTrace();
        }

    }

    public void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
