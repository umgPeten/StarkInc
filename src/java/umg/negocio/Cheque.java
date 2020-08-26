package umg.negocio;

import java.sql.Date;

/**
 *
 * @author mikesb
 */
public class Cheque {
    
    private String id;
    private double monto;
    private String montoLetras;
    private String fecha;
    private String lugar;
    private Chequera chequera;
    private String beneficiario;
    private String estado;

    public Cheque() {
    }

    public Cheque(String id, double monto, String montoLetras, String fecha, String lugar, Chequera chequera) {
        this.id = id;
        this.monto = monto;
        this.montoLetras = montoLetras;
        this.fecha = fecha;
        this.lugar = lugar;
        this.chequera = chequera;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getMontoLetras() {
        return montoLetras;
    }

    public void setMontoLetras(String montoLetras) {
        this.montoLetras = montoLetras;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Chequera getChequera() {
        return chequera;
    }

    public void setChequera(Chequera chequera) {
        this.chequera = chequera;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    

    @Override
    public String toString() {
        return "Cheque{" + "id=" + id + ", monto=" + monto + ", montoLetras=" + montoLetras + ", fecha=" + fecha + ", lugar=" + lugar + ", chequera=" + chequera + '}';
    }
    
    
    
}
