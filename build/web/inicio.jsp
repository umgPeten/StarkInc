<%-- 
    Document   : inicio
    Created on : 16-may-2020, 22:44:38
    Author     : mikesb
--%>

<%@page import="umg.modelo.ChequesDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="umg.negocio.Cheque"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <header>

        <%@include file="cabecero.jsp"%>
        <title>Inicio - Stark Inc</title>
    </header>
    <body>
        <%@include file="menu.jsp"%>
        <%
            
            ArrayList<Cheque> cheques = new ChequesDAO().consultarCheques(this.id_rol, username, null, null, null, null,null);
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

                                    <small>Listado de los cheques emitidos</small>

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
                                            <th>Modificar</th>
                                            <th>Anular</th>
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
                                            <th><a href="<%=request.getContextPath()%>/cheques?accion=modificar_cheque&id_cheque=<%out.println(cheque.getId());%>&id_rol=<%out.println(id_rol);%>&monto=<%out.println(cheque.getMonto());%>&lugar=<%out.println(cheque.getLugar());%>&id_chequera=<%out.println(cheque.getChequera().getId());%>&num_cuenta=<%out.println(cheque.getChequera().getCuenta().getNumero_cuenta());%>&banco=<%out.println(cheque.getChequera().getCuenta().getBanco().getNombre());%>">
                                                    <button type="submit" class="btn btn-primary "><i class="material-icons">payment</i></button></a></th>
                                            <th><a href="<%=request.getContextPath()%>/cheques?accion=anular_cheque&id_cheque=<%out.println(cheque.getId());%>&id_rol=<%out.println(id_rol);%>&username=<%out.println(username);%>"><button type="submit" class="btn btn-danger "><i class="material-icons">delete</i></button></a></th>
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

        <%@include file="libreriasJS.jsp" %>
        <script src="/js/plugins/node-waves/waves.js"></script>
    </body>
</html>

