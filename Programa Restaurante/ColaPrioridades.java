import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;


 // En lugar de ser FIFO es una Cola de Prioridad
 // el que tenga la prioridad más alta (el número 1) sale primero, sin importar cuándo entró
 // El "implements Iterable" nos permite recorrerla fácilmente en la GUI
public class ColaPrioridades implements Iterable<Tarea> {
    
    // Esto hace que el elemento con el número de urgencia más bajo (1) sea el que salga primero
    private PriorityQueue<Tarea> queue = new PriorityQueue<>(Comparator.comparingInt(Tarea::getUrgencia));

    // Agrega una tarea. La cola automáticamente la pone en su lugar según su prioridad
    public void add(Tarea tarea) {
        queue.add(tarea);
    }

    // Quita y devuelve la tarea de más alta prioridad
    public Tarea poll() {
        return queue.poll();
    }
    
    // Busca una tarea específica dentro de la cola usando su ID
    public Tarea buscarPorId(int id) {
        for (Tarea t : queue) {
            if (t.getIdLista() == id) {
                return t;
            }
        }
        return null;
    }
    
    // Devuelve una copia de las tareas para mostrarlas en la GUI
    public List<Tarea> getAll() {
        return new ArrayList<>(queue);
    }

    // Esta función es necesaria para poder usar la Cola en los bucles "for" de la GUI
    @Override
    public java.util.Iterator<Tarea> iterator() {
        return queue.iterator();
    }
}