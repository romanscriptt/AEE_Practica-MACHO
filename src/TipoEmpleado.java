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

    public char getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }

    public static TipoEmpleado fromCodigo(char codigo) {
        char upper = Character.toUpperCase(codigo);
        for (TipoEmpleado t : values()) {
            if (t.codigo == upper) return t;
        }
        throw new IllegalArgumentException("Código de tipo de empleado inválido: " + codigo);
    }
}
