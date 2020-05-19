package umg.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import umg.negocio.Departamento;
import umg.negocio.Empleado;
import umg.negocio.EmpleadoDTO;
import umg.negocio.Puesto;

/**
 *
 * @author mikesb
 */
public class UsuarioDao {

    private Connection con;
    private ResultSet rs;
    private CallableStatement cs;
    private static UsuarioDao instancia;
    private PreparedStatement ps;

    public static UsuarioDao getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioDao();
        }
        return instancia;
    }

    public Object[] insertarEmpleado(EmpleadoDTO empDTO) throws SQLException {
        con = Conexion.getInstancia().Conectar();
        Object[] objetos = null;
        int errorCode = 0;
        String msg = "Sin errores";
        try {
            cs = con.prepareCall("{call insertarUsuario(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, 1);
            cs.setInt(2, empDTO.getIdentificacion());
            cs.setInt(3, empDTO.getTipo_id());
            cs.setString(4, empDTO.getNombre());
            cs.setString(5, empDTO.getApellido());
            cs.setString(6, empDTO.getTelefono());
            cs.setString(7, empDTO.getDireccion());
            cs.setInt(8, empDTO.getId_genero());
            cs.setInt(9, empDTO.getId_genero());
            cs.setString(10, empDTO.getFecha_nac());
            cs.registerOutParameter(11, java.sql.JDBCType.INTEGER);
            cs.registerOutParameter(12, java.sql.JDBCType.VARCHAR);
            cs.execute();

            errorCode = cs.getInt(11);
            msg = cs.getString(12);

            if (errorCode == 0) {
                objetos = new Object[]{true, errorCode, msg};
            } else {
                objetos = new Object[]{false, errorCode, msg};
            }
            System.out.println(errorCode);

        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            con.close();
            cs.close();
        }
        return objetos;
    }

    public ArrayList<Empleado> obtenerEmpleados() throws SQLException {
        con = Conexion.getInstancia().Conectar();
        ArrayList<Empleado> empleados = new ArrayList<>();
        Empleado empleado;
        Puesto puesto = new Puesto();
        Departamento departamento = new Departamento();

        try {
            cs = con.prepareCall("{call obtenerEmpleados(?)}");
            cs.registerOutParameter(1, java.sql.JDBCType.REF_CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                empleado = new Empleado();
                empleado.setId_empleado(rs.getInt(1));
                empleado.setNombre(rs.getString(2));
                empleado.setApellido(rs.getString(3));
                empleado.setDireccion(rs.getString(4));
                empleado.setTelefono(rs.getString(5));
                puesto.setPuesto(rs.getString(6));
                departamento.setDepartamento(rs.getString(7));
                puesto.setDepartamento(departamento);
                empleado.setPuesto(puesto);
                empleados.add(empleado);
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            con.close();
            rs.close();
            cs.close();
        }
        System.out.println(empleados.size());
        return empleados;
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
