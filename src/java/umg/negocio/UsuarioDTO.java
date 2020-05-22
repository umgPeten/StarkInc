package umg.negocio;

/**
 *
 * @author mikesb
 */
public class UsuarioDTO {

    private String fullname;
    private String nombre;
    private String apellido;
    private String usernamae;
    private int id_rol;
    private int id_empleado;
    private String rol;
    private int montoMinimo;
    private int montoMaximo;
    private Departamento departamento;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String fullname, int id_rol, String rol) {
        this.fullname = fullname;
        this.id_rol = id_rol;
        this.rol = rol;
    }

    public String getUsernamae() {
        return usernamae;
    }

    public void setUsernamae(String usernamae) {
        this.usernamae = usernamae;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public int getMontoMinimo() {
        return montoMinimo;
    }

    public void setMontoMinimo(int montoMinimo) {
        this.montoMinimo = montoMinimo;
    }

    public int getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(int montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
    
    
    

    @Override
    public String toString() {
        return "UsuarioDTO{" + "fullname=" + fullname + ", usernamae=" + usernamae + ", id_rol=" + id_rol + ", id_empleado=" + id_empleado + ", rol=" + rol + ", montoMinimo=" + montoMinimo + ", montoMaximo=" + montoMaximo + '}';
    }
    
    
    
    
    
  
    
    

}
