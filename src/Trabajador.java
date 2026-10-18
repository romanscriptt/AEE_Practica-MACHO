/**
 *
 * @author Álvaro López De San Román
 */
public class Trabajador {

    private String nombre;
    private char sexo;
    private float salario;
    private int anio;
    private int mes;
    private int dia;
    private char tipoEmpleado;
    private int provincia;

    public Trabajador() {
    }

    public Trabajador(String nombre, char sexo, float salario, int anio, int mes, 
                      int dia, char tipoEmpleado, int provincia) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.salario = salario;
        this.anio = anio;
        this.mes = mes;
        this.dia = dia;
        this.tipoEmpleado = tipoEmpleado;
        this.provincia = provincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public char getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(char tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public int getProvincia() {
        return provincia;
    }

    public void setProvincia(int provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return nombre + " | Sexo: " + sexo + " | Salario: " + salario + "€ | Fecha: " 
                + dia + "/" + mes + "/" + anio + " | Tipo: " + tipoEmpleado + " | Prov: " + provincia;
    }
}
