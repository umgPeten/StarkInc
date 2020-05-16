
import java.sql.Connection;
import umg.modelo.Conexion;


public class prueba {
       public static void main(String[] args) {
        
           Connection con = Conexion.getInstancia().Conectar();
    
       }
    
}
