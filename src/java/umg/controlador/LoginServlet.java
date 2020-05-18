package umg.controlador;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import umg.modelo.LoginDao;
import umg.negocio.Login;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    LoginDao logindao;

    public void init() {
        logindao = new LoginDao();
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Login login = new Login(username, password);

        try {
            if (logindao.validate(login)) {

                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                resp.sendRedirect("inicio.jsp");

            } else {
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                resp.sendRedirect("login.jsp");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getErrorCode();
        }

    }

}
