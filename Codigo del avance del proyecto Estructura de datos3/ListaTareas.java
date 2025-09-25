import java.util.LinkedList;
import java.util.List;

public class ListaTareas {
    private LinkedList<Tarea> lista = new LinkedList<>();

    public void insertar(Tarea tarea) {
        lista.add(tarea);
    }

    public boolean eliminar(int id) {
        for (Tarea t : lista) {
            if (t.getIdLista() == id) {
                lista.remove(t);
                return true;
            }
        }
        return false;
    }

    public Tarea buscarPorId(int id) {
        for (Tarea t : lista) {
            if (t.getIdLista() == id) {
                return t;
            }
        }
        return null;
    }

    public List<Tarea> getAll() {
        return new LinkedList<>(lista);
    }
}