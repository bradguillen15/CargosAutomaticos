package proyectosistemasoperativos;

public class Cliente {

    private final String cedula;
    private final String nombre;
    private final String apellido;
    private final String correo;
    private final String numeroTarjeta;
    private final String CVV;
    private double saldo;

    public Cliente(String cedula, String nombre, String apellido, String correo, String numeroTarjeta, double saldo, String CVV) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.numeroTarjeta = numeroTarjeta;
        this.CVV = CVV;
        this.saldo = saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getCVV() {
        return CVV;
    }

    public double getSaldo() {
        return saldo;
    }
}
