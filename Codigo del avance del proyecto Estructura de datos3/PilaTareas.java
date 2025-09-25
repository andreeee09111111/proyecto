import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PilaTareas {
    private Stack<Tarea> pila = new Stack<>();

    public void push(Tarea tarea) {
        pila.push(tarea);
    }

    public Tarea pop() {
        if (!pila.isEmpty()) {
            return pila.pop();
        }
        return null;
    }

    public List<Tarea> getAll() {
        return new ArrayList<>(pila);
    }
}