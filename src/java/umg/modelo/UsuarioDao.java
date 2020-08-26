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
    //variables para obtener todos empleados, solo un empleado, un usuario o varios usuarios
    public static final int GET_ALL_EMPLOYES = 1;
    public static final int GET_ONLY_EMPLOYES = 2;
    public static final int GET_USER = 1;
    public static final int GET_USER_EDIT = 2;
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

    public Object[] insertarEmpleado(EmpleadoDTO empDTO, int id_rol) throws SQLException {
        con = Conexion.getInstancia().Conectar(id_rol);
        Object[] objetos = null;
        int errorCode = 0;
        String msg = "Sin errores";
        try {
            cs = con.prepareCall("{call ADMINISTRADOR.insertarEmpleado(?,?,?,?,?,?,?,?,?,?,?,?)}");
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

    public ArrayList<Empleado> obtenerEmpleados(int tipo, int id_rol) throws SQLException {
        con = Conexion.getInstancia().Conectar(id_rol);
        ArrayList<Empleado> empleados = new ArrayList<>();
        Empleado empleado;
        Puesto puesto = new Puesto();

        try {
            cs = con.prepareCall("{call ADMINISTRADOR.obtenerEmpleados(?,?)}");
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

    public ArrayList<UsuarioDTO> get_usuarios(int id_rol) throws SQLException {
        con = Conexion.getInstancia().Conectar();
        ArrayList<UsuarioDTO> usuarios = new ArrayList<>();
        try {
            cs = con.prepareCall("{call ADMINISTRADOR.obtenerEmpleados(?,?)}");
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

    public boolean insertarUsuario(UsuarioDTO usuarioDTO, int id_rol) throws SQLException {
        con = Conexion.getInstancia().Conectar(id_rol);
        System.out.println(usuarioDTO.toString());
        boolean exitoso = false;
        try {
            cs = con.prepareCall("{call ADMINISTRADOR.INSERTAR_USUARIO(?,?,?,?,?,?)}");
            cs.setString(1, usuarioDTO.getUsernamae());
            cs.setInt(2, usuarioDTO.getId_empleado());
            cs.setInt(3, usuarioDTO.getId_rol());
            cs.setInt(4, usuarioDTO.getMontoMinimo());
            cs.setInt(5, usuarioDTO.getMontoMaximo());
            cs.registerOutParameter(6, java.sql.JDBCType.INTEGER);
            cs.execute();
            int result = cs.getInt(6);
            if (result == 1) {
                exitoso = true;
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            con.close();
            cs.close();
        }
        return exitoso;
    }

    public UsuarioDTO get_usuario(String usuario, int tipo, int id_rol) {
        con = Conexion.getInstancia().Conectar(id_rol);
        UsuarioDTO usuarioDTO = null;
        try {
            cs = con.prepareCall("{call ADMINISTRADOR.obtener_usuario(?,?,?)}");
            cs.setString(1, usuario);
            cs.registerOutParameter(2, java.sql.JDBCType.REF_CURSOR);

            switch (tipo) {
                case 1:
                    cs.setInt(3, GET_USER);
                    cs.execute();
                    rs = (ResultSet) cs.getObject(2);

                    if (rs.next()) {
                        usuarioDTO = new UsuarioDTO();
                        String nombre = rs.getString(1);
                        String apellido = rs.getString(2);

                        usuarioDTO.setFullname(nombre + " " + apellido);
                        usuarioDTO.setUsernamae(rs.getString(3));
                        usuarioDTO.setId_rol(rs.getInt(4));
                        usuarioDTO.setRol(rs.getString(5));
                    }
                    break;
                case 2:
                    cs.setInt(3, GET_USER_EDIT);
                    cs.execute();
                    rs = (ResultSet) cs.getObject(2);

                    if (rs.next()) {
                        usuarioDTO = new UsuarioDTO();
                        usuarioDTO.setNombre(rs.getString(1));
                        usuarioDTO.setApellido(rs.getString(2));
                        usuarioDTO.setRol(rs.getString(3));
                        usuarioDTO.setMontoMaximo(rs.getInt(4));
                        usuarioDTO.setMontoMinimo(rs.getInt(5));
                    }
                    break;

            }

        } catch (SQLException ex) {
            printSQLException(ex);

        }
        return usuarioDTO;

    }

    /*METODO PARA MODIFICAR MONTO MAXIMO Y MINIMO DE UN USUARIO DEL SISTEMA */
    public boolean editar_usuario(UsuarioDTO usuarioDTO, int id_rol) throws SQLException {
        boolean respuesta = false;
        con = Conexion.getInstancia().Conectar(id_rol);
        try {
            
            cs = con.prepareCall("call ADMINISTRADOR.ACTUALIZAR_USUARIO(?,?,?,?,?)");
            cs.setString(1, usuarioDTO.getUsernamae());
            cs.setInt(2, usuarioDTO.getMontoMaximo());
            cs.setInt(3, usuarioDTO.getMontoMinimo());
            cs.setInt(4, usuarioDTO.getId_rol());
            cs.registerOutParameter(5, java.sql.Types.INTEGER);

            cs.execute();
            int res = cs.getInt(5);
            System.out.println(res);
            if (res == 1) {
                respuesta = true;
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            con.close();
            cs.close();
        }

        return respuesta;
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
