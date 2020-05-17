
import java.sql.Connection;
import java.sql.SQLException;
import umg.modelo.Conexion;
import umg.modelo.LoginDao;


public class prueba {
    
       public static void main(String[] args) throws SQLException {
        
           LoginDao logindao = new LoginDao();
           boolean esLogueado = logindao.validate("mgongasdasdora", "admin");
           System.out.println(esLogueado);
    
       }
    
}
