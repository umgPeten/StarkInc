package umg.negocio;

/**
 *
 * @author mikesb
 */
public class Empleado extends Persona {
    private int id_empleado;
    private String username;
    private String last_session;
    private Puesto puesto;

    public Empleado(String username, String last_session) {
        this.username = username;
        this.last_session = last_session;
    }
    
    public Empleado(String nombre, String apellido, String telefono, String direccion, int idgenero, Identificacion identificacion, String fechaNac, Puesto puesto) {
        super(nombre, apellido, telefono, direccion, idgenero, identificacion, fechaNac);
        this.puesto = puesto;

    }
    
    public Empleado(String username, String last_session, String nombre, String apellido, String telefono, String direccion, char genero, Identificacion identificacion, String fechaNac) {
        super(nombre, apellido, telefono, direccion, genero, identificacion, fechaNac);
        this.username = username;
        this.last_session = last_session;
    }

    public Empleado() {
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }
    
    
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLast_session() {
        return last_session;
    }

    public void setLast_session(String last_session) {
        this.last_session = last_session;
    }

    

}
