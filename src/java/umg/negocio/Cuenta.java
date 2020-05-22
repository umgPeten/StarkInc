package umg.negocio;

/**
 *
 * @author mikesb
 */
public class Cuenta {
    private String numero_cuenta;
    private String nombre;
    private double fondos;
    private String moneda;
    private String tipo_cuenta;
    private Banco banco;
    private Chequera chequera;

    public Cuenta() {
    }

    public Cuenta(String numero_cuenta, String nombre, double fondos, String moneda) {
        this.numero_cuenta = numero_cuenta;
        this.nombre = nombre;
        this.fondos = fondos;
        this.moneda = moneda;
    }

    public Cuenta(String numero_cuenta, String nombre, double fondos, String moneda, Banco banco) {
        this.numero_cuenta = numero_cuenta;
        this.nombre = nombre;
        this.fondos = fondos;
        this.moneda = moneda;
        this.banco = banco;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
    
    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getFondos() {
        return fondos;
    }

    public void setFondos(double fondos) {
        this.fondos = fondos;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Chequera getChequera() {
        return chequera;
    }

    public void setChequera(Chequera chequera) {
        this.chequera = chequera;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "numero_cuenta=" + numero_cuenta + ", nombre=" + nombre + ", fondos=" + fondos + ", moneda=" + moneda + '}';
    }
    
    
}
