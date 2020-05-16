package umg.negocio;

/**
 *
 * @author mikesb
 */
public class Proveedor {

    private int id;
    private String nombre;

    public Proveedor() {
    }

    public Proveedor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    
    

}
