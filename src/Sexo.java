
/**
 * @author Álvaro López De San Román
 */
public enum Sexo {

    M('M', "Mujer"),
    H('H', "Hombre");

    private final char codigo;
    private final String descripcion;

    Sexo(char codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public char getCodigo() {
        return this.codigo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public static Sexo fromCodigo(char codigo) {
        Sexo resultado;
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
            throw new IllegalArgumentException("Código de sexo inválido: " + codigo);
        }
        return resultado;
    }
}

