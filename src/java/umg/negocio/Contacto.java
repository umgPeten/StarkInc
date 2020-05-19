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


    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

}
