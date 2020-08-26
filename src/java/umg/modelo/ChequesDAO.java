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
public class ChequesDAO {

    private Connection con;
    private CallableStatement cst;
    private ResultSet rs;
    private static ChequesDAO instancia;
    private Cheque cheque = null;
    private Chequera chequera = null;
    private Banco banco = null;
    private Cuenta cuenta = null;
    private ArrayList<Cheque> cheques = null;

    public static ChequesDAO getInstancia() {
        if (instancia != null) {
            instancia = new ChequesDAO();
        }
        return instancia;

    }

    /*Este metodo llama al procedimiento almacenado Consultar_cheques, y 
      su funcion es consultar cheques dependiendo los parametros enviados
      aca se envia el id_estado
     */
    public ArrayList<Cheque> consultarCheques(int id_rol, String username, String num_cuenta, String num_chequera,
            String num_cheque, String fecha_from, String fecha_to, int estado) throws SQLException {

        con = Conexion.getInstancia().Conectar(id_rol);
        cheques = new ArrayList<>();

        try {
            cst = con.prepareCall("{call ADMINISTRADOR.consulta_cheques(?,?,?,?,?,?,?,?)}");
            cst.setString(1, username);
            cst.setString(2, num_cuenta);
            cst.setString(3, num_chequera);
            cst.setString(4, num_cheque);
            cst.setString(5, fecha_from);
            cst.setString(6, fecha_to);
            cst.setInt(7, estado);
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

    /*
      Este metodo llama al procedimiento almacenado Consultar_cheques, y 
      su funcion es consultar cheques dependiendo los parametros enviados
      aca NO se envia el id_estado
  
     */
    public ArrayList<Cheque> consultarCheques(int id_rol, String username, String num_cuenta, String num_chequera,
            String num_cheque, String fecha_from, String fecha_to) throws SQLException {

        con = Conexion.getInstancia().Conectar(id_rol);
        cheques = new ArrayList<>();

        try {
            cst = con.prepareCall("{call ADMINISTRADOR.consulta_cheques(?,?,?,?,?,?,?,?)}");
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

    /*
    En este metodo se genera un cheque
     */
    public boolean generarCheque(int id_rol, String id_chequera,
            String direccion, double monto,
            String username, int id_proveedor) throws SQLException {

        boolean exito = false;

        int id_error = 0;
        String msgError = null;
        String num_cheque = null;
        int estado;
        con = Conexion.getInstancia().Conectar(id_rol);

        try {
            cst = con.prepareCall("{call ADMINISTRADOR.Generar_cheque(?,?,?,?,?,?,?,?,?,?)}");
            cst.setString(1, id_chequera);
            cst.setString(2, direccion);
            cst.setDouble(3, monto);
            cst.setInt(4, id_proveedor);
            cst.setString(5, username);
            cst.registerOutParameter(6, java.sql.JDBCType.INTEGER);
            cst.registerOutParameter(7, java.sql.JDBCType.INTEGER);
            cst.registerOutParameter(8, java.sql.JDBCType.VARCHAR);
            cst.registerOutParameter(9, java.sql.JDBCType.VARCHAR);
            cst.registerOutParameter(10, java.sql.JDBCType.INTEGER);

            cst.execute();

            int esExitoso = cst.getInt(6);
            //Si se obtiene un valor 0 en el parametro de salida de la DB
            //Se inserta el intento fallido en la Bitacora de operaciones fallidas
            if (esExitoso == 0) {
                id_error = cst.getInt(7);
                msgError = cst.getString(8);
                num_cheque = cst.getString(9);
                estado = cst.getInt(10);
                System.out.println(id_error + " " + msgError);

                cst = con.prepareCall("{call ADMINISTRADOR.insertar_operacion(?,?,?,?,?,?)}");
                cst.setString(1, username);
                cst.setString(2, num_cheque);
                cst.setInt(3, estado);
                cst.setInt(4, id_error);
                cst.setString(5, msgError);
                cst.setInt(6, 0);

                cst.execute();
                System.out.println("se hizo el execute en caso de fallo");
            } else {
                exito = true;
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            con.close();
            cst.close();
        }

        return exito;
    }

    /*Este metodo llama al prcedimiento modificar_cheque*/
    public boolean modificarCheque(int id_rol, String username, String id_cheque,
            double monto, String lugar, int id_proveedor) throws SQLException {
        boolean exito = false;
        con = Conexion.getInstancia().Conectar(id_rol);
        System.out.println("se hizo la instancia con");
        try {
            cst = con.prepareCall("{call ADMINISTRADOR.modificar_cheque(?,?,?,?,?,?,?,?,?,?)}");
            cst.setString(1, username);
            System.out.println(username);
            cst.setString(2, id_cheque);
            System.out.println(id_cheque);
            cst.setDouble(3, monto);
            System.out.println(monto);
            cst.setString(4, lugar);
            System.out.println(lugar);
            cst.setInt(5, id_proveedor);
            System.out.println(id_proveedor);
            cst.registerOutParameter(6, java.sql.JDBCType.INTEGER);
            cst.registerOutParameter(7, java.sql.JDBCType.INTEGER);
            cst.registerOutParameter(8, java.sql.JDBCType.VARCHAR);
            cst.registerOutParameter(9, java.sql.JDBCType.VARCHAR);
            cst.registerOutParameter(10, java.sql.JDBCType.INTEGER);

            cst.execute();

            int esExitoso = cst.getInt(6);
            /*EN CASO DE FALLAR LA MODIFICACION DEL CHEQUE, SE HACE UN INSERT EN LA BITACORA DE FALLOS*/

            if (esExitoso == 0) {
                int id_error = cst.getInt(7);
                String msgError = cst.getString(8);
                int estado = cst.getInt(10);

                System.out.println("El error es: " + id_error + " " + msgError);

                cst = con.prepareCall("{call ADMINISTRADOR.insertar_operacion(?,?,?,?,?,?)}");
                cst.setString(1, username);
                cst.setString(2, id_cheque);
                cst.setInt(3, estado);
                cst.setInt(4, id_error);
                cst.setString(5, msgError);
                cst.setInt(6, 0);

                cst.execute();

                System.out.println("se hizo el execute en caso de fallo");
            } else {
                exito = true;
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            cst.close();
            con.close();
        }
        return exito;
    }

    /*
        En este metdo se hace un update a un cheque de la tabla cheques
        para cambiar el estado del cheque a liberado
    */
    public boolean liberarCheque(int id_rol, String id_cheque, double monto, String username) throws SQLException {
        boolean exito = false;
        int id_error = 0;
        String msgError = null;
        String num_cheque = null;
        int estado;

        con = Conexion.getInstancia().Conectar(id_rol);

        try {
            cst = con.prepareCall("{call ADMINISTRADOR.liberar_cheque(?,?,?,?,?,?,?,?)}");
            cst.setString(1, id_cheque);
            cst.setDouble(2, monto);
            cst.setString(3, username);
            cst.registerOutParameter(4, java.sql.JDBCType.INTEGER);
            cst.registerOutParameter(5, java.sql.JDBCType.INTEGER);
            cst.registerOutParameter(6, java.sql.JDBCType.VARCHAR);
            cst.registerOutParameter(7, java.sql.JDBCType.VARCHAR);
            cst.registerOutParameter(8, java.sql.JDBCType.INTEGER);

            cst.execute();

            int esExitoso = cst.getInt(4);
            //En caso de fallo se hace insert en la bitacora de operaciones fallidos
            if (esExitoso == 0) {
                id_error = cst.getInt(5);
                msgError = cst.getString(6);
                num_cheque = cst.getString(7);
                estado = cst.getInt(8);
                System.out.println("Ha ocurrido un error ");
                System.out.println("ID Error :  " + id_error);
                System.out.println(msgError);

                cst = con.prepareCall("{call ADMINISTRADOR.insertar_operacion(?,?,?,?,?,?)}");
                cst.setString(1, username);
                cst.setString(2, num_cheque);
                cst.setInt(3, estado);
                cst.setInt(4, id_error);
                cst.setString(5, msgError);
                cst.setInt(6, 0);

                cst.execute();
                System.out.println("se hizo el execute en caso de fallo");

            } else {
                exito = true;
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            con.close();
            cst.close();
        }
        return exito;
    }

    public boolean anularCheque(int id_rol, String nombre_usuario, String num_cheque) throws SQLException {
        boolean exito = false;
        con = Conexion.getInstancia().Conectar(id_rol);
        int id_error;
        String msgError;
        String n_cheque;
        int status;

        try {
            cst = con.prepareCall("{call ADMINISTRADOR.anular_cheque(?,?,?,?,?,?,?)}");
            cst.setString(1, nombre_usuario);
            cst.setString(2, num_cheque);
            cst.registerOutParameter(3, java.sql.JDBCType.INTEGER);
            cst.registerOutParameter(4, java.sql.JDBCType.INTEGER);
            cst.registerOutParameter(5, java.sql.JDBCType.VARCHAR);
            cst.registerOutParameter(6, java.sql.JDBCType.VARCHAR);
            cst.registerOutParameter(7, java.sql.JDBCType.INTEGER);

            cst.execute();

            int esExitoso = cst.getInt(3);

            if (esExitoso == 0) {
                id_error = cst.getInt(4);
                msgError = cst.getString(5);
                n_cheque = cst.getString(6);
                status = cst.getInt(7);
                System.out.println(id_error + " " + msgError);

                cst = con.prepareCall("{call ADMINISTRADOR.insertar_operacion(?,?,?,?,?,?)}");
                cst.setString(1, nombre_usuario);
                cst.setString(2, n_cheque);
                cst.setInt(3, status);
                cst.setInt(4, id_error);
                cst.setString(5, msgError);
                cst.setInt(6, 0);

                cst.execute();
            } else {
                exito = true;
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            con.close();
            cst.close();
        }
        return exito;
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
