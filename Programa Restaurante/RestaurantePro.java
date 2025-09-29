import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

// Pieza del Árbol Binario que guarda un Empleado
class NodoEmpleado {
    Empleado empleado;
    NodoEmpleado izquierda;
    NodoEmpleado derecha;

    public NodoEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}

// El Árbol Binario de Búsqueda (BST) para manejar los empleados
// Nos ayuda a encontrar rápidamente empleados libres en un departamento específico
class ArbolEmpleados {
    private NodoEmpleado raiz;

    // Agrega un nuevo empleado al árbol, ordenándolo por departamento
    public void insertar(Empleado empleado) {
        raiz = insertarRecursivo(raiz, empleado);
    }

    private NodoEmpleado insertarRecursivo(NodoEmpleado raiz, Empleado empleado) {
        if (raiz == null) {
            return new NodoEmpleado(empleado);
        }
        
        // Compara los nombres de los departamentos para decidir si va a la izquierda o derecha
        int comparacion = empleado.getDepartamento().compareTo(raiz.empleado.getDepartamento());

        if (comparacion < 0) {
            raiz.izquierda = insertarRecursivo(raiz.izquierda, empleado);
        } else {
            raiz.derecha = insertarRecursivo(raiz.derecha, empleado);
        }

        return raiz;
    }

    
    // Busca en el árbol y devuelve todos los empleados que están en el 'departamento' y están disponibles
    public List<Empleado> buscarPorDepartamento(String departamento) {
        List<Empleado> resultado = new ArrayList<>();
        buscarRecursivo(raiz, departamento, resultado);
        return resultado;
    }

    private void buscarRecursivo(NodoEmpleado nodo, String departamento, List<Empleado> resultado) {
        if (nodo == null) {
            return;
        }

        int comparacion = departamento.compareTo(nodo.empleado.getDepartamento());

        // Se va a la izquierda
        if (comparacion <= 0) {
            buscarRecursivo(nodo.izquierda, departamento, resultado);
        }
        
        // Si es el departamento correcto y el empleado no está ocupado, lo añade a la lista de resultados
        if (comparacion == 0) {
            if (!nodo.empleado.isOcupado()) {
                 resultado.add(nodo.empleado);
            }
        }
        
        // Se va a la derecha
        if (comparacion >= 0) {
            buscarRecursivo(nodo.derecha, departamento, resultado);
        }
    }
}

// Esta clase conecta la GUI con todas las estructuras de datos.
// Aquí están los métodos de asignación, búsqueda y los algoritmos especiales.

public class RestaurantePro {
    // Esto es el Hash Map guarda todos los empleados para buscar por ID súper rápido
    private HashMap<String, Empleado> mapaEmpleados = new HashMap<>(); 
    // Esto es el Árbol Binario nos da la lista de empleados disponibles por departamento
    private ArbolEmpleados empleadosPorDepartamento = new ArbolEmpleados(); 
    
    private int contadorIdTemporal = 0;
    private int contadorIdLista = 0;

    // Las 3 estructuras de datos principales
    private final PilaTareas pila = new PilaTareas();
    private final ColaPrioridades cola = new ColaPrioridades(); 
    private final ListaTareas lista = new ListaTareas();

    public RestaurantePro() {
        // Al iniciar, cargamos los empleados de muestra
        inicializarEmpleados();
    }

    private void inicializarEmpleados() {
        // Creación de algunos empleados y su inserción en el Hash Map y el Árbol. Esto es mejor con base de datos :) 
        Empleado[] empleados = {
            new Empleado("C001", "Roberto de León", "Cocina"), new Empleado("C002", "Marta Sánchez", "Cocina"),
            new Empleado("C003", "Juan García", "Cocina"), new Empleado("C004", "Sofía Ruíz", "Cocina"),
            new Empleado("B001", "Jevick Florez", "Barra"), new Empleado("B002", "Andrés Navarro", "Barra"),
            new Empleado("B003", "Elena Cortés", "Barra"), new Empleado("B004", "Mario Torres", "Barra"),
            new Empleado("X001", "Karla Romero", "Caja"), new Empleado("X002", "Diego Pérez", "Caja"),
            new Empleado("X003", "Luisa Blanco", "Caja"), new Empleado("X004", "Pablo Rey", "Caja")
        };

        for (Empleado emp : empleados) {
            mapaEmpleados.put(emp.getId(), emp);
            empleadosPorDepartamento.insertar(emp);
        }
    }

    // Calcula el tiempo total que tomará una cadena de tareas usando Recursividad
    // Llama a sí misma, sumando la duración de cada tarea anterior
    public int calcularTiempoEstimadoRecursivo(Tarea tarea) {
        if (tarea == null) {
            return 0; // Se detiene cuando llega al final de la cadena (no hay tarea previa)
        }
        
        // Suma la duración de esta tarea y le agrega el resultado de la tarea previa
        return tarea.getDuracionMinutos() + calcularTiempoEstimadoRecursivo(tarea.getTareaPrevia());
    }
    
    // Algoritmo de Grafo (Ruta Crítica - CPM) que calcula en qué momento MÁS TEMPRANO puede empezar esta tarea
    public int calcularInicioMasTemprano(Tarea tarea) {
        // Si no depende de nada, empieza en el minuto 0.
        if (tarea == null || tarea.getTareaPrevia() == null) {
            return 0;
        }
        
        Tarea previa = tarea.getTareaPrevia();
        
        // Tiempo de Fin de la Tarea Previa = (Inicio Más Temprano de la Previa) + (Duración de la Previa)
        int tiempoFinPrevia = calcularInicioMasTemprano(previa) + previa.getDuracionMinutos();
        
        // La tarea actual solo puede empezar cuando la previa termine
        return tiempoFinPrevia;
    }
    
    
     // Pregunta a todas las listas si alguna tarea tiene como requisito a la 'tareaAExaminar'
     // Es la protección para no borrar un nodo del Grafo que es vital para otra tarea
    public Tarea esTareaPreviaDeAlguien(Tarea tareaAExaminar) {
        // Revisa en la lista, la pila y la cola
        for (Tarea t : lista.getAll()) { if (t.getTareaPrevia() == tareaAExaminar) return t; }
        for (Tarea t : pila.getAll()) { if (t.getTareaPrevia() == tareaAExaminar) return t; }
        for (Tarea t : cola.getAll()) { if (t.getTareaPrevia() == tareaAExaminar) return t; }
        return null; // Si es null, nadie depende de ella
    }

     // Evita que se forme un ciclo en el Grafo
     // Recorre hacia atrás la cadena de dependencia para ver si vuelve a la tarea inicial
    public boolean creaCiclo(Tarea tareaActual, Tarea tareaPrevia) {
        if (tareaPrevia == null) return false;
        
        Tarea temp = tareaPrevia;
        // Mientras haya tareas previas...
        while (temp != null) {
            // Si la tarea previa es la misma que la tarea que estamos creando, es un ciclo
            if (temp == tareaActual) {
                return true; 
            }
            // Sigue subiendo en la cadena
            temp = temp.getTareaPrevia();
        }
        return false;
    }
    
    // Métodos Auxiliares y Getters
    
    private String generarIdTemporal() { return String.format("T%03d", ++contadorIdTemporal); }
    public int generarIdLista() { return ++contadorIdLista; } // Genera el ID único para cada tarea
    
    public Empleado crearEmpleadoTemporal(String nombre) {
        String id = generarIdTemporal();
        Empleado nuevo = new Empleado(id, nombre, "Otro");
        mapaEmpleados.put(id, nuevo);
        return nuevo;
    }
    
    // Usa el Hash Map para buscar al empleado en O(1)
    public Empleado buscarEmpleado(String consulta) { 
        return mapaEmpleados.get(consulta); 
    }
    
    // Busca una tarea en cualquiera de las 3 estructuras usando su ID
    public Tarea buscarTareaPorId(int id) { 
        Tarea tarea = lista.buscarPorId(id);
        if (tarea != null) return tarea;
        tarea = pila.buscarPorId(id);
        if (tarea != null) return tarea;
        tarea = cola.buscarPorId(id);
        if (tarea != null) return tarea;
        return null; 
    }
    
    // Alias para el método de búsqueda
    public Tarea buscarTareaPorIdLista(int id) { 
        return buscarTareaPorId(id); 
    }
    
    // Asigna un empleado disponible del departamento usando el Árbol Binario
    public Empleado asignarEmpleado(String departamento) {
        // Pide la lista de disponibles al Árbol
        List<Empleado> disponibles = empleadosPorDepartamento.buscarPorDepartamento(departamento);
        if (disponibles.isEmpty()) {
            return null;
        }
        Random rand = new Random();
        // Elige un empleado al azar de los disponibles
        return disponibles.get(rand.nextInt(disponibles.size()));
    }
    
    // Cuando una tarea se completa, liberamos al empleado para que pueda tommar otra
    public void liberarEmpleado(Empleado emp) {
        emp.setOcupado(false);
        emp.setTareaActual(null);
    }
    
    public List<Empleado> getTodosLosEmpleados() { 
        return new ArrayList<>(mapaEmpleados.values()); 
    }
    
    public PilaTareas getPila() { return pila; }
    public ColaPrioridades getCola() { return cola; }
    public ListaTareas getLista() { return lista; }
}