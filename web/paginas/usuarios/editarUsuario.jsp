<%@page import="java.util.Iterator"%>
<%@page import="umg.modelo.PuestosDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="umg.negocio.Rol"%>

<%
    UsuarioDTO usuarioDTOEditar = (UsuarioDTO) request.getAttribute("usuario_edit");
    System.out.println("jsp");
    System.out.println(usuarioDTOEditar.toString());
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../../cabecero.jsp"%>

        <title>Stark Inc - Agregar Usuarios</title>
    </head>
    <body>
        <%@include file="../../menu.jsp"%>
        <section class="content">
            <div class="container-fluid">
                <div class="block-header">
                    <h2>
                        Formulario <br>
                        <small>Formulario para Editar Informacion de los Usuarios</small>
                    </h2>

                </div>

                <div class="card">
                    <div class="header">
                        <h2>Modifique el formulario</h2>
                    </div>

                    <div class="body">
                        <form id="form_validation" action="<%=request.getContextPath()%>/servEmpleados?accion=editar_usuario&id_rol=<%out.println(id_rol);%>&usuario=<%out.println(usuarioDTOEditar.getUsernamae());%>" method="POST" class="was-validate">
                            <div class="row clearfix">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <label>Usuario</label>
                                            <input type="text" class="form-control" placeholder="Usuario" name="usuario" disabled value="<%out.println(usuarioDTOEditar.getUsernamae());%>">
                                        </div>
                                    </div>
                                </div>


                            </div>

                            <div class="row clearfix">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <label>Nombre</label>
                                            <input type="text" class="form-control" placeholder="Ingrese el nombre" disabled name="nombre" value="<%out.println(usuarioDTOEditar.getNombre());%>">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <label>Apellido</label>
                                            <input type="text" class="form-control" placeholder="Ingrese el apellido" disabled name="apellido" value="<%out.println(usuarioDTOEditar.getApellido());%>">
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="row clearfix">

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <label>Monto Minimo</label>
                                            <input type="number" class="form-control" placeholder="Ingrese el monto maximo" name="monto_minimo" required value=<%out.println(usuarioDTOEditar.getMontoMinimo());%>>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <label>Monto Maximo</label>
                                            <input type="number" class="form-control" placeholder="Ingrese el monto minimo" name="monto_maximo" required value=<%out.println(usuarioDTOEditar.getMontoMaximo());%>>
                                        </div>
                                    </div>
                                </div>


                            </div>

                            <div class="row clearfix">
                                <div class="col-md-6">
                                    <div class="form-line">
                                        <label for="inputRol">Seleccion un Rol</label>
                                        <select id="inputCuenta" class="form-control" name="rol">
                                            <option selected>Seleccione</option>
                                            <%
                                                Rol rol;
                                                ArrayList<Rol> roles = PuestosDAO.getInstancia().getRoles(id_rol);
                                                Iterator iterador = roles.iterator();
                                                while (iterador.hasNext()) {
                                                    rol = (Rol) iterador.next();
                                            %>
                                            <option value=<%out.println(rol.getId_rol());%>><%out.println(rol.getRol());%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>
                            </div>



                            <div class="row clearfix">
                                <div class="col-md-6">
                                    <div class="form-group">

                                        <button type="submit" class="btn btn-info btn-block">Guardar</button>

                                    </div>
                                </div>
                            </div>
                        </form>



                    </div>

                </div>
            </div
        </section>
        <%@include file="../../libreriasJS.jsp"%>
        <script src="../../js/plugins/node-waves/waves.js"></script>

    </body>
</html>