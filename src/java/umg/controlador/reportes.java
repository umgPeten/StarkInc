package umg.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;
import umg.modelo.ReportesDAO;
import umg.negocio.Cheque;

/**
 *
 * @author mikesb
 */
@WebServlet("/reportes")
public class reportes extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        try {
            switch (accion) {
                case "busqueda_fechas":
                {
                    try {
                        this.busquedaPorFechainsertarEmpleado(req, resp);
                    } catch (ParseException ex) {
                        Logger.getLogger(reportes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;


            }
        } catch (SQLException ex) {
            printStackTrace(ex);
        }

    }

    private void busquedaPorFechainsertarEmpleado(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ParseException, ServletException {
        int id_rol = Integer.parseInt(req.getParameter("id_rol"));
        String username = req.getParameter("username");
        String fecha_inicio = req.getParameter("fecha_inicio");
        String fecha_fin = req.getParameter("fecha_fin");
        String num_cuenta = req.getParameter("num_cuenta");
        
        ArrayList<Cheque> cheques = new ReportesDAO().consultarCheques(id_rol, username, num_cuenta, null, null, fecha_inicio, fecha_fin); 
       req.setAttribute("cheques", cheques);
       req.getRequestDispatcher("consultacheques.jsp").forward(req, resp);

    }

}
