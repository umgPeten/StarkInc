var id_rol = document.getElementById("rol").innerHTML;
console.log('El rol es: ' + id_rol);

window.onload = function ocultar() {
    console.log(id_rol);

    switch (parseInt(id_rol)) {

        /* Caso Pagos */
        case 2:
            document.getElementById("li_empleados").style.display = "none";
            document.getElementById("li_banco").style.display = "none";
            document.getElementById("li_proveedores").style.display = "none";
            document.getElementById("li_reportes").style.display = "none";
            document.getElementById("nuevo_cheque").style.display = "none";
            document.getElementById("estado_cheque").style.display = "none";
            //document.getElementById("ver_chequeras").style.display="none";
            break;


            /*En caso sea un cajero */
        case 3:
            
            document.getElementById("li_empleados").style.display = "none";
            document.getElementById("li_banco").style.display = "none";
            document.getElementById("li_proveedores").style.display = "none";
            document.getElementById("li_reportes").style.display = "none";
            document.getElementById("nuevo_cheque").style.display = "none";
            document.getElementById("estado_cheque").style.display = "none";
            document.getElementById("ver_chequeras").style.display = "none";
            document.getElementById("bandeja_cheque").style.display = "none";
            break;
            
              /*En caso sea un auditor */
        case 4:
            console.log('El rol es: asdasd' + id_rol);
            document.getElementById("li_empleados").style.display = "none";
            document.getElementById("li_proveedores").style.display = "none";
            console.log(id_rol);
            break;

            /*En Caso sea Jefe de Pagos*/
        case 6:
            
           
            
            document.getElementById("li_proveedores").style.display = "none";
            
            
            break;
    }



};




$(document).ready(function () {

    $(window).scroll(function () {
        if ($(this).scrollTop() > 0) {
            $('header').addClass('header-inicio2');
            console.log('me movi xd');
        } else {
            $('header').removeClass('header-inicio2');
            console.log('Estoy arriba');
        }
    });
});

