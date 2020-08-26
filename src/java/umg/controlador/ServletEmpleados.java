package umg.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
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

        try {
            String accion = req.getParameter("accion");
            System.out.println(accion);

            switch (accion) {
                case "insertarEmpleado":
                    this.insertarEmpleado(req, resp);
                    break;

                case "insertarUsuario":
                    this.insertarUsuario(req, resp);
                    break;

                case "editar_usuario":
                    this.guardarUsuario(req, resp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServletEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accion = req.getParameter("accion");
        System.out.println(accion);
        if (accion != null) {

            switch (accion) {
                case "editar": {
                    try {
                        this.editarUsuario(req, resp);
                    } catch (SQLException ex) {
                        Logger.getLogger(ServletEmpleados.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }
        }
    }

    private void insertarEmpleado(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id_rol = Integer.parseInt(req.getParameter("id_rol"));

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
        Object[] objeto = userDAO.insertarEmpleado(empDTO, id_rol);
        boolean insertado = (boolean) objeto[0];
        if (insertado == true) {
            resp.sendRedirect("form_agregar_usuario.jsp");
        }

    }

    private void insertarUsuario(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id_rol_user = Integer.parseInt(req.getParameter("id_rol"));

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

        if (userDAO.insertarUsuario(userDTO, id_rol_user)) {

            resp.sendRedirect("empleados.jsp");

        } else {
            System.out.println("no se agrego");
            out.print("<script type=\"text/javascript\">"
                    + "alert(\"Ha ocurrido un error\");"
                    + "</script>");
            System.out.println("error al agregar usuario");
            resp.sendRedirect("empleados.jsp");
        }

    }

    private void editarUsuario(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException {
        int id_rol = Integer.parseInt(req.getParameter("id_rol"));
        String username = req.getParameter("usuario");
        System.out.println(id_rol);

        System.out.println(username);
        UsuarioDTO usuarioDTO = UsuarioDao.getInstancia().get_usuario(username, 2, id_rol);
        usuarioDTO.setUsernamae(username);
        System.out.println(usuarioDTO.toString());
        req.setAttribute("usuario_edit", usuarioDTO);
        req.getRequestDispatcher("/paginas/usuarios/editarUsuario.jsp").forward(req, resp);

    }

    private void guardarUsuario(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {

        int id_rol = Integer.parseInt(req.getParameter("id_rol"));

        String usuario = req.getParameter("usuario");
        String montoMaxSTR = req.getParameter("monto_maximo");
        String montoMinSTR = req.getParameter("monto_minimo");
        String rolSTR = req.getParameter("rol");

        int montoMax = Integer.parseInt(montoMaxSTR);
        int montoMin = Integer.parseInt(montoMinSTR);
        int rol = Integer.parseInt(rolSTR);

        userDTO = new UsuarioDTO();
        userDTO.setUsernamae(usuario);
        userDTO.setMontoMaximo(montoMax);
        userDTO.setMontoMinimo(montoMin);
        userDTO.setId_rol(rol);

        boolean rs = UsuarioDao.getInstancia().editar_usuario(userDTO, id_rol);
        
        if(rs == true){
            System.out.println("Usuario Editado exitosamente");
            resp.sendRedirect("inicio.jsp");
            
        }else{
            System.out.println("Error al editar el usuario");
            resp.sendError(resp.SC_BAD_REQUEST);
        }

    }

}
