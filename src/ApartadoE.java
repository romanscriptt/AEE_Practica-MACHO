import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Antonio Muñoz Herrera
 */
public class ApartadoE {
    private static File archivo;
    private static FileInputStream entrada;
    private static DataInputStream dataEntrada;
    private static int[] contaProvincias = new int[9]; // Índices del 1 al 8 para las provincias de Andalucía
    private static String[] nombreProvincias = new String[]{
        "",          // Índice 0 no utilizado
        "Almería",   // Código 1
        "Cádiz",     // Código 2
        "Córdoba",   // Código 3
        "Granada",   // Código 4
        "Huelva",    // Código 5
        "Jaén",      // Código 6
        "Málaga",    // Código 7
        "Sevilla"    // Código 8
    };

    public static void ejecutar() {
        archivo = new File("FICHE.DAT");

        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("El archivo FICHE.DAT no existe o está vacío.");
            return;
        }

        for (int i = 0; i < contaProvincias.length; i++) {
            contaProvincias[i] = 0;
        }

        int totalTrabajadores = 0;
        boolean eof = false; 

        try {
            entrada = new FileInputStream(archivo);
            dataEntrada = new DataInputStream(entrada);

            while (!eof) {
                try {
                    Trabajador t = TrabajadorDAO.leer(dataEntrada);
                    totalTrabajadores++;

                    int codProvincia = t.getProvincia();
                    if (codProvincia >= 1 && codProvincia <= 8) {
                        contaProvincias[codProvincia]++;
                    } else {
                        System.err.println("Advertencia: Código de provincia inválido (" 
                                + codProvincia + ") para el trabajador " + t.getNombre());
                    }

                } catch (EOFException e) {
                    eof = true;
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: No se encontró el fichero FICHE.DAT.");
        } catch (IOException e) {
            System.err.println("Error de lectura en el archivo: " + e.getMessage());
        } finally {
            try {
                if (dataEntrada != null) {
                    dataEntrada.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar los flujos de datos: " + e.getMessage());
            }
        }

        System.out.println("========================================================================");
        System.out.println("        PROVINCIA(S) CON MAYOR NÚMERO DE EMPLEADOS EN MACHO S.A.");
        System.out.println("========================================================================");

        if (totalTrabajadores > 0) {
            int maxEmpleados = 0;
            for (int i = 1; i <= 8; i++) {
                if (contaProvincias[i] > maxEmpleados) {
                    maxEmpleados = contaProvincias[i];
                }
            }

            if (maxEmpleados > 0) {
                System.out.println("Número máximo de empleados en una provincia: " + maxEmpleados);
                System.out.println("Provincia(s) con mayor número de empleados:");
                
                for (int i = 1; i <= 8; i++) {
                    if (contaProvincias[i] == maxEmpleados) {
                        System.out.println(" - " + nombreProvincias[i] + " (" + maxEmpleados + " empleados)");
                    }
                }
            } else {
                System.out.println("No se registraron empleados en provincias válidas.");
            }
        } else {
            System.out.println("No se leyó ningún registro de trabajador en el fichero.");
        }
        
        System.out.println("========================================================================");
    }
}
