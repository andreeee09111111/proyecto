import java.util.PriorityQueue;

public class ColaPrioridades extends PriorityQueue<Tarea> {
    public ColaPrioridades() {
        super((t1, t2) -> {
            int comparacionUrgencia = Integer.compare(t1.getUrgencia(), t2.getUrgencia());
            if (comparacionUrgencia != 0) {
                return comparacionUrgencia;
            }
            return 0;
        });
    }
}