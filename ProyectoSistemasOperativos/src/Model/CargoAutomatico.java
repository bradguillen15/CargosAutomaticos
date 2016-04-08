package Model;

public class CargoAutomatico {
    
private String cedulaJuridicaInstitucionCargoAuto;
private String descripcionCargoAuto;
private Double montoCargoCargoAuto;
private Cliente elCliente;


    public CargoAutomatico(String cedulaJuridicaInstitucionCargoAuto, String descripcionCargoAuto, Double montoCargoCargoAuto, Cliente elCliente) {
        this.cedulaJuridicaInstitucionCargoAuto = cedulaJuridicaInstitucionCargoAuto;
        this.descripcionCargoAuto = descripcionCargoAuto;
        this.montoCargoCargoAuto = montoCargoCargoAuto;
        this.elCliente = elCliente;
    }

    public Double getMontoCargoCargoAuto() {
        return montoCargoCargoAuto;
    }

    public void setMontoCargoCargoAuto(Double montoCargoCargoAuto) {
        this.montoCargoCargoAuto = montoCargoCargoAuto;
    }

    public String getCedulaJuridicaInstitucionCargoAuto() {
        return cedulaJuridicaInstitucionCargoAuto;
    }

    public void setCedulaJuridicaInstitucionCargoAuto(String cedulaJuridicaInstitucionCargoAuto) {
        this.cedulaJuridicaInstitucionCargoAuto = cedulaJuridicaInstitucionCargoAuto;
    }

    public String getDescripcionCargoAuto() {
        return descripcionCargoAuto;
    }

    public void setDescripcionCargoAuto(String descripcionCargoAuto) {
        this.descripcionCargoAuto = descripcionCargoAuto;
    }

    public Cliente getElCliente() {
        return elCliente;
    }

    public void setElCliente(Cliente elCliente) {
        this.elCliente = elCliente;
    }
}
