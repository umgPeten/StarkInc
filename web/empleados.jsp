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
                    <h2>Empleados</h2>
                </div>
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <h2>
                                    Empleados
                                    <small>Listado de todos los empleados</small>
                                </h2>

                            </div>


                            <div class="body table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th scope="row"># Id_Empleado</th>
                                            <th>Nombre</th>
                                            <th>Apellido</th>
                                            <th>Direccion</th>
                                            <th>telefono</th>
                                            <th>Puesto</th>
                                            <th>Departamento</th>
                                            <th>Ver más</th>
                                            <th>Editar</th>
                                            <th>Eliminar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<Empleado> empleados = new ArrayList<Empleado>();
                                            Empleado emp = new Empleado();
                                            empleados = userDAO.obtenerEmpleados(1);
                                            Iterator iterador = empleados.iterator();
                                            while (iterador.hasNext()) {
                                                emp = (Empleado) iterador.next();

                                        %>                        
                                        <tr>
                                            <th scope="row"><%out.println(emp.getId_empleado());%></th>
                                            <th><%out.println(emp.getNombre());%></th>
                                            <th><%out.println(emp.getApellido());%></th>
                                            <th><%out.println(emp.getDireccion());%></th>
                                            <th><%out.println(emp.getTelefono());%></th>
                                            <th><%out.println(emp.getPuesto().getPuesto());%></th>
                                            <th><%out.println(emp.getPuesto().getDepartamento().getDepartamento());%></th>
                                            <th><button type="submit" class="btn btn-group-sm btn-light "><li class="material-icons">find_in_page</li></button></th>
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
                                                            empleados = userDAO.obtenerEmpleados(2);
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
