package umg.controlador;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import umg.modelo.LoginDao;
import umg.modelo.UsuarioDao;
import static umg.modelo.UsuarioDao.GET_USER;
import umg.negocio.Login;
import umg.negocio.UsuarioDTO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    LoginDao logindao;
    UsuarioDao userDAO;

    public void init() {
        logindao = new LoginDao();
        userDAO = new UsuarioDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        
        switch(accion){
            case "logout":
                req.getSession().invalidate();
                resp.sendRedirect("login.jsp");
                break;
            
        }
    }
    
    
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Login login = new Login(username, password);

        try {
            if (logindao.validate(login)) {
                UsuarioDTO userDTO = UsuarioDao.getInstancia().get_usuario(username, GET_USER, 7);
                HttpSession session = req.getSession(true);
                session.setAttribute("usuario_log", userDTO);
                HttpSession id_rol = req.getSession(true);
                int id_rol_usuario = userDTO.getId_rol();
                System.out.println(id_rol_usuario);
                id_rol.setAttribute("id_rol_usuario", id_rol_usuario);

                resp.sendRedirect("inicio.jsp");

            } else {

                resp.sendRedirect("login.jsp");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getErrorCode();
        }

    }

}
