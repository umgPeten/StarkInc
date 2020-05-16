<%-- 
    Document   : login
    Created on : 14-may-2020, 12:45:31
    Author     : mikesb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset='utf-8'>
        <meta http-equiv='X-UA-Compatible' content='IE=edge'>
        <title>Stark Inc - Login</title>
        <meta name='viewport' content='width=device-width, initial-scale=1'>
        <link rel='stylesheet' type='text/css' media='screen' href='estilos.css'>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src='scripts.js'></script>
    </head>
    <body>

        <header class="header-inicio">
            <div class="logo-header">
                <a href="index.html">Stark Inc</a>
            </div>

            <div class="header-nav">
                <nav>
                    <a href="index.html">Inicio</a>
                    <a href="#">Acerca de Nosotros</a>
                    <a href="#">Contactanos</a>
                    <a href="login.html">Iniciar Sesión</a>
                </nav>
            </div>

        </header>


        <div class="principal2">
            <div class="form-inicio">
                <aside>
                    <div class="header-form">
                        <h3>Login Account</h3>
                    </div>
                    <div class="login-form">
                        <form action="<%=request.getContextPath()%>/login" method="POST">
                            <div class="form-group">
                                <label for="username">Usuario</label>
                                <input type="text" id="username" name="username" required="required">
                            </div>
                            <div class="form-group">
                                <label for="password">Contraseña</label>
                                <input type="password" id="password" name="password" required="required">
                            </div>
                            <div class="form-group">
                                <label class="form-remember">
                                    <input type="checkbox">
                                    Recuérdame
                                </label>
                                <a class="form-recovery" href="#">Forgot Password?</a>
                            </div>
                            <div class="form-group">
                                <button type="submit">Iniciar Sesión</button>
                            </div>
                        </form>
                    </div>
                </aside>
            </div>
        </div>
    </body>

</html>