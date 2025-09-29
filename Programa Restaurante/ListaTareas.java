import java.util.ArrayList;
import java.util.List;


// Esta es una lista simple para tareas asignadas por departamento
// mouestra el algoritmo de Merge Sort (Divide y Vencerás)
public class ListaTareas {
    private List<Tarea> tareas = new ArrayList<>();

    public void insertar(Tarea tarea) {
        tareas.add(tarea);
    }

    // Elimina una tarea de la lista buscando su ID
     
    public boolean eliminar(int id) {
        return tareas.removeIf(t -> t.getIdLista() == id);
    }
    
    public Tarea buscarPorId(int id) {
        for (Tarea t : tareas) {
            if (t.getIdLista() == id) {
                return t;
            }
        }
        return null;
    }
    
    public List<Tarea> getAll() {
        return tareas;
    }

    // Arranca el algoritmo de Merge Sort para ordenar la lista por prioridad (urgencia)
    public void ordenarPorPrioridad() {
        if (tareas == null || tareas.size() <= 1) {
            return;
        }
        // La lista 'tareas' se reemplaza con la lista ordenada que devuelve Merge Sort
        this.tareas = mergeSort(tareas); 
    }
     // Esta es la parte recursiva que rompe la lista.
     // Parte la lista a la mitad una y otra vez hasta que todas las piezas tienen una sola tarea
    private List<Tarea> mergeSort(List<Tarea> lista) {
        if (lista.size() <= 1) {
            return lista;
        }

        int mitad = lista.size() / 2;
        List<Tarea> izquierda = lista.subList(0, mitad);
        List<Tarea> derecha = lista.subList(mitad, lista.size());

        // Llama a mergeSort para las dos mitades, sigue rompiendo
        izquierda = mergeSort(izquierda);
        derecha = mergeSort(derecha);

        // Cuando ya no puede romper más, empieza a juntar (merge)
        return merge(izquierda, derecha);
    }

    
     // Esta parte junta las piezas rotas de forma ordenada
     //Compara las tareas de las dos listas (izquierda y derecha) y pone la más urgente primero
    
    private List<Tarea> merge(List<Tarea> izquierda, List<Tarea> derecha) {
        List<Tarea> resultado = new ArrayList<>();
        int i = 0, j = 0;

        while (i < izquierda.size() && j < derecha.size()) {
            // Compara la urgencia. si la izquierda es más urgente (número más pequeño) la añade.
            if (izquierda.get(i).getUrgencia() <= derecha.get(j).getUrgencia()) {
                resultado.add(izquierda.get(i++));
            } else {
                resultado.add(derecha.get(j++));
            }
        }

        // Agrega cualquier tarea que haya sobrado después de la comparación
        while (i < izquierda.size()) {
            resultado.add(izquierda.get(i++));
        }
        while (j < derecha.size()) {
            resultado.add(derecha.get(j++));
        }

        return resultado;
    }
}