
/**
 *
 * @author Álvaro López De San Román
 */
public enum Provincia {

    ALMERIA((byte) 1, "Almería"),
    CADIZ((byte) 2, "Cádiz"),
    CORDOBA((byte) 3, "Córdoba"),
    GRANADA((byte) 4, "Granada"),
    HUELVA((byte) 5, "Huelva"),
    JAEN((byte) 6, "Jaén"),
    MALAGA((byte) 7, "Málaga"),
    SEVILLA((byte) 8, "Sevilla");

    private final byte codigo;
    private final String nombre;

    Provincia(byte codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public byte getCodigo() {
        return this.codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public static Provincia fromCodigo(byte codigo) {
        Provincia resultado;
        int posicion;
        boolean encontrado;

        resultado = null;
        encontrado = false;
        posicion = 0;

        while (posicion < values().length && !encontrado) {
            if (values()[posicion].codigo == codigo) {
                resultado = values()[posicion];
                encontrado = true;
            } else {
                posicion++;
            }
        }

        if (!encontrado) {
            throw new IllegalArgumentException("Código de provincia inválido: " + codigo);
        }
        return resultado;
    }
}
