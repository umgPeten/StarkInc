<%-- 
    Document   : inicio
    Created on : 16-may-2020, 22:44:38
    Author     : mikesb
--%>
<%@page import="umg.negocio.Chequera"%>
<%@page import="umg.modelo.ChequeraDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="umg.negocio.Cuenta"%>
<%@page import="umg.modelo.CuentaDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="cabecero.jsp"%>
        <link href="js/plugins/node-waves/waves.css" rel="stylesheet" />
        <title>Stark Inc - Chequeras</title>
    </head>
    <body>
        <%@include file="menu.jsp"%>

        <section class="content">
            <div class="container-fluid">
                <div class="block-header">
                    <h2>Chequeras</h2>
                </div>
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <h2>
                                    Chequeras
                                    <small>Listado de las chequeras disponibles</small>
                                </h2>

                            </div>

                            <div class="body table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th># Chequera</th>
                                            <th>Numero Cuenta</th>
                                            <th>Banco</th>
                                            <th>Stock</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <%
                                            ChequeraDAO chequeraDAO = new ChequeraDAO();
                                            Chequera chequera = new Chequera();
                                            ArrayList<Chequera> chequeras = chequeraDAO.getChequera();
                                            Iterator it = chequeras.iterator();
                                            while (it.hasNext()) {

                                                chequera = (Chequera) it.next();
                                                String numero_cuenta = chequera.getCuenta().getNumero_cuenta();
                                                System.out.println(numero_cuenta);
                                                String banco = chequera.getCuenta().getBanco().getNombre();
                                        %>

                                        <tr>
                                            <th scope="row"><%out.println(chequera.getId());%></th>
                                            <th><%out.println(numero_cuenta);%></th>
                                            <th><%out.println(banco);%></th>
                                            <th><%out.println(chequera.getStock());%></th>

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
                                        <button type="submit" class="btn btn-primary btn-block" data-toggle="modal" data-target="#modalChequera" data-whatever="@mdo">Agregar Chequera</button>
                                    </div>
                                </div>
                            </div>



                            <div class="modal fade" id="modalChequera" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="modalChequeraLabel">Agregar Chequera</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form id="form_validation" action="<%=request.getContextPath()%>/cheques" method="POST" class="was-validate">
                                                <div class="form-group">
                                                    <label for="inputChequera">Numero de Cuenta</label>
                                                    <select id="inputCuenta" class="form-control" name="num_cuenta">
                                                        <option selected>Seleccion el numero de cuenta</option>
                                                        <%
                                                            CuentaDAO cuentaDAO = new CuentaDAO();
                                                            ArrayList<Cuenta> cuentas = cuentaDAO.getCuentas();
                                                            Iterator iteCuentas = cuentas.iterator();
                                                            Cuenta cuenta = new Cuenta();
                                                            while (iteCuentas.hasNext()) {
                                                                cuenta = (Cuenta) iteCuentas.next();

                                                        %>
                                                        <option value="<%out.print(cuenta.getNumero_cuenta());%>"><%out.print(cuenta.getNumero_cuenta() + "   - " + cuenta.getBanco().getNombre());%></option>
                                                        <%
                                                            }


                                                        %>
                                                    </select>

                                                </div>

                                                <div class="form-group">
                                                    <div class="form-line">
                                                        <label>#ID Chequera</label>
                                                        <input type="number" class="form-control" placeholder="Ingrese el ID de su chequera" name="id_chequera">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="form-line">
                                                        <label>Inicio de la Chequera</label>
                                                        <input type="number" class="form-control" placeholder="Ingrese el inicio su chequera" name="inicio">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="form-line">
                                                        <label>Fin de la Chequera</label>
                                                        <input type="number" class="form-control" placeholder="Ingrese el fin de su chequera" name="fin">
                                                    </div>
                                                </div>

                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Salir</button>
                                                    <button type="submit" class="btn btn-primary" name="accion" value="agregarChequera">Agregar Chequera</button>
                                                </div>
                                            </form>
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