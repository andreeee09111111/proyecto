public class Empleado {
    private String id;
    private String nombre;
    private String departamento;
    private boolean ocupado;
    private Tarea tareaActual;

    public Empleado(String id, String nombre, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.ocupado = false;
        this.tareaActual = null;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDepartamento() { return departamento; }
    public boolean isOcupado() { return ocupado; }
    public Tarea getTareaActual() { return tareaActual; }

    public void setOcupado(boolean ocupado) { this.ocupado = ocupado; }
    public void setTareaActual(Tarea tareaActual) { this.tareaActual = tareaActual; }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Departamento: " + departamento + ", Estado: " + (ocupado ? "Ocupado" : "Disponible");
    }
}