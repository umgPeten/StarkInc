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
            <label for="password">Contrase�a</label>
            <input type="password" id="password" name="password" required="required">
        </div>
        <div class="form-group">
            <label class="form-remember">
                <input type="checkbox">
                Recu�rdame
            </label>
            <a class="form-recovery" href="#">Forgot Password?</a>
        </div>
        <div class="form-group">
            <button type="submit">Iniciar Sesi�n</button>

        </div>
    </form>

</div>