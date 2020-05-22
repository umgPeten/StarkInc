package umg.negocio;

/**
 *
 * @author mikesb
 */
public class Chequera {
    
    private String id;
    private int stock;
    private int inicio;
    private int fin;
    private Cuenta cuenta;

    public Chequera() {
    }

    public Chequera(String id, int stock, int inicio, int fin, Cuenta cuenta) {
        this.id = id;
        this.stock = stock;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }
  
}
