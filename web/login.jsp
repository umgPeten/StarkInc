<%-- 
    Document   : login.jsp
    Created on : 15-may-2020, 23:41:17
    Author     : mikesb
--%>

<%
    HttpSession sesion = request.getSession(false);
    String username = (String) sesion.getAttribute("usuario");
    if (username != null) {
        response.sendRedirect("inicio.jsp");
    }
    

    %>

<head>
<%@include file="cabecero.jsp"%>

<title>Stark Inc - Login</title>
</head>
<body class="login-page">
    <div class="login-box">
        <div class="logo">
            <a href="javascript:void(0);">Stark<b>INC</b></a>
            <small>Creando un mejor mundo para ti :)</small>
        </div>
        <div class="card">
            <div class="body">
                <form action="<%=request.getContextPath()%>/login" method="POST">
                    <div class="msg">Inicia Sesión para poder ingresar</div>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">person</i>
                        </span>
                        <div class="form-line">
                            <input type="text" class="form-control" name="username" placeholder="Username" required autofocus>
                        </div>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">lock</i>
                        </span>
                        <div class="form-line">
                            <input type="password" class="form-control" name="password" placeholder="Password" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-8 p-t-5">
                            <input type="checkbox" name="rememberme" id="rememberme" class="filled-in chk-col-pink">
                            <label for="rememberme">Recuérdame</label>
                        </div>
                        <div class="col-xs-4">
                            <button class="btn btn-primary bg-pink waves-effect" type="submit">Ingresar</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>

    <%@include file="libreriasJS.jsp"%>
    <script src="js/plugins/node-waves/waves.js"></script>
    <script src="js/pages/login/sign-in.js"></script>

</body>
