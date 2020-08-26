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
import umg.negocio.Rol;

/**
 *
 * @author mikesb
 */
public class PuestosDAO {
    //Estas variables globales se usan para mandar como parametro
    //para obtener los datos deseados, ya sea departamentos, puestos o roles
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

     public ArrayList<Departamento> getDepartamentos(int id_rol) throws SQLException {
        con = Conexion.getInstancia().Conectar(id_rol);
        ArrayList<Departamento> departamentos = new ArrayList<>();
        Departamento departamento;

        try {
            cs = con.prepareCall("{call ADMINISTRADOR.GET_DEPA_PUESTO_ROL(?,?,?)}");
            cs.registerOutParameter(1, java.sql.JDBCType.REF_CURSOR);
            cs.setInt(2, DEPARTAMENTOS);
            cs.setInt(3, 0);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
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
    
    public ArrayList<Puesto> getPuestos(int id_departamento, int id_rol) throws SQLException {
        con = Conexion.getInstancia().Conectar(id_rol);
        ArrayList<Puesto> puestos = new ArrayList<>();
        Puesto puesto;

        try {
            cs = con.prepareCall("{call ADMINISTRADOR.GET_DEPA_PUESTO_ROL(?,?,?)}");
            cs.registerOutParameter(1, java.sql.JDBCType.REF_CURSOR);
            cs.setInt(2, PUESTOS);
            cs.setInt(3, id_departamento);
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

    public ArrayList<Rol> getRoles(int id_rol) throws SQLException {
        ArrayList<Rol> roles = new ArrayList<>();
        Rol rol;
        con = Conexion.getInstancia().Conectar();
        try {
            
            cs = con.prepareCall("{call ADMINISTRADOR.GET_DEPA_PUESTO_ROL(?,?,?)}");
            cs.registerOutParameter(1, java.sql.JDBCType.REF_CURSOR);
            cs.setInt(2, ROLES);
            cs.setInt(3, 0);
            cs.execute();
           
            rs = (ResultSet) cs.getObject(1);
            
            while (rs.next()) {
                rol = new Rol();
                rol.setId_rol(rs.getInt(1));
                rol.setRol(rs.getString(2));
                roles.add(rol);
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            con.close();
            rs.close();
            cs.close();
        }
        return roles;
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
