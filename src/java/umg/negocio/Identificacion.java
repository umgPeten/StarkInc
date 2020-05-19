package umg.negocio;

/**
 *
 * @author mikesb
 */
public class Identificacion {

    private int num_id;
    private int tipo_identificacion;

    public Identificacion(int num_id, int tipo_identificacion) {
        this.num_id = num_id;
        this.tipo_identificacion = tipo_identificacion;
    }

    public Identificacion() {
    }

    public int getNum_id() {
        return num_id;
    }

    public void setIdentificacion(int num_id) {
        this.num_id = num_id;
    }

    public int getTipo_identificacion() {
        return tipo_identificacion;
    }

    public void setTipo_identificacion(int tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }

}
