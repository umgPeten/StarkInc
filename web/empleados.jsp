<%-- 
    Document   : inicio
    Created on : 16-may-2020, 22:44:38
    Author     : mikesb
--%>
<%@page import="java.util.Iterator"%>
<%@page import="umg.negocio.Empleado"%>
<%@page import="java.util.ArrayList"%>
<%@page import="umg.modelo.UsuarioDao"%>
<!DOCTYPE html>
<html>
    <%@include file="cabecero.jsp"%>
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
                                    Usuarios
                                    <small>Listado de todos los empleados y usuarios</small>
                                </h2>

                            </div>


                            <div class="body table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th># Id_Empleado</th>
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
                                            UsuarioDao userDAO = new UsuarioDao();
                                            ArrayList<Empleado>empleados = new ArrayList<Empleado>();
                                            Empleado emp = new Empleado();
                                            empleados = userDAO.obtenerEmpleados();
                                            Iterator itEmpleados = empleados.iterator();
                                            while(itEmpleados.hasNext()){
                                            emp = (Empleado) itEmpleados.next();
                                        
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
                            </div>

                        </div>
                    </div>
                </div>
            </div>

        </section>



        <%@include file="libreriasJS.jsp" %>
    </body>
</html>
