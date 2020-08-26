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
    
    private static final String USER_ADMIN = "ADMINISTRADOR";
    private static final String PASS_ADMIN = "ADMIN";

    private static final String USER_CAJERO = "USUARIO_CAJERO";
    private static final String PASS_CAJERO = "CAJERO123";

    private static final String USER_PAGOS = "USUARIO_PAGOS";
    private static final String PASS_PAGOS = "PAGOS123";

    private static final String USER_AUDITOR = "USUARIO_AUDITOR";
    private static final String PASS_AUDITOR = "AUDITOR123";

    private static final String USER_GERENCIA = "USUARIO_GERENCIA";
    private static final String PASS_GERENCIA = "GERENCIA123";
    
    private static final String USER_JEFEPAGOS = "JEFE_PAGOS";
    private static final String PASS_JEFEPAGOS = "JEFEPAGOS123";
    
    public static final String USER_DEFAULT = "USUARIO_DEFAULT";
    private static final String PASS_DEFAULT = "DEFAULT123";

    private static final String URL = "jdbc:oracle:thin:@localhost:1521/orclpdb";
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

    /*
    En este metodo se recibe el id del usuario para conectarse a la base de datos
    con las credenciales del tipo de usuario en la base de datos
    */
    public Connection Conectar(int userID) {
        String usuario = null;
        String pass = null;
        cargarDriver();
        try {
            switch (userID) {

                case 1:
                    usuario = USER_ADMIN;
                    pass = PASS_ADMIN;
                    break;
                case 3:
                    usuario = USER_CAJERO;
                    pass = PASS_CAJERO;
                    break;

                case 2:
                    usuario = USER_PAGOS;
                    pass = PASS_PAGOS;
                    break;

                case 4:
                    usuario = USER_AUDITOR;
                    pass = PASS_AUDITOR;
                    break;
                    
                case 5:
                    usuario = USER_GERENCIA;
                    pass = PASS_GERENCIA;
                    break;
                    
                case 6:
                    System.out.println("Conectandome como jefe de pagos");
                    usuario = USER_JEFEPAGOS;
                    pass = PASS_JEFEPAGOS;
                    break;
                case 7:
                    usuario = USER_DEFAULT;
                    pass = PASS_DEFAULT;
                    break;
            }
            con = DriverManager.getConnection(URL, usuario, pass);

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
