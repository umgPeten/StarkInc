<%-- 
    Document   : inicio
    Created on : 16-may-2020, 22:44:38
    Author     : mikesb
--%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="umg.negocio.Cuenta"%>
<%@page import="umg.modelo.CuentaDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="cabecero.jsp"%>

        <title>Stark Inc - Cuentas</title>
    </head>
    <body>
        <%@include file="menu.jsp"%>

        <section class="content">
            <div class="container-fluid">
                <div class="block-header">
                    <h2>Cuentas</h2>
                </div>
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <h2>
                                    Cuentas Bancarias
                                    <small>Listado de las cuentas bancarias</small>
                                </h2>

                            </div>

                            <div class="body table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th># Cuenta</th>
                                            <th>Nombre</th>
                                            <th>Fondos</th>
                                            <th>Banco</th>
                                            <th>Tipo de Cuenta</th>
                                            <th>Moneda</th>
                                            <th>Agregar Fondos</th>

                                        </tr>
                                    </thead>

                                    <tbody>
                                        <%
                                            CuentaDAO cuentaDAO = new CuentaDAO();
                                            Cuenta cuenta = new Cuenta();
                                            ArrayList<Cuenta> cuentas = cuentaDAO.getCuentas();
                                            Iterator itCuetas = cuentas.iterator();
                                            while (itCuetas.hasNext()) {
                                                cuenta = (Cuenta) itCuetas.next();
                                        %>
                                        <tr>
                                            <th scope="row"><%out.println(cuenta.getNumero_cuenta());%></th>
                                            <th><%out.println(cuenta.getNombre());%></th>
                                            <th><%out.println(cuenta.getFondos());%></th>
                                            <th><%out.println(cuenta.getBanco().getNombre());%></th>
                                            <th><%out.println(cuenta.getTipo_cuenta());%></th>
                                            <th><%out.println(cuenta.getMoneda());%></th>
                                            <th><button type="submit" class="btn btn-light btn-group-sm"><li class="material-icons">attach_money</li></button></th>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>

                                </table>
                                <div class="row clearfix">
                                    <div class="col-md-3">
                                        <br>
                                        <br>
                                        <button type="submit" class="btn btn-success btn-block" name="accion" value="insertarEmpleado">Agregar Cuenta</button>
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>
            </div>

        </section>



        <%@include file="libreriasJS.jsp" %>

    </body>
</html>

