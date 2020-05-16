package umg.negocio;

/**
 *
 * @author mikesb
 */
public class Chequera {
    
    private int id;
    private int stock;
    private int inicio;
    private int fin;

    public Chequera() {
    }

    public Chequera(int id, int stock, int inicio, int fin) {
        this.id = id;
        this.stock = stock;
        this.inicio = inicio;
        this.fin = fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
