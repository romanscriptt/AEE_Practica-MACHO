import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Álvaro López De San Román 
 */
public class MenuPrincipal {

    public static void main(String[] args) {
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        int opcion = -1;

        do {
            System.out.println("========================================================================");
            System.out.println("                   GESTION DE PLANTILLA - MACHO S.A.");
            System.out.println("========================================================================");
            System.out.println("1. Alta de nuevo trabajador (Apartado A)");
            System.out.println("2. Listado de antigüedad >= 10 años (Apartado B)");
            System.out.println("3. Listado de sueldos de la plantilla (Apartado C)");
            System.out.println("4. Empleado(s) con sueldo más alto (Apartado D)");
            System.out.println("5. Provincia con mayor número de empleados (Apartado E)");
            System.out.println("0. Salir");
            System.out.println("========================================================================");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(teclado.readLine());

                switch (opcion) {
                    case 1:
                        ApartadoA.ejecutar();
                        break;
                    case 2:
                        ApartadoB.ejecutar();
                        break;
                    case 3:
                        ApartadoC.ejecutar();
                        break;
                    case 4:
                        ApartadoD.ejecutar();
                        break;
                    case 5:
                        ApartadoE.ejecutar();
                        break;
                    case 0:
                        System.out.println("Saliendo de la aplicación...");
                        break;
                    default:
                        System.out.println("Opción no válida. Introduzca un número entre 0 y 5.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Debe introducir un número entero.");
            } catch (IOException e) {
                System.out.println("Error de entrada/salida: " + e.getMessage());
            }

        } while (opcion != 0);
    }
}
