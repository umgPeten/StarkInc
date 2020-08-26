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
    
    String fechaSTR = "";
    Date fecha = null;
    Cheque cheque = (Cheque) request.getAttribute("cheque_liberar");
    
    for(int i = 0; i < cheque.getFecha().length(); i++){
        if(cheque.getFecha().charAt(i) == ' '){
            break;
        }else{
            fechaSTR += cheque.getFecha().charAt(i);
        } 
        
    }

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
                            Chequera <%out.println(cheque.getChequera().getId());%><br>
                            <small>Por favor complete el siguiente formulario</small>
                        </h2>

                    </div>

                    <div class="card">
                        <div class="header">
                            <div class="row clearfix">
                                <div class="col-md-4">

                                    <div class="form-line">
                                        <h3 class="header-form font-30">
                                            <%out.println(cheque.getChequera().getCuenta().getBanco().getNombre());%>
                                        </h3>
                                    </div>

                                </div>

                                <div class="col-md-4">

                                    <div class="form-line">
                                        <h3 class="text-center font-18">STARK INC</h3>
                                        <p class="text-center"><%out.println(cheque.getChequera().getCuenta().getNumero_cuenta());%></p>
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
                            <form id="form_validation" action="<%=request.getContextPath()%>/cheques?accion=liberar_cheque&id_rol=<%out.println(id_rol);%>&id_cheque=<%out.println(cheque.getId());%>&username=<%out.println(this.username);%>&monto=<%out.println(cheque.getMonto());%>" method="POST" class="was-validate">
                                <div class="row form-group margin-0">
                                    <div class="col-md-2">
                                        <label class="font-18 text-left">Lugar y Fecha</label>
                                    </div>

                                    <div class="col-md-4">
                                        <input type="text" class="form-group font-bold text-center" value="<%out.println(cheque.getLugar());%>" disabled name="lugar">
                                    </div>
                                    <div class="col-md-2">
                                        <input type="text" class="form-group text-center" placeholder="Fecha" name="fecha" value="<%out.println(fechaSTR);%>" disabled>
                                    </div>
                                    <div class="col-md-1">
                                        <label class="font-18">Monto</label>
                                    </div>
                                    <div class="col-md-3">
                                        <input type="text" class="form-group font-bold text-center" value="<%out.println(cheque.getMonto());%>"  disabled placeholder="Ingrese monto" name="mont0">
                                    </div>
                                </div>
                                <div class="row form-group margin-0">
                                    <div class="col-md-2">
                                        <label class="font-14" >Pago a la orden de:</label>
                                    </div>
                                    <div class="col-md-10">
                                        <input type="text" class="form-group font-bold text-center" value="<%out.println(cheque.getBeneficiario());%>"  disabled name="beneficiario">
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-4">
                                        <div class="form-group">

                                            <button type="submit" class="btn btn-primary btn-block waves-effect">Liberar</button>

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