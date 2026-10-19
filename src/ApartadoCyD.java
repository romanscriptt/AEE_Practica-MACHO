import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Álvaro López De San Román y Antonio Muñoz Herrera
 */
public class ApartadoCyD {

    private static File archivo;
    private static FileInputStream entrada;
    private static DataInputStream dataEntrada;

    public static void main(String[] args) {
        archivo = new File("FICHE.DAT");

        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("El archivo FICHE.DAT no existe o está vacío.");
            return;
        }

        System.out.println("=========================================================================================================");
        System.out.println("                                LISTADO DE SUELDOS TOTALES EN MACHO S.A.");
        System.out.println("=========================================================================================================");

        int contador = 0;
        Calendar fechaActual = Calendar.getInstance();

        double maxSueldo = -1.0;
        List<String> empleadosSueldoMaximo = new ArrayList<String>();

        try {
            entrada = new FileInputStream(archivo);
            dataEntrada = new DataInputStream(entrada);

            while (!TrabajadorDAO.ff()) {
                Trabajador t = TrabajadorDAO.leer(dataEntrada);

                if (t != null && !TrabajadorDAO.ff()) {
                    contador++;

                    double salarioBase = t.getSalario();

                    int aniosAntiguedad = 0;
                    if (t.getAnoIngreso() > 1900 && t.getMesIngreso() >= 1 && t.getMesIngreso() <= 12 
                            && t.getDiaIngreso() >= 1 && t.getDiaIngreso() <= 31) {
                        
                        Calendar fechaIngreso = new GregorianCalendar(t.getAnoIngreso(), t.getMesIngreso() - 1, t.getDiaIngreso());
                        aniosAntiguedad = fechaActual.get(Calendar.YEAR) - fechaIngreso.get(Calendar.YEAR);

                        if (fechaActual.get(Calendar.MONTH) < fechaIngreso.get(Calendar.MONTH) || 
                           (fechaActual.get(Calendar.MONTH) == fechaIngreso.get(Calendar.MONTH) && 
                            fechaActual.get(Calendar.DAY_OF_MONTH) < fechaIngreso.get(Calendar.DAY_OF_MONTH))) {
                            aniosAntiguedad--;
                        }
                    } else {
                        aniosAntiguedad = fechaActual.get(Calendar.YEAR) - t.getAnoIngreso();
                    }

                    if (aniosAntiguedad < 0) {
                        aniosAntiguedad = 0;
                    }

                    double trienios = (aniosAntiguedad / 3) * 24.0;

                    double compDestino = 0.0;
                    Provincia prov = t.getProvincia();
                    if (prov == Provincia.ALMERIA || prov == Provincia.HUELVA || prov == Provincia.JAEN) {
                        compDestino = salarioBase * 0.10;
                    }

                    double compGenero = (Character.toUpperCase(t.getSexo()) == 'H') ? 120.0 : 0.0;

                    double sueldoTotal = salarioBase + trienios + compDestino + compGenero;

                    System.out.printf("%-3d | %-28s | Base: %8.2f€ | Trienios: %6.2f€ | Destino: %6.2f€ | Género: %6.2f€ | TOTAL: %8.2f€%n",
                            contador, t.getNombreApe(), salarioBase, trienios, compDestino, compGenero, sueldoTotal);


                    double sueldoRedondeado = Math.round(sueldoTotal * 100.0) / 100.0;

                    if (sueldoRedondeado > maxSueldo) {
                        maxSueldo = sueldoRedondeado;
                        empleadosSueldoMaximo.clear(); 
                        empleadosSueldoMaximo.add(t.getNombreApe());
                    } else if (sueldoRedondeado == maxSueldo) {
                        empleadosSueldoMaximo.add(t.getNombreApe()); 
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: No se encontró el fichero FICHE.DAT.");
        } catch (IOException e) {
            System.err.println("Error de lectura al procesar el archivo: " + e.getMessage());
        } finally {

            try {
                if (dataEntrada != null) {
                    dataEntrada.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar los flujos de lectura: " + e.getMessage());
            }
        }

        System.out.println("=========================================================================================================");
        System.out.println("Procesamiento de sueldos finalizado correctamente. Total trabajadores analizados: " + contador);
        System.out.println("=========================================================================================================");

        System.out.println("\n=========================================================================================================");
        System.out.println("                                RESULTADO DEL APARTADO D: SUELDO MÁXIMO");
        System.out.println("=========================================================================================================");
        if (contador > 0 && maxSueldo >= 0) {
            System.out.printf("Sueldo superior registrado: %.2f€%n", maxSueldo);
            System.out.println("Empleado(s) con el sueldo más alto:");
            for (String nombre : empleadosSueldoMaximo) {
                System.out.println(" - " + nombre);
            }
        } else {
            System.out.println("No se procesaron registros para calcular el sueldo máximo.");
        }
        System.out.println("=========================================================================================================\n");
    }
}
