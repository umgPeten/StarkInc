package umg.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import umg.negocio.Banco;
import umg.negocio.Cheque;
import umg.negocio.Chequera;
import umg.negocio.Cuenta;

/**
 *
 * @author mikesb
 */
public class ReportesDAO {
    
    private CallableStatement cst;
    private Connection con;
    private ResultSet rs;
    private static ReportesDAO instancia;
    Cheque cheque;
    Banco banco;
    Cuenta cuenta;
    Chequera chequera;
    ArrayList<Cheque> cheques;

    public static ReportesDAO getInstancia() {
        if (instancia == null) {
            instancia = new ReportesDAO();
        }
        return instancia;
    }
    
    public ArrayList<Cheque> consultarCheques(int id_rol, String username, String num_cuenta, String num_chequera,
            String num_cheque, String fecha_from, String fecha_to) throws SQLException {

        con = Conexion.getInstancia().Conectar(id_rol);
        cheques = new ArrayList<>();

        try {
            cst = con.prepareCall("{call ADMINISTRADOR.consulta_cheques_reportes(?,?,?,?,?,?,?,?)}");
            cst.setString(1, username);
            cst.setString(2, num_cuenta);
            cst.setString(3, num_chequera);
            cst.setString(4, num_cheque);
            cst.setString(5, fecha_from);
            cst.setString(6, fecha_to);
            cst.setNull(7, java.sql.Types.INTEGER);
            cst.registerOutParameter(8, java.sql.JDBCType.REF_CURSOR);

            cst.execute();
            rs = (ResultSet) cst.getObject(8);

            while (rs.next()) {
                cheque = new Cheque();
                banco = new Banco();
                cuenta = new Cuenta();
                chequera = new Chequera();

                banco.setNombre(rs.getString(1));
                cuenta.setNumero_cuenta(rs.getString(2));
                chequera.setId(rs.getString(3));
                cheque.setId(rs.getString(4));
                cheque.setFecha(rs.getString(5));
                cheque.setLugar(rs.getString(6));
                cheque.setMonto(rs.getDouble(7));
                cheque.setBeneficiario(rs.getString(8));
                cheque.setEstado(rs.getString(9));

                cuenta.setBanco(banco);
                chequera.setCuenta(cuenta);
                cheque.setChequera(chequera);
                cheques.add(cheque);
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            con.close();
            cst.close();
            rs.close();
        }
        return cheques;

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
