
import java.sql.Connection;
import java.sql.SQLException;
import umg.modelo.Conexion;
import umg.modelo.LoginDao;
import umg.negocio.Login;


public class prueba {
    
       public static void main(String[] args) throws SQLException {
        
           LoginDao logindao = new LoginDao();
           Login login = new Login("asdasd", "asdasd");
           System.out.println(logindao.validate(login));
           
    
           
       
       }
    
}
