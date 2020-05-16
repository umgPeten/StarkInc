package umg.negocio;

/**
 *
 * @author mikesb
 */
public class Contacto extends Persona {

    private Proveedor proveedor;

    public Contacto(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Contacto() {
    }

    public Contacto(String nombre, String apellido, String telefono, String direccion, char genero, Identificacion identificacion) {
        super(nombre, apellido, telefono, direccion, genero, identificacion);
    }

    public Contacto(String nombre, String apellido, String telefono, String direccion, char genero, Proveedor proveedor, Identificacion identificacion) {
        super(nombre, apellido, telefono, direccion, genero, identificacion);
        this.proveedor = proveedor;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

}
