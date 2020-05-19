package umg.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import umg.modelo.LoginDao;
import umg.modelo.UsuarioDao;
import umg.negocio.EmpleadoDTO;
import umg.negocio.Login;

@WebServlet("/servEmpleados")

public class ServletEmpleados extends HttpServlet {
    
    UsuarioDao userDAO = new UsuarioDao();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String accion = req.getParameter("accion");
        
        switch (accion) {
            case "insertarEmpleado": {
                try {
                    this.insertarEmpleado(req, resp);
                } catch (SQLException ex) {
                    Logger.getLogger(ServletEmpleados.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            
        }
        
    }
    
    private void insertarEmpleado(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        UsuarioDao usarioDao = new UsuarioDao();
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
        Object[] objeto = usarioDao.insertarEmpleado(empDTO);
        boolean insertado = (boolean) objeto[0];
        if (insertado == true) {
            resp.sendRedirect("form_agregar_usuario.jsp");
        }
        
    }
    
}
