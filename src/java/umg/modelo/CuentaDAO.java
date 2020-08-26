package umg.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import umg.negocio.Banco;
import umg.negocio.Cuenta;

/**
 *
 * @author mikesb
 */
public class CuentaDAO {

    private CallableStatement cs;
    private Connection con;
    private ResultSet rs;
    private static CuentaDAO instancia;

    public static CuentaDAO getInstancia() {
        if (instancia != null) {
            instancia = new CuentaDAO();
        }
        return instancia;
    }

    /*
        metodo para obtener todas las cuentas
    */
    public ArrayList<Cuenta> getCuentas(int id_rol) {
        con = Conexion.getInstancia().Conectar(id_rol);

        ArrayList<Cuenta> cuentas = new ArrayList<>();
        try {
            cs = con.prepareCall("{call ADMINISTRADOR.OBTENERCUENTAS(?)}");
            cs.registerOutParameter(1, java.sql.JDBCType.REF_CURSOR);
            cs.execute();

            rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                Banco banco = new Banco();
                cuenta.setNumero_cuenta(rs.getString(1));
                cuenta.setNombre(rs.getString(2));
                cuenta.setFondos(rs.getDouble(3));
                banco.setNombre(rs.getString(4));
                cuenta.setTipo_cuenta(rs.getString(5));
                cuenta.setMoneda(rs.getString(6));
                cuenta.setBanco(banco);
                cuentas.add(cuenta);
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {

        }

        return cuentas;
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
