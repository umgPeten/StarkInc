<%-- 
    Document   : vercheques
    Created on : 25-may-2020, 20:09:34
    Author     : mikesb
--%>

<%@page import="java.util.Iterator"%>
<%@page import="umg.negocio.Cheque"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../../cabecero.jsp" %>

        <title>Ver cheques</title>
    </head>
    <body>
        <%@include file="../../menu.jsp"%>
        <%
            String id_chequera = request.getParameter("id_chequera");
            ArrayList<Cheque> cheques = (ArrayList<Cheque>) request.getAttribute("cheques");
            Cheque cheque = null;
        %>
        <section class="content">
            <div class="container-fluid">
                <div class="block-header">
                    <h2>Cheques</h2>
                </div>
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <h2>
                                    Chequera <%out.println(id_chequera);%>
                                    <small>Listado de los cheques todos los cheques</small>

                                </h2>

                            </div>

                            <div class="body table-responsive">
                                <table class="table table-striped">
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
                                        %>

                                    </tbody>

                                </table>
                                
                            </div>




                        </div>
                    </div>
                </div>

        </section>





        <%@include file="../../libreriasJS.jsp"%>
        <script src="../../js/plugins/node-waves/waves.js"></script>
    </body>
</html>
