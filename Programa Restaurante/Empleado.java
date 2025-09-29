// Esta es la ficha de cada empleado del restaurante.
//Aquí guardamos toda su información.
 
public class Empleado {
    private String id;
    private String nombre;
    private String departamento;
    // Esta bandera nos dice si el empleado está ocupado (true) o si está libre (false)
    private boolean ocupado;
    // Aquí guardamos la tarea específica que está haciendo en este momento
    private Tarea tareaActual;

    public Empleado(String id, String nombre, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.ocupado = false;
        this.tareaActual = null;
    }

    // Funciones para obtener y cambiar la información

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDepartamento() { return departamento; }
    public boolean isOcupado() { return ocupado; }
    // Usamos esta función cuando le asignamos o quitamos una tarea
    public void setOcupado(boolean ocupado) { this.ocupado = ocupado; }
    public Tarea getTareaActual() { return tareaActual; }
    public void setTareaActual(Tarea tareaActual) { this.tareaActual = tareaActual; }

    @Override
    public String toString() {
    // Formato para mostrar el empleado
        return nombre + " (" + departamento + ")";
    }
}