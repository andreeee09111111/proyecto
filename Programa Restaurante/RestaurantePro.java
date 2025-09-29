import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.SwingUtilities;

// Esta clase actúa como el cerebro del sistema
// Contiene las estructuras de datos principales (Pila, Cola, Lista)
// También maneja el Hash Map para Empleados y la lógica del Grafo
public class RestaurantePro {
    
    // Las tres estructuras de datos principales
    private final PilaTareas pila;
    private final ColaPrioridades cola;
    private final ListaTareas lista;
    
    // Hash Map para acceso rápido a la información de los empleados por ID (O(1))
    private final HashMap<String, Empleado> empleadosMap;
    
    // Contador para generar IDs únicos para las tareas en la lista (Grafo)
    private int contadorIdLista = 1000;

    // Constructor que inicializa todas las estructuras
    public RestaurantePro() {
        this.pila = new PilaTareas();
        this.cola = new ColaPrioridades();
        this.lista = new ListaTareas();
        this.empleadosMap = new HashMap<>();
        
        // Inicializa los 30 empleados de prueba con IDs fijos
        inicializarEmpleados();
        // NOTA: Se quitó el método cargarTareasIniciales(), el sistema inicia sin tareas.
    }

    // Métodos de Inicialización y Helper 
    
    // Crea los 30 empleados exactamente como están definidos en la lista (Lo recomendado seria una base de datos por su flexibilidad)
    private void inicializarEmpleados() {
        // Empleados de Cocina (C001 - C010)
        empleadosMap.put("C001", new Empleado("C001", "Roberto de Leon", "Cocina"));
        empleadosMap.put("C002", new Empleado("C002", "Marta Sánchez", "Cocina"));
        empleadosMap.put("C003", new Empleado("C003", "Jorge Ramírez", "Cocina"));
        empleadosMap.put("C004", new Empleado("C004", "Sofía Delgado", "Cocina"));
        empleadosMap.put("C005", new Empleado("C005", "Ricardo López", "Cocina"));
        empleadosMap.put("C006", new Empleado("C006", "Valeria Jiménez", "Cocina"));
        empleadosMap.put("C007", new Empleado("C007", "Daniel Pineda", "Cocina"));
        empleadosMap.put("C008", new Empleado("C008", "Fernanda Cruz", "Cocina"));
        empleadosMap.put("C009", new Empleado("C009", "Sebastián Morales", "Cocina"));
        empleadosMap.put("C010", new Empleado("C010", "Luis Hernández", "Cocina"));
        
        // Empleados de Barra (B001 - B010)
        empleadosMap.put("B001", new Empleado("B001", "Jevick Flores", "Barra"));
        empleadosMap.put("B002", new Empleado("B002", "Andrés Navarro", "Barra"));
        empleadosMap.put("B003", new Empleado("B003", "Paola Vázquez", "Barra"));
        empleadosMap.put("B004", new Empleado("B004", "Felipe Castillo", "Barra"));
        empleadosMap.put("B005", new Empleado("B005", "Natalia Ríos", "Barra"));
        empleadosMap.put("B006", new Empleado("B006", "Julián Mendoza", "Barra"));
        empleadosMap.put("B007", new Empleado("B007", "Lorena Aguirre", "Barra"));
        empleadosMap.put("B008", new Empleado("B008", "Tomás Herrera", "Barra"));
        empleadosMap.put("B009", new Empleado("B009", "Gabriela Flores", "Barra"));
        empleadosMap.put("B010", new Empleado("B010", "Manuel Díaz", "Barra"));

        // Empleados de Caja (X001 - X010)
        empleadosMap.put("X001", new Empleado("X001", "Karla Romero", "Caja"));
        empleadosMap.put("X002", new Empleado("X002", "Diego Pérez", "Caja"));
        empleadosMap.put("X003", new Empleado("X003", "Alejandra Soto", "Caja"));
        empleadosMap.put("X004", new Empleado("X004", "Iván Guerrero", "Caja"));
        empleadosMap.put("X005", new Empleado("X005", "Mariana Salinas", "Caja"));
        empleadosMap.put("X006", new Empleado("X006", "Óscar Villalobos", "Caja"));
        empleadosMap.put("X007", new Empleado("X007", "Patricia Domínguez", "Caja"));
        empleadosMap.put("X008", new Empleado("X008", "Eduardo Chávez", "Caja"));
        empleadosMap.put("X009", new Empleado("X009", "Andrea Silva", "Caja"));
        empleadosMap.put("X010", new Empleado("X010", "Rodrigo Castañeda", "Caja"));
    }
    
    // Crea un empleado temporal para tareas que no tienen un departamento fijo
    public Empleado crearEmpleadoTemporal(String nombre) {
        String id = "T" + (new Random().nextInt(900) + 100);
        Empleado temp = new Empleado(id, nombre, "Temporal");
        empleadosMap.put(id, temp); // Se añade al mapa para seguimiento
        return temp;
    }

    // El método cargarTareasIniciales() ha sido removido.

    // Generador de ID únicos para las tareas que entran a la Lista (y son parte del Grafo)
    public int generarIdLista() {
        return contadorIdLista++;
    }

    // Busca un empleado disponible en un departamento específico (Simulación de búsqueda en ABB)
    public Empleado asignarEmpleado(String departamento) {
        // Itera sobre el Hash Map para encontrar un empleado disponible
        for (Empleado emp : empleadosMap.values()) {
            if (emp.getDepartamento().equals(departamento) && !emp.isOcupado()) {
                return emp;
            }
        }
        return null; // Nadie disponible
    }
    
    // Marca un empleado como disponible y limpia su tarea actual
    public void liberarEmpleado(Empleado empleado) {
        if (empleado != null) {
            empleado.setOcupado(false);
            empleado.setTareaActual(null);
        }
    }
    
    // Busca un empleado por su ID de forma rápida (Hash Map - O(1))
    public Empleado buscarEmpleado(String id) {
        return empleadosMap.get(id);
    }
    
    // Obtiene una lista de todos los empleados
    public List<Empleado> getTodosLosEmpleados() {
        return new ArrayList<>(empleadosMap.values());
    }

    // Busca una tarea por su ID en cualquiera de las tres estructuras (Grafo)
    public Tarea buscarTareaPorId(int id) {
        // 1. Buscar en Pila
        for (Tarea t : pila.getAll()) {
            if (t.getIdLista() == id) return t;
        }
        // 2. Buscar en Cola de Prioridad
        for (Tarea t : cola.getAll()) {
            if (t.getIdLista() == id) return t;
        }
        // 3. Buscar en Lista Enlazada
        Tarea tLista = lista.buscarPorId(id);
        if (tLista != null) return tLista;
        
        return null; // No se encontró
    }
    
    // Revisa si alguna tarea depende de la tarea que se quiere eliminar/modificar (Grafo)
    public Tarea esTareaPreviaDeAlguien(Tarea t) {
        // Se revisan todas las tareas en las tres estructuras
        List<Tarea> todas = new ArrayList<>();
        todas.addAll(pila.getAll());
        todas.addAll(cola.getAll());
        todas.addAll(lista.getAll());
        
        for (Tarea otra : todas) {
            // Revisa si la tarea 't' es la predecesora de 'otra'
            if (otra.getTareaPrevia() == t) {
                return otra;
            }
        }
        return null; // Nadie depende de ella
    }

    // Métodos de Algoritmos Avanzados (Recursividad y Grafo) 

    // Calcula el tiempo total de una cadena de tareas usando Recursividad
    public int calcularTiempoEstimadoRecursivo(Tarea tarea) {
        // Caso Base: Si no hay tarea previa, el tiempo es solo la duración de esta tarea
        if (tarea.getTareaPrevia() == null) {
            return tarea.getDuracionMinutos();
        }
        // Caso Recursivo: Suma la duración de la tarea actual y llama a la función
        // para calcular el tiempo de la tarea previa
        return tarea.getDuracionMinutos() + calcularTiempoEstimadoRecursivo(tarea.getTareaPrevia());
    }

    // Verifica si la adición de una dependencia crea un ciclo (Grafo - Detección de Ciclos)
    public boolean creaCiclo(Tarea nuevaTarea, Tarea tareaPrevia) {

        // Si en algún momento encontramos la nuevaTarea, hay un ciclo
        Tarea actual = tareaPrevia;
        while (actual != null) {
            if (actual.equals(nuevaTarea)) {
                return true; // Se encontró un ciclo
            }
            actual = actual.getTareaPrevia(); // Sigue la cadena hacia atrás
        }
        return false; // No hay ciclo detectado
    }
    
    // Calcula el tiempo de inicio más temprano de una tarea (Grafo)
    public int calcularInicioMasTemprano(Tarea tarea) {
        // Si no tiene predecesora, su inicio más temprano es 0
        if (tarea.getTareaPrevia() == null) {
            return 0;
        }
        
        // Inicio Temprano = (Inicio Más Temprano de la Previa) + (Duración de la Previa)
        return calcularInicioMasTemprano(tarea.getTareaPrevia()) + tarea.getTareaPrevia().getDuracionMinutos();
    }


    // --- Getters para acceder a las estructuras desde la GUI ---
    
    public PilaTareas getPila() {
        return pila;
    }

    public ColaPrioridades getCola() {
        return cola;
    }

    public ListaTareas getLista() {
        return lista;
    }
    
    // --- Método principal que lanza la aplicación ---
    
    public static void main(String[] args) {
        // Inicializa la lógica del restaurante
        RestaurantePro restaurante = new RestaurantePro();
        
        // Lanza la Interfaz Gráfica de Usuario (GUI)
        // SwingUtilities.invokeLater asegura que la GUI se ejecute en el hilo seguro de Swing
        SwingUtilities.invokeLater(() -> new RestauranteAppGUI(restaurante));
    }
}