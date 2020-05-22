<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="umg.modelo.PuestosDAO"%>
<%@page import="umg.negocio.Departamento"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="cabecero.jsp"%>

        <title>Stark Inc - Agregar Usuarios</title>


        <script type="text/javascript">
            function combo_departamento() {
                $(#)
            }

        </script>

    </head>
    <body>
        <%@include file="menu.jsp"%>
        <section class="content">
            <div class="container-fluid">
                <div class="block-header">
                    <h2>
                        Formulario <br>
                        <small>Formulario para la creacion de nuevos empleados</small>
                    </h2>

                </div>

                <div class="card">
                    <div class="header">
                        <h2>Complete el formulario</h2>
                    </div>

                    <div class="body">
                        <form id="form_validation" action="<%=request.getContextPath()%>/servEmpleados" method="POST" class="was-validate">
                            <div class="row clearfix">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <label>Nombre</label>
                                            <input type="text" class="form-control" placeholder="Ingrese el nombre" name="nombre">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <label>Apellido</label>
                                            <input type="text" class="form-control" placeholder="Ingrese el apellido" name="apellido">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row clearfix">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <label>Número de teléfono</label>
                                            <input type="text" class="form-control" placeholder="Ingrese el numero de telefono" name="telefono">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <label>Dirección</label>
                                            <input type="text" class="form-control" placeholder="Ingrese la dirección" name="direccion">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row clearfix">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <label>Documento de Identificación</label>
                                            <input type="text" class="form-control" placeholder="Ingrese documento de Identificacion" name="identificacion">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <label>Tipo de Documento</label>
                                            <select id="inputID" class="form-control" name="tipo_documento">
                                                <option selected>Seleccione</option>
                                                <option value="1">DPI</option>
                                                <option value="2">Cédula</option>
                                                <option value="3">Pasaporte</option>
                                            </select>
                                        </div>
                                    </div>    
                                </div>

                            </div>

                            <div class="row clearfix">
                                <div class="col-md-3">
                                    <label>Fecha de Nacimiento</label>
                                    <div class="input-group date" id="bs_datepicker_component_container">
                                        <div class="form-line">
                                            <input type="text" class="form-control" placeholder="# 15/02/2000" name="fecha_nac">
                                        </div>
                                        <span class="input-group-addon">
                                            <i class="material-icons">date_range</i>
                                        </span>
                                    </div>
                                </div>
                            </div>



                            <div class="row clearfix">
                                <div class="col-md-3">
                                    <div class="form-group form-line">
                                        <label>Seleccione el genero</label>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="radioGenero" id="gridRadios1" value="1" checked><br>
                                            <label class="form-check-label" for="radioGeneroF">
                                                Femenino
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="radioGenero" id="gridRadios2" value="2"><br>
                                            <label class="form-check-label" for="radioGeneroM">
                                                Masculino
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row clearfix">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <label>Departamento</label>
                                            <select id="inputDepartamento" class="form-control" name="departamento" onchange="combo_departamento()">

                                                <%
                                                    Departamento departamento;
                                                    PuestosDAO puestosDAO = new PuestosDAO();
                                                    ArrayList<Departamento> puestos = puestosDAO.getDepartamentos();
                                                    Iterator it = puestos.iterator();
                                                    while (it.hasNext()) {
                                                        departamento = (Departamento) it.next();

                                                %>
                                                <option value = "<%out.print(departamento.getId_departamento());%>"><%out.print(departamento.getDepartamento());%></option>
                                                <%
                                                    }
                                                %>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <label>Puestos</label>
                                            <select id="inputRol" class="form-control" name="puesto">
                                                <option selected>Seleccione el puesto</option>
                                                <option value="1">Administrador</option>             
                                            </select>
                                        </div>
                                    </div>    
                                </div>
                            </div>

                            <div class="row clearfix">
                                <div class="col-md-3">
                                    <div class="form-group">

                                        <button type="submit" class="btn btn-success btn-block" name="accion" value="insertarEmpleado">Enviar</button>

                                    </div>
                                </div>
                            </div>
                        </form>
                                         
                                            
                                            
                    </div>

                </div>
            </div
        </section>
        <%@include file="libreriasJS.jsp"%>
        <script src="js/plugins/node-waves/waves.js"></script>
    </body>
</html>