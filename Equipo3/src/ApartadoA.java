
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

    private static String leerLinea(BufferedReader teclado) throws IOException {
        String linea;

        linea = teclado.readLine();
        if (linea == null) {
            throw new IOException("Se ha cerrado la entrada de datos.");
        }
        return linea.trim();
    }

    private static String leerNombre(BufferedReader teclado) throws IOException {
        String nombre;
        boolean valido;

        valido = false;
        nombre = "";
        while (!valido) {
            System.out.print("Nombre y apellidos (máximo 30 caracteres): ");
            nombre = leerLinea(teclado);
            if (nombre.length() == 0) {
                System.out.println("El nombre no puede estar vacío.");
            } else if (nombre.length() > 30) {
                System.out.println("El nombre no puede tener más de 30 caracteres.");
            } else if (!nombre.matches("[a-zA-Z]+")) {
                System.out.println("El nombre solo puede tener letras y espacios.");
            } else {
                valido = true;
            }
        }
        return nombre;
    }

    private static Sexo leerSexo(BufferedReader teclado) throws IOException {
        Sexo sexo;
        String texto;
        String opciones;
        boolean valido;

        opciones = "";
        for (Sexo s : Sexo.values()) {
            opciones = opciones + s.getCodigo() + " = " + s.getDescripcion() + "   ";
        }

        valido = false;
        sexo = null;
        while (!valido) {
            System.out.print("Sexo (" + opciones.trim() + "): ");
            texto = leerLinea(teclado);
            if (texto.length() == 1) {
                try {
                    sexo = Sexo.fromCodigo(texto.charAt(0));
                    valido = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("El sexo tiene que ser M o H.");
                }
            } else {
                System.out.println("Escribe solo una letra: M o H.");
            }
        }
        return sexo;
    }

    private static double leerSalario(BufferedReader teclado) throws IOException {
        double salario;
        boolean valido;

        valido = false;
        salario = 0;
        while (!valido) {
            System.out.print("Salario (0.01 a 9999.99): ");
            try {
                salario = Double.parseDouble(leerLinea(teclado).replace(',', '.'));
                if (salario > 0 && salario <= 9999.99) {
                    valido = true;
                } else {
                    System.out.println("El salario tiene que estar entre 0.01 y 9999.99.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un número válido.");
            }
        }
        return Math.round(salario * 100) / 100.0;
    }

    private static Fecha leerFecha(BufferedReader teclado) throws IOException {
        Fecha fecha;
        boolean valido;

        valido = false;
        fecha = null;
        while (!valido) {
            System.out.print("Fecha de ingreso (dd/mm/aaaa): ");
            try {
                fecha = new Fecha(leerLinea(teclado));
                if (fecha.esPosteriorA(Fecha.hoy())) {
                    System.out.println("La fecha de ingreso no puede ser futura.");
                } else {
                    valido = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return fecha;
    }

    private static TipoEmpleado leerTipo(BufferedReader teclado) throws IOException {
        TipoEmpleado tipo;
        String texto;
        String opciones;
        boolean valido;

        opciones = "";
        for (TipoEmpleado t : TipoEmpleado.values()) {
            opciones = opciones + t.getCodigo() + " = " + t.getDescripcion() + "   ";
        }

        valido = false;
        tipo = null;
        while (!valido) {
            System.out.print("Tipo de empleado (" + opciones.trim() + "): ");
            texto = leerLinea(teclado);
            if (texto.length() == 1) {
                try {
                    tipo = TipoEmpleado.fromCodigo(texto.charAt(0));
                    valido = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Ese tipo de empleado no existe.");
                }
            } else {
                System.out.println("Escribe solo una letra: C, F o D.");
            }
        }
        return tipo;
    }

    private static Provincia leerProvincia(BufferedReader teclado) throws IOException {
        Provincia provincia;
        String opciones;
        int numero;
        boolean valido;

        opciones = "";
        for (Provincia p : Provincia.values()) {
            opciones = opciones + p.getCodigo() + " = " + p.getNombre() + "   ";
        }

        valido = false;
        provincia = null;
        while (!valido) {
            System.out.println("Provincias: " + opciones.trim());
            System.out.print("Provincia (1-" + Provincia.values().length + "): ");
            try {
                numero = Integer.parseInt(leerLinea(teclado));
                if (numero >= 1 && numero <= Provincia.values().length) {
                    provincia = Provincia.fromCodigo((byte) numero);
                    valido = true;
                } else {
                    System.out.println("La provincia tiene que estar entre 1 y " + Provincia.values().length + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un número válido.");
            }
        }
        return provincia;
    }

    public static void main(String[] args) {

        BufferedReader teclado;
        File fichero;
        File carpeta;
        Trabajador trabajador;
        String respuesta;

        teclado = new BufferedReader(new InputStreamReader(System.in));
        fichero = TrabajadorDAO.getFichero();
        carpeta = fichero.getParentFile();
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        try (DataOutputStream salida = new DataOutputStream(new FileOutputStream(fichero, true))) {
            do {
                System.out.println("--- ALTA DE TRABAJADOR ---");
                trabajador = new Trabajador(leerNombre(teclado), leerSexo(teclado),
                        leerSalario(teclado), leerFecha(teclado),
                        leerTipo(teclado), leerProvincia(teclado));
                TrabajadorDAO.escribir(salida, trabajador);
                System.out.println("Trabajador guardado en " + fichero.getPath());

                System.out.print("¿Desea continuar? (s/n): ");
                respuesta = leerLinea(teclado);
            } while (respuesta.equalsIgnoreCase("s"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}