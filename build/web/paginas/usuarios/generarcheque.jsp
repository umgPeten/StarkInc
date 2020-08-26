<%-- 
    Document   : generarcheque
    Created on : 26-may-2020, 11:44:43
    Author     : mikesb
--%>

<%@page import="umg.negocio.Cheque"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="umg.modelo.ProveedorDAO"%>
<%@page import="umg.negocio.Proveedor"%>
<%@page import="umg.negocio.Chequera"%>
<%
    Chequera chequera = (Chequera) request.getAttribute("chequera");
    ArrayList<Proveedor> proveedores = (ArrayList<Proveedor>) request.getAttribute("proveedores");
    Proveedor proveedor = null;
    Iterator iterador = proveedores.iterator();
    
        Date fecha = new Date();
        String formatoFecha;
        String formato = "dd/MM/yyyy";
        SimpleDateFormat dateSDF = new SimpleDateFormat(formato);
        formatoFecha = dateSDF.format(fecha);
        Cheque cheque = (Cheque)request.getAttribute("cheque_modificar");
        


%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Nuevo cheque</title>
        <%@include file="../../cabecero.jsp"%>

    </head>
    <body>
        <%@include file="../../menu.jsp"%>
        <section>
            <section class="content">
                <div class="container-fluid">
                    <div class="block-header">
                        <h2>
                            Chequera <%out.println(chequera.getId());%><br>
                            <small>Por favor complete el siguiente formulario</small>
                        </h2>

                    </div>

                    <div class="card">
                        <div class="header">
                            <div class="row clearfix">
                                <div class="col-md-3">

                                    <div class="form-line">
                                        <h3 class="header-form font-30">
                                            <%out.println(chequera.getCuenta().getBanco().getNombre());%>
                                        </h3>
                                    </div>

                                </div>

                                <div class="col-md-3">

                                    <div class="form-line">
                                        <h3 class="text-center font-18">STARK INC</h3>
                                        <p class="text-center"><%out.println(chequera.getCuenta().getNumero_cuenta());%></p>
                                    </div>

                                </div>
                            </div>
                        </div>

                        <div class="body">
                            <form id="form_validation" action="<%=request.getContextPath()%>/cheques?accion=ingresar_cheque&id_rol=<%out.println(id_rol);%>&id_chequera=<%out.println(chequera.getId());%>&username=<%out.println(this.username);%>" method="POST" class="was-validate">
                                <div class="row form-group margin-0">
                                    <div class="col-md-2">
                                        <label class="font-18 text-left">Lugar y Fecha</label>
                                    </div>

                                    <div class="col-md-4">
                                        <input type="text" class="form-group font-bold text-center" placeholder="Ingrese el lugar" name="lugar">
                                    </div>
                                    <div class="col-md-2">
                                        <input type="text" class="form-group text-center" placeholder="Fecha" name="fecha" value=<%out.println(formatoFecha);%> disabled>
                                    </div>
                                    <div class="col-md-1">
                                        <label class="font-18">Monto</label>
                                    </div>
                                    <div class="col-md-3">
                                        <input type="number" class="form-group font-bold text-center" placeholder="Ingrese monto" name="monto">
                                    </div>
                                </div>
                                <div class="row form-group margin-0">
                                    <div class="col-md-2">
                                        <label class="font-14" >Pago a la orden de:</label>
                                    </div>
                                    <div class="col-md-10">
                                        <select id="inputID" class="form-group" name="proveedor">
                                            <option selected>Seleccione</option>
                                            <%
                                                while (iterador.hasNext()){
                                                proveedor = (Proveedor)iterador.next();
                                            %>
                                            <option value=<%out.println(proveedor.getId());%>><%out.println(proveedor.getNombre());%></option>

                                            <%
                                                }
                                                %>
                                        </select>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-md-8">

                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">

                                            <button type="submit" class="btn btn-primary btn-block">Generar</button>

                                        </div>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </section>
        <%@include file="../../libreriasJS.jsp" %>
        <script src="../../js/plugins/node-waves/waves.js"></script>
    </body>
</html>
