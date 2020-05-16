$(document).ready(function () {

    $(window).scroll(function () {
        if($(this).scrollTop() > 0){
         $('header').addClass('header-inicio2');
            console.log('me movi xd');   
        }else{
         $('header').removeClass('header-inicio2');
            console.log('Estoy arriba');
        }
    });
});