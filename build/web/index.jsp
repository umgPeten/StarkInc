<!DOCTYPE html>
<html>

    <head>
        <meta charset='utf-8'>
        <meta http-equiv='X-UA-Compatible' content='IE=edge'>
        <title>Stark Inc - Inicio</title>
        <meta name='viewport' content='width=device-width, initial-scale=1'>
        <link rel='stylesheet' type='text/css' media='screen' href='estilos.css'>
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,400italic,700,700italic,900,900italic" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src='scripts.js'></script>
    </head>

    <body>

        <%@include file="header.jsp"%>

        <%
        response.sendRedirect("login.jsp");
        %>

            <div class="form-inicio">
                <aside>
                    <%@include file="formularioLogin.jsp" %>
                </aside>

            </div>

        </div>

    </body>

</html>