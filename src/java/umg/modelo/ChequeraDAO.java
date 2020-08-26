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

/*
    En este clase es la que se encarga de ejecutar los procedimientos almacenados
    relacionados con una chequera
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

    /*
        Metodo para insertar una chequera
     */
    public boolean insertarChequera(Chequera chequera, String cuenta, int id_rol) throws SQLException {
        con = Conexion.getInstancia().Conectar(id_rol);
        boolean insertado = false;
        int exito = 0;
        try {

            cst = con.prepareCall("{call ADMINISTRADOR.insertar_chequera(?,?,?,?,?)}");
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

    /*
        Metodo para consultar las chequeras
     */
    public ArrayList<Chequera> getChequera(int id_rol, String id_chequera, String numero_cuenta) throws SQLException {
        con = Conexion.getInstancia().Conectar(id_rol);
        ArrayList<Chequera> chequeras = new ArrayList<>();
        Cuenta cuenta;
        Chequera chequera;
        Banco banco;
        System.out.println("toy aca");
        try {
            cst = con.prepareCall("{call ADMINISTRADOR.get_chequeras(?,?,?)}");
            cst.registerOutParameter(1, java.sql.JDBCType.REF_CURSOR);
            cst.setString(2, id_chequera);
            cst.setString(3, numero_cuenta);
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
                System.out.println(chequera);
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
