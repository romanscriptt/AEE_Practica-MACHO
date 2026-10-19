/**
 * @author Álvaro López De San Román / Antonio Muñoz Herrera
 */
public class Trabajador {

    private String nombreApe;
    private char sexo;
    private double salario;
    private short anoIngreso;
    private byte mesIngreso;
    private byte diaIngreso;
    private TipoEmpleado tipoEmpleado;
    private Provincia provincia;

    public Trabajador() {
    }

    public Trabajador(String nombreApe, char sexo, double salario, short anoIngreso, 
                      byte mesIngreso, byte diaIngreso, TipoEmpleado tipoEmpleado, Provincia provincia) {
        this.nombreApe = nombreApe;
        this.sexo = sexo;
        this.salario = salario;
        this.anoIngreso = anoIngreso;
        this.mesIngreso = mesIngreso;
        this.diaIngreso = diaIngreso;
        this.tipoEmpleado = tipoEmpleado;
        this.provincia = provincia;
    }

    public String getNombreApe() {
        return nombreApe;
    }

    public void setNombreApe(String nombreApe) {
        this.nombreApe = nombreApe;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public short getAnoIngreso() {
        return anoIngreso;
    }

    public void setAnoIngreso(short anoIngreso) {
        this.anoIngreso = anoIngreso;
    }

    public byte getMesIngreso() {
        return mesIngreso;
    }

    public void setMesIngreso(byte mesIngreso) {
        this.mesIngreso = mesIngreso;
    }

    public byte getDiaIngreso() {
        return diaIngreso;
    }

    public void setDiaIngreso(byte diaIngreso) {
        this.diaIngreso = diaIngreso;
    }

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return nombreApe + " | Sexo: " + sexo + " | Salario: " + salario + "€ | Fecha: " 
                + diaIngreso + "/" + mesIngreso + "/" + anoIngreso + " | Tipo: " + tipoEmpleado 
                + " | Prov: " + provincia;
    }
}
