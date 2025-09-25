public class Tarea {
    private String descripcion;
    private String departamento;
    private int urgencia;
    private Empleado empleadoAsignado;
    private Tarea tareaPrevia;
    private int idLista; // Nuevo campo para el ID de la lista

    public Tarea(String descripcion, String departamento, int urgencia, Empleado empleadoAsignado) {
        this.descripcion = descripcion;
        this.departamento = departamento;
        this.urgencia = urgencia;
        this.empleadoAsignado = empleadoAsignado;
        this.tareaPrevia = null;
        this.idLista = -1; // -1 por defecto
    }

    public String getDescripcion() { return descripcion; }
    public String getDepartamento() { return departamento; }
    public int getUrgencia() { return urgencia; }
    public Empleado getEmpleadoAsignado() { return empleadoAsignado; }
    public Tarea getTareaPrevia() { return tareaPrevia; }
    public int getIdLista() { return idLista; }

    public void setEmpleadoAsignado(Empleado empleadoAsignado) { this.empleadoAsignado = empleadoAsignado; }
    public void setTareaPrevia(Tarea tareaPrevia) { this.tareaPrevia = tareaPrevia; }
    public void setIdLista(int idLista) { this.idLista = idLista; }

    @Override
    public String toString() {
        String urgTxt = switch (urgencia) {
            case 1 -> "Alta";
            case 2 -> "Media";
            default -> "Baja";
        };
        String infoEmpleado = (empleadoAsignado != null) ? " Asignado a: " + empleadoAsignado.getNombre() + " (ID: " + empleadoAsignado.getId() + ")" : "";
        String dependenciaTxt = (tareaPrevia != null) ? " -> Depende de: " + tareaPrevia.getDescripcion() : "";
        String idListaTxt = (idLista != -1) ? "ID: " + idLista + " | " : "";
        return idListaTxt + "[" + departamento + "] (" + urgTxt + ") -> " + descripcion + infoEmpleado + dependenciaTxt;
    }
}