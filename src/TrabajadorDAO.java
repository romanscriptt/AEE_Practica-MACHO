
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Álvaro López De San Román
 */
public class TrabajadorDAO {

    private static final String CARPETA = "Ficheros";
    private static final String NOMBRE_FICHERO = "FICHE.DAT";

    public static File getFichero() {
        return new File(CARPETA + File.separator + NOMBRE_FICHERO);
    }

    public static void escribir(DataOutputStream flujo, Trabajador trabajador) throws IOException {
        flujo.writeUTF(trabajador.getNombreApellidos());
        flujo.writeChar(trabajador.getSexo().getCodigo());
        flujo.writeDouble(trabajador.getSalario());
        flujo.writeShort(trabajador.getFechaIngreso().getAnio());
        flujo.writeByte(trabajador.getFechaIngreso().getMes());
        flujo.writeByte(trabajador.getFechaIngreso().getDia());
        flujo.writeChar(trabajador.getTipoEmpleado().getCodigo());
        flujo.writeByte(trabajador.getProvincia().getCodigo());
    }

    public static Trabajador leer(DataInputStream flujo) throws IOException {
        Trabajador trabajador;
        String nombreApellidos;
        char codigoSexo;
        double salario;
        short anio;
        byte mes;
        byte dia;
        char codigoTipo;
        byte codigoProvincia;

        try {
            nombreApellidos = flujo.readUTF();
            codigoSexo = flujo.readChar();
            salario = flujo.readDouble();
            anio = flujo.readShort();
            mes = flujo.readByte();
            dia = flujo.readByte();
            codigoTipo = flujo.readChar();
            codigoProvincia = flujo.readByte();

            trabajador = new Trabajador(nombreApellidos, Sexo.fromCodigo(codigoSexo), salario,
                    new Fecha(dia, mes, anio),
                    TipoEmpleado.fromCodigo(codigoTipo),
                    Provincia.fromCodigo(codigoProvincia));
        } catch (EOFException e) {
            trabajador = null;
        }
        return trabajador;
    }

}
