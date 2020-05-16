package umg.negocio;

import java.util.ArrayList;

/**
 *
 * @author mikesb
 */
public class Banco {
    private int id;
    private String nombre;
    private ArrayList<Cuenta> cuentas;

    public Banco(int id, String nombre, ArrayList<Cuenta> cuentas) {
        this.id = id;
        this.nombre = nombre;
        this.cuentas = cuentas;
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

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
    
    public void addCuenta(Cuenta cuenta){
        cuentas.add(cuenta);
    }
    
    @Override
    public String toString() {
        return "Banco{" + "id=" + id + ", nombre=" + nombre + ", cuentas=" + cuentas + '}';
    }
}
