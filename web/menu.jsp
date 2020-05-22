<%-- 
    Document   : menu
    Created on : 17-may-2020, 16:53:10
    Author     : mikesb
--%>

<%@page import="umg.modelo.UsuarioDao"%>
<%@page import="umg.negocio.UsuarioDTO"%>
<%

    HttpSession sesion = request.getSession(false);

    String username = (String) sesion.getAttribute("usuario");
    if (username == null) {
        response.sendRedirect("login.jsp");

    }
    UsuarioDao userDAO = new UsuarioDao();
    UsuarioDTO usuario = userDAO.get_usuario(username);
%>




<nav class="navbar">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="javascript:void(0);" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false"></a>
            <a href="javascript:void(0);" class="bars"></a>
            <a class="navbar-brand" href="index.html">STARK INCORPORATION</a>
        </div>
        
    </div>
</nav>

<section>
    <aside id="leftsidebar" class="sidebar">
        <div class="user-info">
            <div class="image">
                <img src="img/user.png" width="48" height="48" alt="User" />
            </div>
            <div class="info-container">
                <div class="name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><%out.print(usuario.getFullname());%></div>
                <div class="username"><%out.print(usuario.getUsernamae());%></div>
                <div class="rol"><%out.print(usuario.getRol());%></div>
            </div>
        </div>

        <div class="menu">
            <ul class="list">
                <li class="header">MENÚ DE NAVEGACIÓN</li>

                <li>
                    <a href="../../index.html">
                        <i class="material-icons">home</i>
                        <span>Inicio</span>
                    </a>
                </li>

                <li class="active">
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">person</i>
                        <span>Empleados</span>
                    </a>
                    <ul class="ml-menu">
                        <li class="active">
                            <a href="#">Submenú</a>
                        </li>
                        <li>
                            <a href="usuarios.jsp">Usuarios</a>
                        </li>
                        <li>
                            <a href="empleados.jsp">Empleados</a>
                        </li>
                        <li>
                            <a href="#">Contactos</a>
                        </li>

                        <li>
                            <a href="form_agregar_usuario.jsp">Agregar Nuevo</a>
                        </li>
                    </ul>
                </li>

                <li class="active">
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">local_atm</i>
                        <span>Cheques</span>
                    </a>
                    <ul class="ml-menu">
                        <li class="active">
                            <a href="#">SubMenu</a>
                        </li>
                        <li>
                            <a href="nuevocheque.jsp">Nuevo</a>
                        </li>
                        <li>
                            <a href="#">Estados</a>
                        </li>
                        <li>
                            <a href="chequeras.jsp">Chequeras</a>
                        </li>
                    </ul>
                </li>

                <li class="active">
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">account_balance</i>
                        <span>Banco</span>
                    </a>
                    <ul class="ml-menu">
                        <li class="active">
                            <a href="#">Menu</a>
                        </li>
                        <li>
                            <a href="#">Bancos</a>
                        </li>
                        <li>
                            <a href="cuentas.jsp">Cuentas</a>
                        </li>
                    </ul>
                </li>



                <li class="active">
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">airport_shuttle</i>
                        <span>Proveedores</span>
                    </a>
                    <ul class="ml-menu">
                        <li class="active">
                            <a href="#">Ver Proveedores</a>
                        </li>
                        <li>
                            <a href="#">Nuevo</a>
                        </li>
                        <li>
                            <a href="#">Contactos</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>

    </aside>

</section>