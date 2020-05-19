package umg.negocio;

import java.util.ArrayList;

/**
 *
 * @author mikesb
 */
public class Departamento {
    private int id_departamento;
    private String departamento;
    private ArrayList<Puesto> puestos;

    public Departamento() {
    }

    public Departamento(int id_departamento, String departamento, ArrayList<Puesto> puestos) {
        this.id_departamento = id_departamento;
        this.departamento = departamento;
        this.puestos = puestos;
    }

    public Departamento(int id_departamento, String departamento) {
        this.id_departamento = id_departamento;
        this.departamento = departamento;
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public ArrayList<Puesto> getPuestos() {
        return puestos;
    }

    public void setPuestos(ArrayList<Puesto> puestos) {
        this.puestos = puestos;
    }

    @Override
    public String toString() {
        return "Departamento{" + "id_departamento=" + id_departamento + ", departamento=" + departamento + ", puestos=" + puestos + '}';
    }
    
    
    
}
