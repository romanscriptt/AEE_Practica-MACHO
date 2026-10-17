import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 *
 * @author Álvaro López De San Román
 */
public class TrabajadorDAO {

    public static void escribir(DataOutputStream data, Trabajador t) throws IOException {
        data.writeUTF(t.getNombre());
        data.writeChar(t.getSexo());
        data.writeFloat(t.getSalario());
        data.writeInt(t.getAnio());
        data.writeInt(t.getMes());
        data.writeInt(t.getDia());
        data.writeChar(t.getTipoEmpleado());
        data.writeInt(t.getProvincia());
    }

    public static Trabajador leer(DataInputStream data) throws EOFException, IOException {
        String nombre = data.readUTF();
        char sexo = data.readChar();
        float salario = data.readFloat();
        int anio = data.readInt();
        int mes = data.readInt();
        int dia = data.readInt();
        char tipoEmpleado = data.readChar();
        int provincia = data.readInt();

        return new Trabajador(nombre, sexo, salario, anio, mes, dia, tipoEmpleado, provincia);
    }
}
