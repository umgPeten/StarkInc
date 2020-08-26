<%-- 
    Document   : consultacheques
    Created on : 30-may-2020, 3:29:50
    Author     : mikesb
--%>
<%@page import="umg.negocio.Cuenta"%>
<%@page import="umg.modelo.CuentaDAO"%>
<%@page import="umg.modelo.ProveedorDAO"%>
<%@page import="umg.negocio.Proveedor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="umg.negocio.Cheque"%>
<%@page import="java.util.Iterator"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta Cheques - Stark Inc</title>
        <%@include file="cabecero.jsp" %>
        <link href="js/plugins/jquery-datatable/skin/bootstrap/css/dataTables.bootstrap.css"
              </head>
    <body>
        <%@include file="menu.jsp" %>

        <section class="content">
            <div class="container-fluid">
                <div class="block-header">
                    <h2>REPORTES DE CHEQUES</h2>
                </div>
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <form action="<%=request.getContextPath()%>/reportes?accion=busqueda_fechas&id_rol=<%out.println(id_rol);%>&username=<%out.println(username);%>"  method="POST">
                                    <div class="row clearfix">
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <div class="form-line">
                                                    <label>Desde</label>
                                                    <input type="text" class="form-control" placeholder="dd/mm/yyyy" name="fecha_inicio">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <div class="form-line">
                                                    <label>Hasta</label>
                                                    <input type="text" class="form-control" placeholder="dd/mm/yyyy" name="fecha_fin">

                                                </div>
                                            </div>
                                        </div>
                                        <div class="table-responsive">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <div class="form-line">
                                                        <label>Numero de Cuenta</label>
                                                        <select id="inputID" class="form-control" name="num_cuenta">
                                                            <option selected>Seleccione</option>
                                                            <%
                                                                CuentaDAO cuentaDAO = new CuentaDAO();
                                                                ArrayList<Cuenta> cuentas = cuentaDAO.getCuentas(id_rol);
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
                                                </div>
                                            </div>

                                            <div class="col-md-1">
                                                <div class="form-group">
                                                    <div class="form-control">
                                                        <label> </label>
                                                        <button type="submit" class="btn btn-link "><i class="material-icons">search</i></button></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                </form>
                            </div>
                            <a class="dt-button buttons-pdf buttons-html5" tabindex="0" aria-controls="DataTables_Table_1" href="#"><span>PDF</span></a>
                            <div class="body table-responsive">
                                <table class="table table-bordered table-striped table-hover dataTable js-exportable">
                                    <thead>
                                        <tr>
                                            <th>#Num de Cheque</th>
                                            <th>Beneficiario</th>
                                            <th>Monto</th>
                                            <th>Fecha</th>
                                            <th>Lugar</th>
                                            <th>Estado</th>
                                            <th>Banco</th>


                                        </tr>
                                    </thead>

                                    <tbody>
                                        <%
                                            Cheque cheque = new Cheque();
                                            ArrayList<Cheque> cheques = (ArrayList<Cheque>) request.getAttribute("cheques");
                                            if (cheques != null) {
                                                Iterator iterador = cheques.iterator();
                                                while (iterador.hasNext()) {
                                                    cheque = (Cheque) iterador.next();

                                        %>
                                        <tr>
                                            <th scope="row"><%out.println(cheque.getId());%></th>
                                            <th><%out.println(cheque.getBeneficiario());%></th>
                                            <th><%out.println(cheque.getMonto());%></th>
                                            <th><%out.println(cheque.getFecha());%></th>
                                            <th><%out.println(cheque.getLugar());%></th>
                                            <th><%out.println(cheque.getEstado());%></th>
                                            <th><%out.println(cheque.getChequera().getCuenta().getBanco().getNombre());%></th>


                                        </tr>
                                        <%
                                                }
                                            }
                                        %>
                                    </tbody>

                                </table>

                            </div>
                        </div>
                    </div>
                </div>

        </section>

        <%@include file="libreriasJS.jsp" %>
        <script src="js/plugins/jquery-datatable/jquery.dataTables.js"></script>
        <script src="js/plugins/jquery-datatable/extensions/export/buttons.print.min.js"></script>

    </body>
</html>
