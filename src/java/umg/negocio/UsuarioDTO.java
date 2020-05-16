package umg.negocio;
/**
 *
 * @author mikesb
 */
public class UsuarioDTO {
    private String fullname;
    private int id_rol;
    private Identificacion identificacion;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String fullname, int id_rol, Identificacion identificacion) {
        this.fullname = fullname;
        this.id_rol = id_rol;
        this.identificacion = identificacion;
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

    public Identificacion getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Identificacion identificacion) {
        this.identificacion = identificacion;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" + "fullname=" + fullname + ", id_rol=" + id_rol + ", identificacion=" + identificacion + '}';
    }
    
    
}
