package umg.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import umg.negocio.Login;

/**
 *
 * @author mikesb
 */
public class LoginDao {

    private Connection con;
    private ResultSet rs;
    private CallableStatement cs;
    private static LoginDao instancia;
    private PreparedStatement ps;

    public static LoginDao getInstancia() {
        if (instancia == null) {
            instancia = new LoginDao();
        }
        return instancia;
    }

    public boolean validate(Login login) throws SQLException {
        boolean acceso = false;
        try {
            con = Conexion.getInstancia().Conectar();

            cs = con.prepareCall("{call validateLogin(?,?,?)}");

            cs.registerOutParameter(1, java.sql.JDBCType.INTEGER);
            cs.setString(2, login.getUsername());
            cs.setString(3, login.getPassword());

            cs.executeQuery();

            int n = cs.getInt(1);

            if (n > 0) {
                acceso = true;
            }

        } catch (SQLException ex) {
            printSQLException(ex);
            acceso = false;
        } finally {
            cs.close();
            con.close();
        }

        return acceso;
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
