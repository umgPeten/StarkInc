package umg.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import umg.modelo.UsuarioDao;
import umg.negocio.EmpleadoDTO;
import umg.negocio.UsuarioDTO;

@WebServlet("/servEmpleados")

public class ServletEmpleados extends HttpServlet {

    UsuarioDao userDAO = new UsuarioDao();
    UsuarioDTO userDTO;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String accion = req.getParameter("accion");
        System.out.println(accion);
        switch (accion) {
            case "insertarEmpleado": {
                try {
                    this.insertarEmpleado(req, resp);
                } catch (SQLException ex) {
                    Logger.getLogger(ServletEmpleados.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "insertarUsuario": {
                try {
                    this.insertarUsuario(req, resp);
                } catch (SQLException ex) {
                    ex.getErrorCode();
                    ex.getMessage();
                }

                break;
            }

        }

    }

    private void insertarEmpleado(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {

        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String telefono = req.getParameter("telefono");
        String direccion = req.getParameter("direccion");
        String identificacionSTR = req.getParameter("identificacion");
        int identificacion = Integer.parseInt(identificacionSTR);
        String tipoDocumentoSTR = req.getParameter("tipo_documento");
        int id_tipo = Integer.parseInt(tipoDocumentoSTR);
        String fecha_nac = req.getParameter("fecha_nac");
        String generoSTR = req.getParameter("radioGenero");
        int genero = Integer.parseInt(generoSTR);
        String departamentoSTR = req.getParameter("departamento");
        int departamento = Integer.parseInt(departamentoSTR);
        String puestoSTR = req.getParameter("puesto");
        int puesto = Integer.parseInt(puestoSTR);

        EmpleadoDTO empDTO = new EmpleadoDTO(identificacion, id_tipo, nombre, apellido, telefono, direccion, genero, puesto, fecha_nac);
        Object[] objeto = userDAO.insertarEmpleado(empDTO);
        boolean insertado = (boolean) objeto[0];
        if (insertado == true) {
            resp.sendRedirect("form_agregar_usuario.jsp");
        }

    }

    private void insertarUsuario(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        userDTO = new UsuarioDTO();
        System.out.println("empezando a insertar");
        String id_empleadoSTR = req.getParameter("id_empleado");
        int id_empleadoINT = Integer.parseInt(id_empleadoSTR);
        
        
        
        System.out.println(id_empleadoINT);
        String id_rol = req.getParameter("rol");
        int id_rolINT = Integer.parseInt(id_rol);
        System.out.println(id_empleadoINT);
        String username = req.getParameter("username");
        String montoMaxSTR = req.getParameter("monto_maximo");
        int montoMax = Integer.parseInt(montoMaxSTR);
        System.out.println(montoMax);
        String montoMinSTR = req.getParameter("monto_minimo");
        int montoMin = Integer.parseInt(montoMinSTR);
        System.out.println(montoMin);
        
        
        
        
        
        userDTO.setId_rol(id_rolINT);
        userDTO.setId_empleado(id_empleadoINT);
        userDTO.setUsernamae(username);
        userDTO.setMontoMaximo(montoMax);
        userDTO.setMontoMinimo(montoMin);
        PrintWriter out = resp.getWriter();

        if (userDAO.insertarUsuario(userDTO)) {
            System.out.println("Agregado satisfactoriamente");
            out.print("<script type=\"text/javascript\" src=\"javascript.js\">"
                    + "alert(\"Agregado satisfactoriamente\")"
                    + "</script>");

            resp.sendRedirect("empleados.jsp");

        } else {
            System.out.println("no se agrego");
            out.print("<script type=\"text/javascript\" src=\"javascript.js\">"
                    + "alert(\"Ha ocurrido un error\")"
                    + "</script>");
        }

    }

}
