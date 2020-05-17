package umg.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleResultSet;

/**
 *
 * @author mikesb
 */
public class LoginDao {

    private Connection con = null;
    private ResultSet rs = null;
    private CallableStatement cs = null;
    private static LoginDao instancia;
    private PreparedStatement ps = null;
    
    public LoginDao() {
    }
    
    public static LoginDao getInstancia() {
        if (instancia == null) {
            instancia = new LoginDao();
        }
        return instancia;
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
    
    public boolean validate(String username, String password) throws SQLException {
        boolean status = false;
        try {
            con = Conexion.getInstancia().Conectar();

            cs = con.prepareCall("{Begin call validateLogin(?,?,?); End;}");

            cs.registerOutParameter(1, java.sql.JDBCType.INTEGER);
            cs.setString(2, username);
            cs.setString(3, password);

            cs.execute();

            

            /*if (encontrado > 0) {
                acceder = true;
            }*/
        } catch (SQLException ex) {

            printSQLException(ex);

        } finally {
            rs.close();
            cs.close();
            con.close();
        }
        return status;
    }

}
