
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author Álvaro López De San Román
 * @author Antonio Muñoz Herrera
 */
public class ApartadoCyD {

    public static void main(String[] args) {

        File fichero;
        Trabajador trabajador;
        double sueldo;
        double sueldoMaximo;

        fichero = TrabajadorDAO.getFichero();

        if (!fichero.exists() || fichero.length() == 0) {
            System.out.println("El fichero " + fichero.getPath() + " no existe o está vacío.");
        } else {
            sueldoMaximo = 0;

            try {
                try (DataInputStream entrada = new DataInputStream(new FileInputStream(fichero))) {
                    System.out.println("LISTADO DE SUELDOS DE MACHO S.A.");
                    while ((trabajador = TrabajadorDAO.leer(entrada)) != null) {
                        sueldo = trabajador.getSueldo();
                        System.out.printf("%-30s %9.2f €%n", trabajador.getNombreApellidos(), sueldo);
                        if (sueldo > sueldoMaximo) {
                            sueldoMaximo = sueldo;
                        }
                    }
                }

                try (DataInputStream entrada = new DataInputStream(new FileInputStream(fichero))) {
                    System.out.printf("%nEMPLEADO(S) CON EL SUELDO SUPERIOR (%.2f €):%n", sueldoMaximo);
                    while ((trabajador = TrabajadorDAO.leer(entrada)) != null) {
                        if (trabajador.getSueldo() == sueldoMaximo) {
                            System.out.println(" - " + trabajador.getNombreApellidos());
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Fichero con datos incorrectos: " + e.getMessage());
            }
        }
    }
}
