package umg.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import umg.negocio.Banco;
import umg.negocio.Chequera;
import umg.negocio.Cuenta;

/**
 *
 * @author mikesb
 */
public class ChequeraDAO {

    private Connection con;
    private CallableStatement cst;
    private ResultSet rs;
    private static ChequeraDAO instancia;

    public static ChequeraDAO getInstancia() {
        if (instancia != null) {
            instancia = new ChequeraDAO();
        }
        return instancia;
    }

    public boolean insertarChequera(Chequera chequera, String cuenta) throws SQLException {
        con = Conexion.getInstancia().Conectar();
        boolean insertado = false;
        int exito = 0;
        try {

            cst = con.prepareCall("{call insertar_chequera(?,?,?,?,?)}");
            cst.setString(1, chequera.getId());
            cst.setString(2, cuenta);
            cst.setInt(3, chequera.getInicio());
            cst.setInt(4, chequera.getFin());
            cst.registerOutParameter(5, java.sql.JDBCType.INTEGER);
            cst.execute();
            exito = cst.getInt(5);
            if (exito == 1) {
                insertado = true;
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            con.close();
            cst.close();
        }
        return insertado;
    }

    public ArrayList<Chequera> getChequera() throws SQLException {
        con = Conexion.getInstancia().Conectar();
        ArrayList<Chequera> chequeras = new ArrayList<>();
        Cuenta cuenta;
        Chequera chequera;
        Banco banco;
        try {
            cst = con.prepareCall("{call get_chequeras(?)}");
            cst.registerOutParameter(1, java.sql.JDBCType.REF_CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(1);
            while (rs.next()) {
                cuenta = new Cuenta();
                banco = new Banco();
                chequera = new Chequera();

                chequera.setId(rs.getString(1));
                cuenta.setNumero_cuenta(rs.getString(2));
                banco.setNombre(rs.getString(3));
                chequera.setStock(rs.getInt(4));
                cuenta.setBanco(banco);
                chequera.setCuenta(cuenta);
                chequeras.add(chequera);
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            con.close();
            cst.close();
            rs.close();
        }
        return chequeras;
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
