<%-- 
    Document   : menu
    Created on : 17-may-2020, 16:53:10
    Author     : mikesb
--%>
<nav class="navbar">
        <div class="container-fluid">
            <div class="navbar-header">
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
                    <div class="name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Miguel Gongora
                    </div>
                    <div class="username">miguelgongora12@gmail.com</div>
                    <div class="rol">Administrador</div>
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
                            <span>Usuarios</span>
                        </a>
                        <ul class="ml-menu">
                            <li class="active">
                                <a href="#">Usuarios</a>
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
                                <a href="#">Cheques en cola</a>
                            </li>
                            <li>
                                <a href="#">Nuevo</a>
                            </li>
                            <li>
                                <a href="#">Estados</a>
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