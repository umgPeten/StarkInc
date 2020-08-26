<%-- 
    Document   : modificarcheque
    Created on : 28-may-2020, 20:15:39
    Author     : mikesb
--%>

<%@page import="umg.modelo.ProveedorDAO"%>
<%@page import="umg.negocio.Cheque"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="umg.negocio.Proveedor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="umg.negocio.Chequera"%>
<%
    
    ArrayList<Proveedor> proveedores = new ProveedorDAO().getProveedores(this.id_rol, null);
    Proveedor proveedor = null;
    Iterator iterador = proveedores.iterator();

    Date fecha = new Date();
    String formatoFecha;
    String formato = "dd/MM/yyyy";
    SimpleDateFormat dateSDF = new SimpleDateFormat(formato);
    formatoFecha = dateSDF.format(fecha);
    String numero_cuenta = (String) request.getAttribute("num_cuenta");
    String banco = (String) request.getAttribute("banco");
    Cheque cheque = (Cheque) request.getAttribute("cheque_modificar");
    String id_chequera = (String) request.getAttribute("id_chequera");
    double monto = cheque.getMonto();


%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../../cabecero.jsp" %>
        <title>Modificar Cheque - Stark Inc</title>
    </head>
    <body>
        <%@include file="../../menu.jsp" %>
        <section>
            <section class="content">
                <div class="container-fluid">
                    <div class="block-header">
                        <h2>
                            Chequera <%out.println(id_chequera);%><br>
                            <small>Por favor complete el siguiente formulario</small>
                        </h2>

                    </div>

                    <div class="card">
                        <div class="header">
                            <div class="row clearfix">
                                <div class="col-md-4">

                                    <div class="form-line">
                                        <h3 class="header-form font-30">
                                            <%out.println(banco);%>
                                        </h3>
                                    </div>

                                </div>

                                <div class="col-md-4">

                                    <div class="form-line">
                                        <h3 class="text-center font-18">STARK INC</h3>
                                        <p class="text-center"><%out.println(numero_cuenta);%></p>
                                    </div>

                                </div>

                                <div class="col-md-4">

                                    <div class="form-line">
                                        <h3 class="text-center font-18">Cheque No.</h3>
                                        <p class="text-center"><%out.println(cheque.getId());%></p>
                                    </div>

                                </div>
                            </div>
                        </div>

                        <div class="body">
                            <form id="form_validation" action="<%=request.getContextPath()%>/cheques?accion=modificar_cheque&id_rol=<%out.println(id_rol);%>&id_cheque=<%out.println(cheque.getId());%>&username=<%out.println(this.username);%>" method="POST" class="was-validate">
                                <div class="row form-group margin-0">
                                    <div class="col-md-2">
                                        <label class="font-18 text-left">Lugar y Fecha</label>
                                    </div>

                                    <div class="col-md-4">
                                        <input type="text" class="form-group font-bold text-center" value="<%out.println(cheque.getLugar());%>" placeholder="Ingrese el lugar" name="lugar">
                                    </div>
                                    <div class="col-md-2">
                                        <input type="text" class="form-group text-center" placeholder="Fecha" name="fecha" value="<%out.println(formatoFecha);%>" disabled>
                                    </div>
                                    <div class="col-md-1">
                                        <label class="font-18">Monto</label>
                                    </div>
                                    <div class="col-md-3">
                                        <input type="number" class="form-group font-bold text-center" value=""  placeholder="Ingrese monto" name="monto">
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
                                                while (iterador.hasNext()) {
                                                    proveedor = (Proveedor) iterador.next();
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

                                            <button type="submit" class="btn btn-danger btn-block waves-effect">Modificar</button>

                                        </div>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </section>
        <%@include file="../../libreriasJS.jsp"%>
    </body>
</html>
