import java.util.Stack;
import java.util.List;

// La Pila se usa para las tareas más urgentes (LIFO)
 
public class PilaTareas {
    private Stack<Tarea> stack = new Stack<>();

    // Pone una tarea en la parte de arriba de la pila.
    public void push(Tarea tarea) {
        stack.push(tarea);
    }

    // Quita y te devuelve la tarea que está hasta arriba
    public Tarea pop() {
        // Si está vacía, devuelve null (nada)
        return stack.isEmpty() ? null : stack.pop();
    }
    
    // Busca una tarea específica dentro de la pila usando su ID
    public Tarea buscarPorId(int id) {
        for (Tarea t : stack) {
            if (t.getIdLista() == id) {
                return t;
            }
        }
        return null;
    }

    // Devuelve todas las tareas de la pila para poder verlas en la GUI
    public List<Tarea> getAll() {
        return stack;
    }
}