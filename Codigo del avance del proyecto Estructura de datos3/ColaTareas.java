import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ColaTareas {
    private Queue<Tarea> cola = new LinkedList<>();

    public void enqueue(Tarea t) { cola.add(t); }
    public Tarea dequeue() { return cola.isEmpty() ? null : cola.poll(); }
    public List<Tarea> getAll() { return new ArrayList<>(cola); }
}