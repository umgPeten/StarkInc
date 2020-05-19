
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import umg.modelo.Conexion;
import umg.modelo.LoginDao;
import umg.modelo.PuestosDAO;
import umg.modelo.UsuarioDao;
import umg.negocio.Departamento;
import umg.negocio.Empleado;
import umg.negocio.EmpleadoDTO;
import umg.negocio.Identificacion;
import umg.negocio.Login;
import umg.negocio.Puesto;


public class prueba {
    
       public static void main(String[] args) throws SQLException {
           
           Connection con = Conexion.getInstancia().Conectar();
           System.out.println("conectado a la asdasdsd");
          Departamento departamento;
          ArrayList<Departamento> departamentos = new ArrayList<>();
          PuestosDAO puestDAO = new PuestosDAO();
          departamentos = puestDAO.getDepartamentos();
          Iterator it = departamentos.iterator();
          
          while(it.hasNext()){
              departamento = (Departamento)it.next();
              System.out.println(departamento.toString());
              
          }
           System.out.println("HOLA");
          Puesto puesto;
          ArrayList<Puesto> puestos = new ArrayList<>();
          puestos = puestDAO.getPuestos();
          Iterator itpuestos = puestos.iterator();
          while(it.hasNext()){
              System.out.println("hola");
              puesto = (Puesto) itpuestos.next();
              System.out.println(puesto.toString());
          }
           System.out.println("final");
       }
    
}
