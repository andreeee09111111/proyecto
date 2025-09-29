//Esta clase representa una tarea o una orden de trabajo
//Contiene todo lo necesario para saber qué hacer y quién debe hacerlo
public class Tarea {
    private String descripcion;
    private String departamento;
    // La prioridad: 1 es la más urgente, 3 es la menos
    private int urgencia; 
    private Empleado empleadoAsignado;
    private int duracionMinutos; 
    // Un ID que usamos para buscar esta tarea en cualquier lista
    private int idLista;
    // Esta variable apunta a la tarea que debe terminarse antes que la otra
    private Tarea tareaPrevia; 

    public Tarea(String descripcion, String departamento, int urgencia, Empleado empleadoAsignado, int duracionMinutos) {
        this.descripcion = descripcion;
        this.departamento = departamento;
        this.urgencia = urgencia;
        this.empleadoAsignado = empleadoAsignado;
        this.duracionMinutos = duracionMinutos;
        this.tareaPrevia = null; 
    }

    // Funciones para obtener y cambiar la información

    public String getDescripcion() { return descripcion; }
    public String getDepartamento() { return departamento; }
    public int getUrgencia() { return urgencia; }
    public Empleado getEmpleadoAsignado() { return empleadoAsignado; }
    public int getDuracionMinutos() { return duracionMinutos; }
    public int getIdLista() { return idLista; }
    public void setIdLista(int idLista) { this.idLista = idLista; }
    public Tarea getTareaPrevia() { return tareaPrevia; }
    // Esto es para establecer la dependencia cuando creamos el Grafo
    public void setTareaPrevia(Tarea tareaPrevia) { this.tareaPrevia = tareaPrevia; }
    
    // Función de comparación para la cola de prioridad
    public int compareTo(Tarea otra) {
        return Integer.compare(this.urgencia, otra.urgencia);
    }

    @Override
    public String toString() {
        // Formato de texto en el que se muestra la tarea
        return "[" + idLista + "] " + descripcion + " (" + empleadoAsignado.getNombre() + ", " + duracionMinutos + " min, P" + urgencia + ")";
    }
}