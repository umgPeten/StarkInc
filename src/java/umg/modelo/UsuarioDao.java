package umg.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import umg.negocio.Departamento;
import umg.negocio.Empleado;
import umg.negocio.EmpleadoDTO;
import umg.negocio.Puesto;
import umg.negocio.UsuarioDTO;

/**
 *
 * @author mikesb
 */
public class UsuarioDao {

    public static final int GET_ALL_EMPLOYES = 1;
    public static final int GET_ONLY_EMPLOYES = 2;
    public static final int GET_USERS = 3;
    private Connection con;
    private ResultSet rs;
    private CallableStatement cs;
    private static UsuarioDao instancia;
    private UsuarioDTO userDTO;
    private Departamento departamento;

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
            cs = con.prepareCall("{call insertarEmpleado(?,?,?,?,?,?,?,?,?,?,?,?)}");
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

    public ArrayList<Empleado> obtenerEmpleados(int tipo) throws SQLException {
        con = Conexion.getInstancia().Conectar();
        ArrayList<Empleado> empleados = new ArrayList<>();
        Empleado empleado;
        Puesto puesto = new Puesto();

        try {
            cs = con.prepareCall("{call obtenerEmpleados(?,?)}");
            cs.registerOutParameter(1, java.sql.JDBCType.REF_CURSOR);
            if (tipo == GET_ALL_EMPLOYES) {
                cs.setInt(2, GET_ALL_EMPLOYES);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);

                while (rs.next()) {
                    empleado = new Empleado();
                    departamento = new Departamento();
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

            } else if (tipo == GET_ONLY_EMPLOYES) {
                cs.setInt(2, GET_ONLY_EMPLOYES);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);

                while (rs.next()) {
                    empleado = new Empleado();
                    empleado.setNombre(rs.getString(1));
                    empleado.setApellido(rs.getString(2));
                    empleado.setId_empleado(rs.getInt(3));
                    empleados.add(empleado);
                }
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            con.close();
            rs.close();
            cs.close();
        }
        return empleados;
    }

    public ArrayList<UsuarioDTO> get_usuarios() throws SQLException {
        con = Conexion.getInstancia().Conectar();
        ArrayList<UsuarioDTO> usuarios = new ArrayList<>();
        try {
            cs = con.prepareCall("{call obtenerEmpleados(?,?)}");
            cs.registerOutParameter(1, java.sql.JDBCType.REF_CURSOR);
            cs.setInt(2, GET_USERS);

            cs.execute();
            rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                departamento = new Departamento();
                userDTO = new UsuarioDTO();
                userDTO.setUsernamae(rs.getString(1));
                userDTO.setNombre(rs.getString(2));
                userDTO.setApellido(rs.getString(3));
                departamento.setDepartamento(rs.getString(4));
                userDTO.setRol(rs.getString(5));
                userDTO.setDepartamento(departamento);
                usuarios.add(userDTO);
                
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            cs.close();
            rs.close();
            con.close();
        }
        return usuarios;
    }

    public boolean insertarUsuario(UsuarioDTO usuarioDTO) throws SQLException {
        con = Conexion.getInstancia().Conectar();
        System.out.println(usuarioDTO.toString());
        boolean exitoso = false;
        try {
            cs = con.prepareCall("{call INSERTAR_USUARIO(?,?,?,?,?)}");
            cs.setString(1, usuarioDTO.getUsernamae());
            cs.setInt(2, usuarioDTO.getId_empleado());
            cs.setInt(3, usuarioDTO.getId_rol());
            cs.setInt(4, usuarioDTO.getMontoMinimo());
            cs.setInt(5, usuarioDTO.getMontoMaximo());

            exitoso = cs.execute();

        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            con.close();
            cs.close();
        }
        return exitoso;
    }

    public UsuarioDTO get_usuario(String usuario) {
        con = Conexion.getInstancia().Conectar();
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        try {
            cs = con.prepareCall("{call obtener_usuario(?,?)}");
            cs.setString(1, usuario);
            cs.registerOutParameter(2, java.sql.JDBCType.REF_CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(2);

            if (rs.next()) {

                String nombre = rs.getString(1);
                String apellido = rs.getString(2);

                usuarioDTO.setFullname(nombre + " " + apellido);
                usuarioDTO.setUsernamae(rs.getString(3));
                usuarioDTO.setId_rol(rs.getInt(4));
                usuarioDTO.setRol(rs.getString(5));
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return usuarioDTO;

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
