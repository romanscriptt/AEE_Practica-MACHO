
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Álvaro López De San Román
 */
public class Fecha {

    // Atributos:
    private static final int ANIO_MINIMO = 1900;
    private static final int ANIO_MAXIMO = 9999;

    private byte dia;
    private byte mes;
    private short anio;

    public Fecha(int dia, int mes, int anio) {
        validar(dia, mes, anio);
        this.dia = (byte) dia;
        this.mes = (byte) mes;
        this.anio = (short) anio;
    }

    public Fecha(String texto) {
        String[] partes;
        int diaLeido;
        int mesLeido;
        int anioLeido;

        if (texto == null || !texto.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
            throw new IllegalArgumentException("El formato tiene que ser dd/mm/aaaa. Ejemplo: 05/03/2015");
        }
        partes = texto.split("/");
        diaLeido = Integer.parseInt(partes[0]);
        mesLeido = Integer.parseInt(partes[1]);
        anioLeido = Integer.parseInt(partes[2]);

        validar(diaLeido, mesLeido, anioLeido);
        this.dia = (byte) diaLeido;
        this.mes = (byte) mesLeido;
        this.anio = (short) anioLeido;
    }

    public byte getDia() {
        return this.dia;
    }

    public byte getMes() {
        return this.mes;
    }

    public short getAnio() {
        return this.anio;
    }

    public static boolean esBisiesto(int anio) {
        return (anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0;
    }

    public static int diasDelMes(int mes, int anio) {
        int dias;

        switch (mes) {
            case 4:
            case 6:
            case 9:
            case 11:
                dias = 30;
                break;
            case 2:
                if (esBisiesto(anio)) {
                    dias = 29;
                } else {
                    dias = 28;
                }
                break;
            default:
                dias = 31;
                break;
        }
        return dias;
    }

    private static void validar(int dia, int mes, int anio) {
        if (anio < ANIO_MINIMO || anio > ANIO_MAXIMO) {
            throw new IllegalArgumentException("El año tiene que estar entre " + ANIO_MINIMO + " y " + ANIO_MAXIMO + ".");
        }
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("El mes tiene que estar entre 1 y 12.");
        }
        if (dia < 1 || dia > diasDelMes(mes, anio)) {
            throw new IllegalArgumentException("Ese día no existe en ese mes.");
        }
    }

    public static Fecha hoy() {
        GregorianCalendar ahora;

        ahora = new GregorianCalendar();
        return new Fecha(ahora.get(Calendar.DAY_OF_MONTH),
                ahora.get(Calendar.MONTH) + 1,
                ahora.get(Calendar.YEAR));
    }

    public boolean esPosteriorA(Fecha otra) {
        boolean posterior;

        if (this.anio != otra.anio) {
            posterior = this.anio > otra.anio;
        } else if (this.mes != otra.mes) {
            posterior = this.mes > otra.mes;
        } else {
            posterior = this.dia > otra.dia;
        }//Fin Si
        return posterior;
    }

    public int aniosHasta(Fecha otra) {
        int anios;

        anios = otra.anio - this.anio;
        if (otra.mes < this.mes || (otra.mes == this.mes && otra.dia < this.dia)) {
            anios--;
        }
        return anios;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", this.dia, this.mes, this.anio);
    }

}
