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

                do {
                    System.out.print("Nombre y apellidos (máximo 30 caracteres): ");
                    nombre = teclado.readLine().trim();
                    if (nombre.length() > 30) {
                        nombre = nombre.substring(0, 30);
                    }
                } while (nombre.isEmpty() || !nombre.matches("[a-zA-Z ]+"));

                do {
                    System.out.print("Sexo (M/H): ");
                    respuesta = teclado.readLine().trim().toUpperCase();
                } while (respuesta.isEmpty() || (respuesta.charAt(0) != 'M' && respuesta.charAt(0) != 'H'));
                sexo = respuesta.charAt(0);

                do {
                    System.out.print("Salario base: ");
                    try {
                        salario = Float.parseFloat(teclado.readLine().trim().replace(',', '.'));
                    } catch (NumberFormatException e) {
                        salario = -1;
                    }
                } while (salario <= 0);

                do {
                    System.out.print("Año de ingreso: ");
                    try {
                        anio = Integer.parseInt(teclado.readLine().trim());
                    } catch (NumberFormatException e) {
                        anio = -1;
                    }
                } while (anio <= 0 || anio > 2026);

                do {
                    System.out.print("Mes de ingreso (1-12): ");
                    try {
                        mes = Integer.parseInt(teclado.readLine().trim());
                    } catch (NumberFormatException e) {
                        mes = -1;
                    }
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
                    try {
                        dia = Integer.parseInt(teclado.readLine().trim());
                    } catch (NumberFormatException e) {
                        dia = -1;
                    }
                } while (dia < 1 || dia > maxDias);

                do {
                    System.out.print("Tipo de empleado (C: Comisión, F: Fijo, D: A domicilio): ");
                    respuesta = teclado.readLine().trim().toUpperCase();
                } while (respuesta.isEmpty() || (respuesta.charAt(0) != 'C' && respuesta.charAt(0) != 'F' && respuesta.charAt(0) != 'D'));
                tipoEmpleado = respuesta.charAt(0);

                System.out.println("Provincias: 1-Almería, 2-Cádiz, 3-Córdoba, 4-Granada, 5-Huelva, 6-Jaén, 7-Málaga, 8-Sevilla");
                do {
                    System.out.print("Provincia (1-8): ");
                    try {
                        provincia = Integer.parseInt(teclado.readLine().trim());
                    } catch (NumberFormatException e) {
                        provincia = -1;
                    }
                } while (provincia < 1 || provincia > 8);

                t = new Trabajador(nombre, sexo, salario, anio, mes, dia, tipoEmpleado, provincia);
                TrabajadorDAO.escribir(dataSalida, t);

                System.out.println("Trabajador guardado correctamente en FICHE.DAT");

                System.out.print("¿Desea introducir otro trabajador? (S/N): ");
                respuesta = teclado.readLine().trim().toUpperCase();

            } while (respuesta.equals("S"));

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
