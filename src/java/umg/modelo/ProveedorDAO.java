package umg.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import umg.negocio.Proveedor;

/**
 *
 * @author mikesb
 */
public class ProveedorDAO {
    
    private CallableStatement cs;
    private Connection con;
    private ResultSet rs;
    private static ProveedorDAO instancia = null;
    ArrayList<Proveedor> proveedores = null;
    Proveedor proveedor = null;

    public static ProveedorDAO getInstancia() {
        if (instancia != null) {
            instancia = new ProveedorDAO();
        }
        return instancia;
    }
    
    /*
        metodo para obtener proveedores
    */
    public ArrayList<Proveedor> getProveedores(int id_rol, String nombreProveedor) throws SQLException{
        con = Conexion.getInstancia().Conectar(id_rol);
        proveedores = new ArrayList<>();
        try{
            cs = con.prepareCall("{call ADMINISTRADOR.get_proveedores(?,?)}");
            cs.registerOutParameter(1, java.sql.JDBCType.REF_CURSOR);
            cs.setString(2, nombreProveedor);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);
            
            while(rs.next()){
                proveedor = new Proveedor();
                proveedor.setId(rs.getInt(1));
                proveedor.setNombre(rs.getString(2));
                proveedor.setDireccion(rs.getString(3));
                proveedor.setTelefono(rs.getString(4));
                proveedores.add(proveedor);
            }
            
            
        }catch(SQLException ex){
            printSQLException(ex);
        }finally{
           
        }
        
        return proveedores;
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
