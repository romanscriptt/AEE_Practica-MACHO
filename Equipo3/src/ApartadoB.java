
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Álvaro López De San Román
 */

public class ApartadoB {

    private static final int ANIOS_MINIMOS = 10;
    private static final int LINEAS_POR_PAGINA = 5;

    public static void main(String[] args) {

        BufferedReader teclado;
        File fichero;
        Trabajador trabajador;
        int totalTrabajadores;
        int totalAntiguos;
        int lineasEnPagina;
        int numeroPagina;
        double porcentaje;

        teclado = new BufferedReader(new InputStreamReader(System.in));
        fichero = TrabajadorDAO.getFichero();

        if (!fichero.exists() || fichero.length() == 0) {
            System.out.println("El fichero " + fichero.getPath() + " no existe o está vacío.");
        } else {
            totalTrabajadores = 0;
            totalAntiguos = 0;
            lineasEnPagina = 0;
            numeroPagina = 1;

            try (DataInputStream entrada = new DataInputStream(new FileInputStream(fichero))) {
                System.out.println("EMPLEADOS CON " + ANIOS_MINIMOS + " O MÁS AÑOS EN MACHO S.A.");
                System.out.println("--- Página " + numeroPagina + " ---");

                while ((trabajador = TrabajadorDAO.leer(entrada)) != null) {
                    totalTrabajadores++;
                    if (trabajador.getAntiguedad() >= ANIOS_MINIMOS) {
                        totalAntiguos++;
                        if (lineasEnPagina == LINEAS_POR_PAGINA) {
                            System.out.println("--- Pulse Enter para ver la página siguiente ---");
                            teclado.readLine();
                            numeroPagina++;
                            lineasEnPagina = 0;
                            System.out.println("--- Página " + numeroPagina + " ---");
                        }
                        System.out.printf("%-30s | Ingreso: %s | %d años%n",
                                trabajador.getNombreApellidos(), trabajador.getFechaIngreso(),
                                trabajador.getAntiguedad());
                        lineasEnPagina++;
                    }
                }

                System.out.println("Trabajadores en la empresa: " + totalTrabajadores);
                System.out.println("Con " + ANIOS_MINIMOS + " o más años: " + totalAntiguos);
                porcentaje = totalAntiguos * 100.0 / totalTrabajadores;
                System.out.printf("Porcentaje sobre el total: %.2f %%%n", porcentaje);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Fichero con datos incorrectos: " + e.getMessage());
            }
        }
    }
}
