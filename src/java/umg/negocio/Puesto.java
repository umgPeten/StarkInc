
package umg.negocio;

public class Puesto {
    private int id_puesto;
    private String puesto;
    private Departamento departamento;

    public Puesto(int id_puesto, String puesto, Departamento departamento) {
        this.id_puesto = id_puesto;
        this.puesto = puesto;
        this.departamento = departamento;
    }
    public Puesto(){
        
    }

    public Puesto(int id_puesto, String puesto) {
        this.id_puesto = id_puesto;
        this.puesto = puesto;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Puesto{" + "id_puesto=" + id_puesto + ", puesto=" + puesto + ", departamento=" + departamento + '}';
    }
    
    
    
    
}
