import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Esta clase representa una estructura de datos simple de Cola (FIFO)
public class ColaTareas {
    // La cola se implementa usando la interfaz Queue y la clase LinkedList de Java
    private Queue<Tarea> cola = new LinkedList<>();

    // Método para añadir una tarea al final de la cola
    public void enqueue(Tarea t) { cola.add(t); }

    // Método para sacar la tarea del frente de la cola
    // Devuelve null si la cola está vacía, para evitar errores
    public Tarea dequeue() { return cola.isEmpty() ? null : cola.poll(); }
    
    // Método para obtener todas las tareas en la cola como una lista
    // Crea una nueva lista para no exponer la estructura interna directamente
    public List<Tarea> getAll() { return new ArrayList<>(cola); }
}