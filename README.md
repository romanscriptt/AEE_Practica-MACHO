# Práctica MACHO (AEE) - Acceso a Datos
Proyecto Java desarrollado para la Actividad Evaluable "Práctica MACHO" del módulo Acceso a Datos. La aplicación realiza el procesamiento secuencial binario del fichero FICHE.DAT garantizando un 0% de uso adicional de memoria RAM.

---

## Autores y Reparto de Trabajo
Proyecto realizado por Álvaro López de San Romám y Antonio Muñoz Herrera.

### Registro de Tareas Realizadas y Control de Tiempos

#### Álvaro López De San Román
* Diagrama de Clases UML 2.5: Diseñado y estructurado bajo el estándar UML 2.5 mostrando las relaciones de dependencia (<<use>> y <<create>>).
  * Tiempo estimado: 1 hora y 30 minutos
  * Tiempo real invertido: 2 horas
* Entidad y Persistencia Base (Trabajador.java y TrabajadorDAO.java): Implementación de la clase DTO y la capa DAO utilitaria para la lectura y escritura secuencial binaria.
* ApartadoA.java: Captura e inserción de trabajadores por teclado mediante BufferedReader con validación completa de rangos, tipos de datos y cálculo dinámico de días según el mes y año bisiesto.
  * Tiempo estimado: 1 hora y 30 minutos
  * Tiempo real invertido: 2 horas

### Antonio Muñoz Herrera
* Diagrama de Clases UML: Revisión y reestructuración de la estructura del diseño tras repasarlo junto con el compañero
   * Tiempo estimado: 1 hora y 30 minutos
   * Tiempo real invertido: 2 horas y 15 minutos
* ApartadoB.java: Obtención del listado de empleados que llevan 10 años o más en la empresa. Utilización de GregorianCalendar para el cálculo de las fechas y validación.
   * Tiempo estimado: 2 horas
   * Tiempo real: 3 horas
* ApartadoD.java: Obtención del empleado con mayor salario y combinación con ApartadoC
   * Tiempo estimado: 2 hora
   * Tiempo real: 2 horas y 30 minutos
* ApartadoE.java: Obtención de la provincia con mayor población
   * Tiempo estimado: 1 hora
   * Tiempo real: 2 horas
