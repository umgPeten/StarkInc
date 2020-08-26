<%-- 
    Document   : menu
    Created on : 17-may-2020, 16:53:10
    Author     : mikesb
--%>


<%@page import="umg.modelo.UsuarioDao"%>
<%@page import="umg.negocio.UsuarioDTO"%>

<%!
    
    public UsuarioDTO usuarioDTO = null;
    HttpSession sesion = null;
    public int id_rol;
    String username;

%>

<%
    
    this.sesion = request.getSession(false);
    if (sesion.getAttribute("usuario_log") == null) {
        response.sendRedirect("login.jsp");
    } else {
        usuarioDTO = (UsuarioDTO) sesion.getAttribute("usuario_log");
        id_rol = usuarioDTO.getId_rol();
        System.out.println("Hola mi id es " + id_rol);
        username = usuarioDTO.getUsernamae();
    }

%>



<nav class="navbar">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="javascript:void(0);" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false"></a>
            <a href="javascript:void(0);" class="bars"></a>
            <a class="navbar-brand" href="index.html">STARK INCORPORATION</a>
        </div>
        <ul class="nav navbar-nav navbar-right">

            <div class="btn-group user-helper-dropdown">
                <i class="material-icons" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">keyboard_arrow_down</i>
                <ul class="dropdown-menu pull-right">
                    
                    <li><a href="<%=request.getContextPath()%>/login?accion=logout" class=" waves-effect waves-block"><i class="material-icons">input</i>Sign Out</a></li>
                </ul>
            </div>
        </ul>
        </li>
        

    </div>
</nav>

<section>
    <aside id="leftsidebar" class="sidebar">
        <div class="user-info">
            <div class="image">
                <img src="img/user.png" width="48" height="48" alt="User" />
            </div>
            <div class="info-container">
                <div class="name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><%out.print(usuarioDTO.getFullname());%></div>
                <div  class="username"><%out.print(usuarioDTO.getUsernamae());%></div>
                <div  class="rol"><%out.print(usuarioDTO.getRol());%></div>

                <p class="invisible" id="rol" ><%out.println(usuarioDTO.getId_rol());%></p>
            </div>
        </div>

        <div class="menu">
            <ul class="list">
                <li class="header">MENÚ DE NAVEGACIÓN</li>

                <li>
                    <a href="inicio.jsp">
                        <i class="material-icons">home</i>
                        <span>Inicio</span>
                    </a>
                </li>

                <li id="li_empleados">
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">person</i>
                        <span>Empleados</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="usuarios.jsp">Usuarios</a>
                        </li>
                        <li>
                            <a <a href="empleados.jsp">Empleados</a>
                        </li>
                        <li>
                            <a href="form_agregar_usuario.jsp">Agregar Empleado</a>
                        </li>
                    </ul>
                </li>

                <li id="li_cheques">
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">local_atm</i>
                        <span>Cheques</span>
                    </a>
                    <ul class="ml-menu">
                        <li id="imprimir_cheque">
                            <a href="imprimir.jsp">Imprimir Cheque</a>
                        </li>

                        <li id="bandeja_cheque">
                            <a href="bandeja.jsp">Bandeja</a>
                        </li>

                        <li id="nuevo_cheque">
                            <a href="nuevocheque.jsp">Nuevo</a>
                        </li>
                        <li id="estado_cheque">
                            <a href="#">Estados</a>
                        </li>
                        <li id="ver_chequeras">
                            <a href="chequeras.jsp">Chequeras</a>
                        </li>
                    </ul>
                </li>

                <li id="li_banco">
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">account_balance</i>
                        <span>Banco</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="#">Bancos</a>
                        </li>
                        <li>
                            <a href="cuentas.jsp">Cuentas</a>
                        </li>
                    </ul>
                </li>

                <li id="li_proveedores">
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">airport_shuttle</i>
                        <span>Proveedores</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="#">Proveedores</a>
                        </li>
                        <li>
                            <a href="#">Nuevo</a>
                        </li>
                        <li>
                            <a href="#">Contactos</a>
                        </li>
                    </ul>
                </li>


                <li id="li_reportes">
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">description</i>
                        <span>Reportes</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="consultacheques.jsp">Consulta de Cheques</a>
                        </li>
                        <li>
                            <a href="#">Cheques liberados por grupo</a>
                        </li>
                        <li>
                            <a href="#">Bitacora</a>
                        </li>
                        <li>
                            <a href="#">Movimientos</a>
                        </li>
                    </ul>
                </li>

            </ul>
        </div>


        <div class="legal">
            <div class="copyright">
                © 2020<a href="https://umg.edu.gt/"> Universidad Mariano Galvez</a>.
            </div>
            <div class="version">
                <b>Base de Datos II </b>
            </div>

        </div>
    </aside>

</section>