import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author Álvaro López De San Román 
 */
public class ApartadoA {

    public static void main(String[] args) {
        ejecutar();
    }

    public static void ejecutar() {
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        File archivo = new File("FICHE.DAT");
        FileOutputStream fsalida = null;
        DataOutputStream dataSalida = null;

        String nombre;
        String respuesta;
        char sexo;
        char tipoEmpleado;
        float salario;
        int anio, mes, dia, provincia;
        Trabajador t;

        try {
            fsalida = new FileOutputStream(archivo, true);
            dataSalida = new DataOutputStream(fsalida);

            do {
                System.out.println("========================================================================");
                System.out.println("                            REGISTRO DE TRABAJADOR");
                System.out.println("========================================================================");

                System.out.print("Nombre y apellidos (máximo 30 caracteres): ");
                nombre = teclado.readLine();
                if (nombre.length() > 30) {
                    nombre = nombre.substring(0, 30);
                }

                do {
                    System.out.print("Sexo (M/H): ");
                    respuesta = teclado.readLine().trim().toUpperCase();
                } while (respuesta.isEmpty() || (respuesta.charAt(0) != 'M' && respuesta.charAt(0) != 'H'));
                sexo = respuesta.charAt(0);

                System.out.print("Salario base: ");
                salario = Float.parseFloat(teclado.readLine());

                System.out.print("Año de ingreso: ");
                anio = Integer.parseInt(teclado.readLine());

                do {
                    System.out.print("Mes de ingreso (1-12): ");
                    mes = Integer.parseInt(teclado.readLine());
                } while (mes < 1 || mes > 12);

                int maxDias = 31;
                if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                    maxDias = 30;
                } else if (mes == 2) {
                    if ((anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0)) {
                        maxDias = 29;
                    } else {
                        maxDias = 28;
                    }
                }

                do {
                    System.out.print("Día de ingreso (1-" + maxDias + "): ");
                    dia = Integer.parseInt(teclado.readLine());
                } while (dia < 1 || dia > maxDias);

                do {
                    System.out.print("Tipo de empleado (C: Comisión, F: Fijo, D: A domicilio): ");
                    respuesta = teclado.readLine().trim().toUpperCase();
                } while (respuesta.isEmpty() || (respuesta.charAt(0) != 'C' && respuesta.charAt(0) != 'F' && respuesta.charAt(0) != 'D'));
                tipoEmpleado = respuesta.charAt(0);

                System.out.println("Provincias: 1-Almería, 2-Cádiz, 3-Córdoba, 4-Granada, 5-Huelva, 6-Jaén, 7-Málaga, 8-Sevilla");
                do {
                    System.out.print("Provincia (1-8): ");
                    provincia = Integer.parseInt(teclado.readLine());
                } while (provincia < 1 || provincia > 8);

                t = new Trabajador(nombre, sexo, salario, anio, mes, dia, tipoEmpleado, provincia);
                TrabajadorDAO.escribir(dataSalida, t);

                System.out.println("Trabajador guardado correctamente en FICHE.DAT");

                System.out.print("¿Desea introducir otro trabajador? (S/N): ");
                respuesta = teclado.readLine().trim().toUpperCase();

            } while (respuesta.equals("S"));

        } catch (NumberFormatException e) {
            System.out.println("Error: Formato numérico incorrecto.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        } finally {
            try {
                if (dataSalida != null) dataSalida.close();
                if (fsalida != null) fsalida.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el fichero.");
            }
        }
    }
}
