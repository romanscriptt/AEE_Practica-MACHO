import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 *
 * @author Antonio Muñoz Herrera
 */
public class ApartadoB {

    private static File archivo;
    private static FileInputStream fsalida; 
    private static DataInputStream dataSalida;

    public static void ejecutar() {
        archivo = new File("FICHE.DAT");

        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("El archivo FICHE.DAT no existe o está vacío.");
            return;
        }

        int totalTrabajadores = 0;
        int masDeDiezAnios = 0;

        Calendar fechaActual = Calendar.getInstance();

        System.out.println("========================================================================");
        System.out.println("     LISTADO DE EMPLEADOS CON 10 O MÁS AÑOS DE ANTIGÜEDAD EN MACHO S.A.");
        System.out.println("========================================================================");
        System.out.printf("%-30s | %-12s | %-10s%n", "NOMBRE Y APELLIDOS", "FECHA INGRESO", "ANTIGÜEDAD");
        System.out.println("------------------------------------------------------------------------");

        try {
            fsalida = new FileInputStream(archivo);
            dataSalida = new DataInputStream(fsalida);

            boolean eof = false; 

            while (!eof) {
                try {
                    Trabajador t = TrabajadorDAO.leer(dataSalida);
                    totalTrabajadores++;

                    if (t.getAnio() > 1900 && t.getMes() >= 1 && t.getMes() <= 12 && t.getDia() >= 1 && t.getDia() <= 31) {
                        
                        Calendar fechaIngreso = new GregorianCalendar(t.getAnio(), t.getMes() - 1, t.getDia());

                        int aniosAntiguedad = fechaActual.get(Calendar.YEAR) - fechaIngreso.get(Calendar.YEAR);

                        if (fechaActual.get(Calendar.MONTH) < fechaIngreso.get(Calendar.MONTH) || 
                           (fechaActual.get(Calendar.MONTH) == fechaIngreso.get(Calendar.MONTH) && 
                            fechaActual.get(Calendar.DAY_OF_MONTH) < fechaIngreso.get(Calendar.DAY_OF_MONTH))) {
                            aniosAntiguedad--;
                        }

                        if (aniosAntiguedad >= 10) {
                            masDeDiezAnios++;
                            System.out.printf("%-30s | %02d/%02d/%04d    | %d años%n",
                                    t.getNombre(), t.getDia(), t.getMes(), t.getAnio(), aniosAntiguedad);
                        }
                    } else {
                        System.err.println("Advertencia: Se omitió un registro con fecha no válida para: " + t.getNombre());
                    }

                } catch (EOFException e) {
                    eof = true;
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: No se encontró el fichero FICHE.DAT.");
        } catch (IOException e) {
            System.err.println("Error de lectura al procesar el archivo: " + e.getMessage());
        } finally {
            try {
                if (dataSalida != null) {
                    dataSalida.close();
                }
                if (fsalida != null) {
                    fsalida.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar los flujos de lectura: " + e.getMessage());
            }
        }

        System.out.println("========================================================================");
        if (totalTrabajadores > 0) {
            double porcentaje = ((double) masDeDiezAnios / totalTrabajadores) * 100;
            System.out.printf("Total trabajadores analizados: %d%n", totalTrabajadores);
            System.out.printf("Trabajadores con 10 o más años: %d%n", masDeDiezAnios);
            System.out.printf("Porcentaje sobre el total: %.2f%%%n", porcentaje);
        } else {
            System.out.println("No se procesaron registros válidos en el archivo.");
        }
        System.out.println("========================================================================");
    }
}