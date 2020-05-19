package umg.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import umg.negocio.Departamento;
import umg.negocio.Empleado;
import umg.negocio.Puesto;

/**
 *
 * @author mikesb
 */
public class PuestosDAO {

    public static final int DEPARTAMENTOS = 1;
    public static final int PUESTOS = 2;
    public static final int ROLES = 3;

    private CallableStatement cs;
    private Connection con;
    private ResultSet rs;
    private static PuestosDAO instancia;

    public static PuestosDAO getInstancia() {
        if (instancia == null) {
            instancia = new PuestosDAO();
        }
        return instancia;
    }

    public ArrayList<Puesto> getPuestos() throws SQLException {
        con = Conexion.getInstancia().Conectar();
        ArrayList<Puesto> puestos = new ArrayList<>();
        Puesto puesto;

        try {
            cs = con.prepareCall("{call GET_DEPA_PUESTO_ROL(?,?)}");
            cs.registerOutParameter(1, java.sql.JDBCType.REF_CURSOR);
            cs.setInt(2, PUESTOS);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                puesto = new Puesto();
                puesto.setId_puesto(rs.getInt(1));
                puesto.setPuesto(rs.getString(2));
                puestos.add(puesto);
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            rs.close();
            cs.close();
            con.close();
        }
        return puestos;
    }
    
    
    public ArrayList<Departamento> getDepartamentos() throws SQLException {
        con = Conexion.getInstancia().Conectar();
        ArrayList<Departamento> departamentos = new ArrayList<>();
        Departamento departamento;

        try {
            cs = con.prepareCall("{call GET_DEPA_PUESTO_ROL(?,?)}");
            cs.registerOutParameter(1, java.sql.JDBCType.REF_CURSOR);
            cs.setInt(2, DEPARTAMENTOS);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);
            
            while(rs.next()) {
                departamento = new Departamento();
                departamento.setId_departamento(rs.getInt(1));
                departamento.setDepartamento(rs.getString(2));
                departamentos.add(departamento);
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            rs.close();
            cs.close();
            con.close();
        }
        return departamentos;
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
