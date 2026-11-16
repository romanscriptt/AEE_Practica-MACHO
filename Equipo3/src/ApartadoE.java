
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author Álvaro López De San Román
 */
public class ApartadoE {

    public static void main(String[] args) {

        File fichero;
        Trabajador trabajador;
        int[] empleadosPorProvincia;
        int maximo;
        int posicion;

        fichero = TrabajadorDAO.getFichero();

        if (!fichero.exists() || fichero.length() == 0) {
            System.out.println("El fichero " + fichero.getPath() + " no existe o está vacío.");
        } else {
            empleadosPorProvincia = new int[Provincia.values().length];

            try (DataInputStream entrada = new DataInputStream(new FileInputStream(fichero))) {
                while ((trabajador = TrabajadorDAO.leer(entrada)) != null) {
                    empleadosPorProvincia[trabajador.getProvincia().ordinal()]++;
                }

                maximo = 0;
                for (posicion = 0; posicion < empleadosPorProvincia.length; posicion++) {
                    if (empleadosPorProvincia[posicion] > maximo) {
                        maximo = empleadosPorProvincia[posicion];
                    }
                }

                System.out.println("PROVINCIA(S) CON MAYOR NÚMERO DE EMPLEADOS (" + maximo + "):");
                for (posicion = 0; posicion < empleadosPorProvincia.length; posicion++) {
                    if (empleadosPorProvincia[posicion] == maximo) {
                        System.out.println(" - " + Provincia.values()[posicion].getNombre());
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
