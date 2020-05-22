
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import umg.modelo.PuestosDAO;
import umg.modelo.UsuarioDao;
import umg.negocio.Rol;
import umg.negocio.UsuarioDTO;

public class prueba {

    public static void main(String[] args) throws SQLException {
        ArrayList<UsuarioDTO> usuarios = UsuarioDao.getInstancia().get_usuarios();
        Iterator iterador = usuarios.iterator();
        UsuarioDTO userDTO;
        while(iterador.hasNext()){
            userDTO = (UsuarioDTO) iterador.next();
            usuarios.add(userDTO);
            System.out.println(userDTO.toString());
        }
                
    }
}
