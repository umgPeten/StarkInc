
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import umg.modelo.ChequeraDAO;
import umg.modelo.ChequesDAO;
import umg.modelo.PuestosDAO;
import umg.modelo.UsuarioDao;
import umg.negocio.Chequera;
import umg.negocio.Rol;
import umg.negocio.UsuarioDTO;

public class prueba {

    public static void main(String[] args) throws SQLException {

        boolean exito;
        ChequesDAO chequeDAO = new ChequesDAO();
        double monto = 4500.0;
        System.out.println(chequeDAO.liberarCheque(2, "78901255", monto, "hestelag"));
    }
}
