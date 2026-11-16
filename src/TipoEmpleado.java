
/**
 *
 * @author Álvaro López De San Román
 */
public enum TipoEmpleado {

    C('C', "A comisión"),
    F('F', "Fijos"),
    D('D', "A domicilio");

    private final char codigo;
    private final String descripcion;

    TipoEmpleado(char codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public char getCodigo() {
        return this.codigo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public static TipoEmpleado fromCodigo(char codigo) {
        TipoEmpleado resultado;
        char codigoMayuscula;
        int posicion;
        boolean encontrado;

        resultado = null;
        encontrado = false;
        posicion = 0;
        codigoMayuscula = Character.toUpperCase(codigo);

        while (posicion < values().length && !encontrado) {
            if (values()[posicion].codigo == codigoMayuscula) {
                resultado = values()[posicion];
                encontrado = true;
            } else {
                posicion++;
            }
        }

        if (!encontrado) {
            throw new IllegalArgumentException("Código de tipo de empleado inválido: " + codigo);
        }
        return resultado;
    }
}
