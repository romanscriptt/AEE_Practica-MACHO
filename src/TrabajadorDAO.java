import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * @author Álvaro López De San Román / Antonio Muñoz Herrera
 */
public class TrabajadorDAO {

    private static boolean esFinFichero = false;

    public static boolean ff() {
        return esFinFichero;
    }

    public static void escribir(DataOutputStream data, Trabajador t) throws IOException {
        data.writeUTF(t.getNombreApe());
        data.writeChar(t.getSexo());
        data.writeDouble(t.getSalario());
        data.writeShort(t.getAnoIngreso());
        data.writeByte(t.getMesIngreso());
        data.writeByte(t.getDiaIngreso());
        data.writeChar(t.getTipoEmpleado().getCodigo());
        data.writeByte(t.getProvincia().getCodigo());
    }

    public static Trabajador leer(DataInputStream data) throws EOFException, IOException {
        try {
            String nombreApe = data.readUTF();
            char sexo = data.readChar();
            double salario = data.readDouble();
            short anoIngreso = data.readShort();
            byte mesIngreso = data.readByte();
            byte diaIngreso = data.readByte();
            char codTipo = data.readChar();
            byte codProv = data.readByte();

            TipoEmpleado tipoEmpleado = TipoEmpleado.fromCodigo(codTipo);
            Provincia provincia = Provincia.fromCodigo(codProv);

            esFinFichero = false;
            return new Trabajador(nombreApe, sexo, salario, anoIngreso, mesIngreso, diaIngreso, tipoEmpleado, provincia);
        } catch (EOFException e) {
            esFinFichero = true;
            throw e;
        }
    }
}
