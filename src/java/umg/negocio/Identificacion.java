package umg.negocio;

/**
 *
 * @author mikesb
 */
public class Identificacion {

    private String identificacion;
    private String tipo_identificacion;

    public Identificacion(String identificacion, String tipo_identificacion) {
        this.identificacion = identificacion;
        this.tipo_identificacion = tipo_identificacion;
    }

    public Identificacion() {
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTipo_identificacion() {
        return tipo_identificacion;
    }

    public void setTipo_identificacion(String tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }

}
