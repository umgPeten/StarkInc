<%-- 
    Document   : inicio
    Created on : 16-may-2020, 22:44:38
    Author     : mikesb
--%>
<!DOCTYPE html>
<html>
    <%@include file="cabecero.jsp"%>
    <%@include file="menu.jsp"%>

    <section class="content">
        <div class="container-fluid">
            <div class="block-header">
                <h2>CHEQUES</h2>
            </div>
            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="card">
                        <div class="header">
                            <h2>
                                Cheques Creados
                                <small>Listado de todos cheques que se han generado</small>
                            </h2>
                            
                        </div>
                        <div class="body table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th># Cheque</th>
                                        <th>Banco</th>
                                        <th>Beneficiario</th>
                                        <th>Monto</th>
                                        <th>Fecha</th>
                                        <th>Imprimir</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th scope="row">1</th>
                                        <td>Banrural</td>
                                        <td>Bimbo S.A</td>
                                        <td>Q 1500.00</td>
                                        <td>17/05/2020</td>
                                        <td><button type="button" class="btn btn-primary">Imprimir</button></td>
                                        
                                    </tr>
                                    <tr>
                                        <th scope="row">2</th>
                                        <td>Banco Agromercantil</td>
                                        <td>Pepsi S.A</td>
                                        <td>Q 2500.00</td>
                                        <td>15/05/2020</td>
                                        <td><button type="button" class="btn btn-primary">Imprimir</button></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">3</th>
                                        <td>Banrural</td>
                                        <td>Intel Inc.</td>
                                        <td>Q 3500.00</td>
                                        <td>18/05/2020</td>
                                        <td><button type="button" class="btn btn-primary">Imprimir</button></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">4</th>
                                        <td>Banco G&T</td>
                                        <td>Helweth Package</td>
                                        <td>Q 3559.00</td>
                                        <td>18/05/2020</td>
                                        <td><button type="button" class="btn btn-primary">Imprimir</button></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">5</th>
                                        <td>Banco de los Trabajadores</td>
                                        <td>Apple Inc.</td>
                                        <td>Q 55016.00</td>
                                        <td>12/05/2020</td>
                                        <td><button type="button" class="btn btn-primary">Imprimir</button></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </section>


    
    <%@include file="libreriasJS.jsp" %>
</html>
