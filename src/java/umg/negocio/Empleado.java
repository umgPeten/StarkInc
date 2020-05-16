package umg.negocio;

/**
 *
 * @author mikesb
 */
public class Empleado extends Persona {

    private String username;
    private String last_session;

    public Empleado(String username, String last_session) {
        this.username = username;
        this.last_session = last_session;
    }

    public Empleado(String username, String last_session, String nombre, String apellido, String telefono, String direccion, char genero, Identificacion identificacion) {
        super(nombre, apellido, telefono, direccion, genero, identificacion);
        this.username = username;
        this.last_session = last_session;
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

    @Override
    public String toString() {
        return "Empleado{" + "username=" + username + ", last_session=" + last_session + '}';
    }

}
