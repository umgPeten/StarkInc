/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umg.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import umg.modelo.ChequeraDAO;
import umg.negocio.Chequera;

/**
 *
 * @author mikesb
 */
@WebServlet("/cheques")
public class ServletCheques extends HttpServlet {

    private ChequeraDAO chequeraDAO = new ChequeraDAO();
    private Chequera chequera = new Chequera();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accion = req.getParameter("accion");
        System.out.println(accion);
        switch (accion) {

            case "agregarChequera": {
                System.out.println("caso agregar chequera");
                try {
                    this.agregarChequera(req, resp);
                } catch (SQLException ex) {
                    Logger.getLogger(ServletCheques.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            break;

        }

    }

    private void agregarChequera(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        System.out.println("agregando cheque");
        chequera.setId(req.getParameter("id_chequera"));
        System.out.println(chequera.getId());
        String inicioSTR = req.getParameter("inicio");

        chequera.setInicio(Integer.parseInt(inicioSTR));
        System.out.println(chequera.getInicio());
        String finSTR = req.getParameter("fin");
        chequera.setFin(Integer.parseInt(finSTR));
        System.out.println(chequera.getFin());
        String cuenta = req.getParameter("num_cuenta");
        System.out.println(cuenta);
        try {
            if (chequeraDAO.insertarChequera(chequera, cuenta)) {
                System.out.println("se agrego correctamente");
                resp.getOutputStream().print("<script type=\"text/javascript\">alert(\"Chequera ingresada correctamente\")</script>");
                resp.sendRedirect("chequeras.jsp");
            } else {
                System.out.println("error");
                resp.getOutputStream().print("<script type=\"text/javascript\">alert(\"Ha ocurrido un error, intentelo mas tarde\")</script>");
                resp.sendRedirect("chequeras.jsp");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getErrorCode();
        }

    }

}
