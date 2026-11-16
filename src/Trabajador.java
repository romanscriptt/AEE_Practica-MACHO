
/**
 *
 * @author Álvaro López De San Román
 */
public class Trabajador {

    private static final int ANIOS_TRIENIO = 3;
    private static final double EUROS_TRIENIO = 24.0;
    private static final double PORCENTAJE_DESTINO = 0.10;
    private static final double EXTRA_HOMBRE = 120.0;

    private String nombreApellidos;
    private Sexo sexo;
    private double salario;
    private Fecha fechaIngreso;
    private TipoEmpleado tipoEmpleado;
    private Provincia provincia;

    public Trabajador(String nombreApellidos, Sexo sexo, double salario, Fecha fechaIngreso, TipoEmpleado tipoEmpleado, Provincia provincia) {
        this.nombreApellidos = nombreApellidos;
        this.sexo = sexo;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
        this.tipoEmpleado = tipoEmpleado;
        this.provincia = provincia;
    }

    public String getNombreApellidos() {
        return this.nombreApellidos;
    }

    public Sexo getSexo() {
        return this.sexo;
    }

    public double getSalario() {
        return this.salario;
    }

    public Fecha getFechaIngreso() {
        return this.fechaIngreso;
    }

    public TipoEmpleado getTipoEmpleado() {
        return this.tipoEmpleado;
    }

    public Provincia getProvincia() {
        return this.provincia;
    }

    public int getAntiguedad() {
        return this.fechaIngreso.aniosHasta(Fecha.hoy());
    }

    
    public double getSueldo() {
        double sueldo;

        sueldo = this.salario;
        sueldo = sueldo + (this.getAntiguedad() / ANIOS_TRIENIO) * EUROS_TRIENIO;
        if (this.provincia == Provincia.JAEN || this.provincia == Provincia.HUELVA
                || this.provincia == Provincia.ALMERIA) {
            sueldo = sueldo + this.salario * PORCENTAJE_DESTINO;
        }
        if (this.sexo == Sexo.H) {
            sueldo = sueldo + EXTRA_HOMBRE;
        }
        return Math.round(sueldo * 100) / 100.0;
    }

    @Override
    public String toString() {
        return this.nombreApellidos + " | Sexo: " + this.sexo.getDescripcion() + " | Salario: " + this.salario + "€" + " | Ingreso: " + this.fechaIngreso + " | Tipo: " + this.tipoEmpleado.getDescripcion() + " | Provincia: " + this.provincia.getNombre();
    }
}
