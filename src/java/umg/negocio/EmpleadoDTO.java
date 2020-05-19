package umg.negocio;

/**
 *
 * @author mikesb
 */
public class EmpleadoDTO {
    
    private int identificacion;
    private int tipo_id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private int id_genero;
    private int id_puesto;
    private String fecha_nac;

    public EmpleadoDTO(int identificacion, int tipo_id, String nombre, String apellido, String telefono, String direccion, int id_genero, int id_puesto, String fecha_nac) {
        this.identificacion = identificacion;
        this.tipo_id = tipo_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.id_genero = id_genero;
        this.id_puesto = id_puesto;
        this.fecha_nac = fecha_nac;
    }

    public EmpleadoDTO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public int getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(int tipo_id) {
        this.tipo_id = tipo_id;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId_genero() {
        return id_genero;
    }

    public void setId_genero(int id_genero) {
        this.id_genero = id_genero;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }
    
    
}
