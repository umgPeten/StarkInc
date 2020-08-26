/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umg.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import umg.modelo.ChequeraDAO;
import umg.modelo.ChequesDAO;
import umg.modelo.ProveedorDAO;
import umg.modelo.ReportesDAO;
import umg.negocio.Cheque;
import umg.negocio.Chequera;
import umg.negocio.Proveedor;

/**
 *
 * @author mikesb
 */
@WebServlet("/cheques")
public class ServletCheques extends HttpServlet {

    private ChequeraDAO chequeraDAO = new ChequeraDAO();
    private Chequera chequera = new Chequera();
    private ChequesDAO chequesDAO;
    private ArrayList<Cheque> cheques;
    private Cheque cheque = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        System.out.println(accion);
        System.out.println(accion);
        try {
            switch (accion) {

                case "ver_cheques":
                    this.ver_cheques(req, resp);
                    break;

                case "generar_cheque":
                    this.generar_chequeGET(req, resp);
                    break;

                case "modificar_cheque":
                    this.modificar_chequeGET(req, resp);
                    break;

                case "ver_cheque":
                    this.ver_cheque(req, resp);
                    break;
                case "anular_cheque":
                    this.anularChequeGET(req, resp);
                    break;
                case "imprimir_cheque":
                    this.imprimirChequeGET(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            resp.sendError(resp.SC_NOT_ACCEPTABLE, ex.getSQLState());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accion = req.getParameter("accion");
        System.out.println(accion);

        try {
            switch (accion) {

                case "agregarChequera":
                    this.agregarChequera(req, resp);
                    break;

                case "ingresar_cheque":
                    this.generar_chequePOST(req, resp);
                    break;

                case "modificar_cheque":
                    this.modificar_chequePOST(req, resp);
                    break;

                case "liberar_cheque":
                    this.liberarCheque(req, resp);
                    break;

                case "anular_cheque":
                    this.anularChequePOST(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServletCheques.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarChequera(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {

        try {
            int id_rol_user = Integer.parseInt(req.getParameter("id_rol"));
            chequera.setId(req.getParameter("id_chequera"));
            System.out.println(chequera.getId());
            String inicioSTR = req.getParameter("inicio");

            chequera.setInicio(Integer.parseInt(inicioSTR));
            System.out.println(chequera.getInicio());
            String finSTR = req.getParameter("fin");
            chequera.setFin(Integer.parseInt(finSTR));
            System.out.println(chequera.getFin());
            String cuenta = req.getParameter("num_cuenta");

            try {
                if (chequeraDAO.insertarChequera(chequera, cuenta, id_rol_user)) {
                    System.out.println("se agrego correctamente");

                    resp.sendRedirect("chequeras.jsp");
                } else {
                    System.out.println("error");

                    resp.sendRedirect("chequeras.jsp");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                ex.getErrorCode();
            }
        } catch (NumberFormatException ex) {
            ex.getMessage();

            resp.sendRedirect("chequeras.jsp");
            resp.getOutputStream().print("<script type=\"text/javascript\">alert(\"Ingrese un valor valido entero, porfavor\")</script>");
        }

    }

    private void generar_chequeGET(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException {
        String id_chequera = req.getParameter("id_chequera");
        int id_rol = Integer.parseInt(req.getParameter("id_rol"));
        ArrayList<Chequera> chequera = new ChequeraDAO().getChequera(id_rol, id_chequera, null);
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        ArrayList<Proveedor> proveedores = proveedorDAO.getProveedores(id_rol, null);
        System.out.println(proveedores.size());
        this.chequera = chequera.get(0);
        if (this.chequera.getStock() > 0) {
            req.setAttribute("chequera", this.chequera);
            req.setAttribute("proveedores", proveedores);
            req.getRequestDispatcher("/paginas/usuarios/generarcheque.jsp").forward(req, resp);

        } else {
            resp.sendError(resp.SC_CONFLICT, "Ya no hay cheques disponibles, pruebe con otra chequera");
        }

    }

    private void ver_cheque(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException {
        String id_cheque = req.getParameter("id_cheque");
        String username = req.getParameter("username");
        int id_rol = Integer.parseInt(req.getParameter("id_rol"));

        ArrayList<Cheque> cheques = new ChequesDAO().consultarCheques(id_rol, username, null, null, id_cheque, null, null);
        if (cheques.size() > 0) {
            Cheque cheque = cheques.get(0);
            req.setAttribute("cheque_liberar", cheque);
            req.getRequestDispatcher("/paginas/usuarios/vercheque.jsp").forward(req, resp);
        }

    }

    private void ver_cheques(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException {
        cheques = new ArrayList<>();
        chequesDAO = new ChequesDAO();
        String id_chequera = req.getParameter("id_chequera");
        int id_rol = Integer.parseInt(req.getParameter("id_rol"));
        String username = req.getParameter("usuario");

        cheques = new ReportesDAO().consultarCheques(id_rol, username, null, id_chequera, null, null, null);
        req.setAttribute("cheques", cheques);
        req.getRequestDispatcher("/paginas/usuarios/vercheques.jsp").forward(req, resp);
    }

    private void generar_chequePOST(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException {
        String id_chequera = req.getParameter("id_chequera");
        String lugar = req.getParameter("lugar");
        String username = req.getParameter("username");
        int id_proveedor = Integer.parseInt(req.getParameter("proveedor"));
        int id_rol = Integer.parseInt(req.getParameter("id_rol"));
        double monto = Double.parseDouble(req.getParameter("monto"));

        System.out.println(id_chequera + " " + lugar + " " + monto + " " + id_proveedor + " " + id_rol + " " + username);

        boolean exito = new ChequesDAO().generarCheque(id_rol, id_chequera, lugar, monto, username, id_proveedor);
        System.out.println("El cheque se modifico " + exito);
        if (exito) {
            resp.sendRedirect("inicio.jsp");
        } else {
            System.out.println("Error al Generar el cheque");
            resp.sendError(resp.SC_BAD_REQUEST, "Error al Generar el cheque");
        }

    }

    private void modificar_chequeGET(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException {
        String id_cheque = req.getParameter("id_cheque");
        double monto = Double.parseDouble(req.getParameter("monto"));
        String lugar = req.getParameter("lugar");
        String numero_cuenta = req.getParameter("num_cuenta");
        String banco = req.getParameter("banco");
        String id_chequera = req.getParameter("id_chequera");
        cheque = new Cheque();
        cheque.setLugar(lugar);
        cheque.setId(id_cheque);
        cheque.setMonto(monto);
        req.setAttribute("id_chequera", id_chequera);
        req.setAttribute("banco", banco);
        req.setAttribute("num_cuenta", numero_cuenta);
        req.setAttribute("cheque_modificar", cheque);
        req.getRequestDispatcher("/paginas/usuarios/modificarcheque.jsp").forward(req, resp);

    }

    private void modificar_chequePOST(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException {
        int id_rol = Integer.parseInt(req.getParameter("id_rol"));
        String username = req.getParameter("username");
        String num_cheque = req.getParameter("id_cheque");
        double monto = Double.parseDouble(req.getParameter("monto"));
        String lugar = req.getParameter("lugar");
        int id_proveedor = Integer.parseInt(req.getParameter("proveedor"));

        boolean exito = new ChequesDAO().modificarCheque(id_rol, username, num_cheque, monto, lugar, id_proveedor);
        if (exito) {
            resp.sendRedirect("inicio.jsp");
        } else {
            resp.sendError(resp.SC_CONFLICT, "Ocurrio un error a la hora de modificar el cheque, acuda al jefe de pagos si el problema persiste");
        }

    }

    private void liberarCheque(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException {
        String num_cheque = req.getParameter("id_cheque");
        double monto = Double.parseDouble(req.getParameter("monto"));
        String username = req.getParameter("username");
        int id_rol = Integer.parseInt(req.getParameter("id_rol"));

        boolean exito = new ChequesDAO().liberarCheque(id_rol, num_cheque, monto, username);

        if (exito) {
            resp.sendRedirect("inicio.jsp");
    
        } else {
            resp.sendError(resp.SC_BAD_REQUEST, "Ocurrio un error a la hora de liberar, vuelva a intentarlo mas tarde");

        }

    }

    private void anularChequeGET(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException {
        cheques = new ArrayList<>();
        chequesDAO = new ChequesDAO();

        String id_cheque = req.getParameter("id_cheque");
        int id_rol = Integer.parseInt(req.getParameter("id_rol"));
        String username = req.getParameter("username");

        cheques = chequesDAO.consultarCheques(id_rol, username, null, null, id_cheque, null, null);

        Cheque cheque = cheques.get(0);
        req.setAttribute("cheque_anular", cheque);
        req.getRequestDispatcher("/paginas/usuarios/anularcheque.jsp").forward(req, resp);

    }

    private void anularChequePOST(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException {
        String id_cheque = req.getParameter("id_cheque"); 
       int id_rol = Integer.parseInt(req.getParameter("id_rol"));
        String username = req.getParameter("username");
        PrintWriter out = resp.getWriter();
        boolean exito = new ChequesDAO().anularCheque(id_rol, username, id_cheque);

        if (exito) {
            resp.sendRedirect("inicio.jsp");
        } else {
            out.print("<script>"
                    + "alert()0;"
                    + "</script>");
            resp.sendRedirect("inicio.jsp");

        }

    }
    
    private void imprimirChequeGET(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException {
        cheques = cheques = new ArrayList<>();
        chequesDAO = new ChequesDAO();
        
        String id_cheque = req.getParameter("id_cheque");
        int id_rol = Integer.parseInt(req.getParameter("id_rol"));
        String username = req.getParameter("username");
        System.out.println(id_cheque + id_rol +  username);
        
        cheques = chequesDAO.consultarCheques(id_rol, username, null, null, id_cheque, null, null);

        Cheque cheque = cheques.get(0);
        req.setAttribute("cheque_imprimir", cheque);
        req.getRequestDispatcher("/paginas/usuarios/imprimircheque.jsp").forward(req, resp);
        
        
    }

}
