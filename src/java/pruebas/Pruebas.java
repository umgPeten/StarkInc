package pruebas;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author mikesb
 */
public class Pruebas {
    public static void main(String[] args) throws ParseException {
        
        SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = sfd.parse("20-04-2020");
        System.out.println(fecha);
        
    }
}
