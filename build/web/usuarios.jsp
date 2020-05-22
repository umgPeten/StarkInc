<%-- 
    Document   : inicio
    Created on : 16-may-2020, 22:44:38
    Author     : mikesb
--%>
<%@page import="umg.modelo.PuestosDAO"%>
<%@page import="umg.negocio.Rol"%>
<%@page import="java.util.Iterator"%>
<%@page import="umg.negocio.Empleado"%>
<%@page import="java.util.ArrayList"%>
<%@page import="umg.modelo.UsuarioDao.*"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="cabecero.jsp"%>

        <title>Stark Inc - Empleados</title>
    </head>
    <body>
        <%@include file="menu.jsp"%>

        <section class="content">
            <div class="container-fluid">
                <div class="block-header">
                    <h2>Usuarios</h2>
                </div>
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <h2>
                                    Usuarios
                                    <small>Listado de los usuarios del sistema</small>
                                </h2>

                            </div>


                            <div class="body table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th scope="row">Usuario</th>
                                            <th>Nombre</th>
                                            <th>Apellido</th>
                                            <th>Departamento</th>
                                            <th>Rol</th>
                                            <th>Editar</th>
                                            <th>Eliminar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                           ArrayList<UsuarioDTO> usuarios = new UsuarioDao().get_usuarios();
                                           UsuarioDTO userDTO;
                                            Iterator iterador = usuarios.iterator();
                                            while (iterador.hasNext()) {
                                                userDTO = (UsuarioDTO) iterador.next();

                                        %>                        
                                        <tr>
                                            <th scope="row"><%out.println(userDTO.getUsernamae());%></th>
                                            <th><%out.println(userDTO.getNombre());%></th>
                                            <th><%out.println(userDTO.getApellido());%></th>
                                            <th><%out.println(userDTO.getDepartamento().getDepartamento());%></th>
                                            <th><%out.println(userDTO.getRol());%></th>
                                            <th><button type="submit" class="btn btn-info btn-group-sm"><li class="material-icons">edit</li></button></th>
                                            <th><button type="submit" class="btn btn-danger btn-group-sm"><li class="material-icons">delete</li></button></th>

                                        </tr>
                                        <%
                                            }
                                        %>



                                    </tbody>
                                </table>
                                <p class="p-b-15">Desea crear un usuario para el sistema?</p>
                                <div class="row clearfix">
                                    <div class="col-md-3">
                                        <div class="form-group">

                                            <button type="submit" class="btn btn-primary btn-block" data-toggle="modal" data-target="#modalAgregarUsuario" data-whatever="@mdo">Agregar Usuario</button>

                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="modal fade" id="modalAgregarUsuario" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="modalChequeraLabel">AGREGAR USUARIO AL SISTEMA</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form id="form_validation" action="<%=request.getContextPath()%>/servEmpleados" method="POST" class="was-validate">
                                                <div class="form-group">
                                                    <label for="inputChequera">Usuario</label>
                                                    <select id="inputCuenta" class="form-control" name="id_empleado">
                                                        <option selected>Seleccione al Usuario</option>
                                                        <%
                                                            Empleado emp;
                                                            ArrayList<Empleado> empleados = userDAO.obtenerEmpleados(2);
                                                            iterador = empleados.iterator();
                                                            while (iterador.hasNext()) {
                                                                emp = (Empleado) iterador.next();
                                                        %>
                                                        <option value=<%out.println(emp.getId_empleado());%>><%out.println(emp.getNombre() + " " + emp.getApellido());%></option>
                                                        <%
                                                            }
                                                        %>
                                                    </select>

                                                </div>

                                                <div class="form-group">
                                                    <label for="inputRol">Seleccion un Rol</label>
                                                    <select id="inputCuenta" class="form-control" name="rol">
                                                        <option selected>Seleccione</option>
                                                        <%
                                                            Rol rol;
                                                            ArrayList<Rol> roles = PuestosDAO.getInstancia().getRoles();
                                                            iterador = roles.iterator();
                                                            while (iterador.hasNext()) {
                                                                rol = (Rol) iterador.next();
                                                        %>
                                                        <option value=<%out.println(rol.getId_rol());%>><%out.println(rol.getRol());%></option>
                                                        <%
                                                            }
                                                        %>
                                                    </select>

                                                </div>

                                                <div class="form-group">
                                                    <div class="form-line">
                                                        <label>Ingrese un nombre de usuario</label>
                                                        <input type="text" class="form-control" placeholder="@username" name="username">
                                                    </div>
                                                </div>


                                                <div class="form-group">
                                                    <div class="form-line">
                                                        <label>Ingrese el monto minimo para generar Chequess</label>
                                                        <input type="number" class="form-control" placeholder="Valor Máximo" name="monto_minimo">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="form-line">
                                                        <label>Ingrese el monto maximo para generar Chequess</label>
                                                        <input type="number" class="form-control" placeholder="Valor Minimo" name="monto_maximo">
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Salir</button>
                                                    <button type="submit" class="btn btn-primary" name="accion" value="insertarUsuario">Agregar Usuario</button>
                                                </div>
                                                <div class="alert alert-danger" style="display: none">
                                                    <strong>Oh snap!</strong> <a href="javascript:void(0);" class="alert-link">Ha ocurrido un problema</a> Intentelo nuevamente.
                                                </div>
                                                <div class="alert alert-success" style="display: none">
                                                    <strong>Well done!</strong> Se he agregado  <a href="javascript:void(0);" class="alert-link">Satisfactoriamente el usuario</a>.
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </section>



        <%@include file="libreriasJS.jsp" %>
        <script src="js/plugins/node-waves/waves.js"></script>
    </body>
</html>