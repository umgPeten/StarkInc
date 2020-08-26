<%@page import="java.util.Iterator"%>
<%@page import="umg.modelo.ChequesDAO"%>
<%@page import="umg.negocio.Cheque"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (id_rol == 3) {
        response.sendRedirect("inicio.jsp");
    }

%>

<!DOCTYPE html>
<html>
    <head>

        <title>Bandeja - StarkInc</title>
        <%@include file="cabecero.jsp"%>
    </head>
    <body>
        <%@include file="menu.jsp" %>

        <%
            int estado = 2;
            switch (id_rol) {
                case 2:
                    estado = 2;
                    break;
                case 4:
                    estado = 2;
                    break;
                case 5:
                    estado = 2;
            }

            ArrayList<Cheque> cheques = new ChequesDAO().consultarCheques(this.id_rol, username, null, null, null, null, null, estado);
            Cheque cheque = null;
        %>
        <section class="content">
            <div class="container-fluid">
                <div class="block-header">
                    <h2>Mi Bandeja</h2>
                </div>
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <h2>

                                    <small>Cheques pendientes de liberacion</small>

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
                                            <th>Liberar</th>

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
                                            <th><a href="<%=request.getContextPath()%>/cheques?accion=ver_cheque&username=<%out.println(username);%>&id_cheque=<%out.println(cheque.getId());%>&id_rol=<%out.println(id_rol);%>">
                                                <button type="submit" class="btn btn-danger"><i class="material-icons">upgrade</i></button></a></th>

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
