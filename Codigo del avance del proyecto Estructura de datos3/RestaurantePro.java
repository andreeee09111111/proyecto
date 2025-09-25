import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

// Clase del nodo del árbol binario
class NodoEmpleado {
    Empleado empleado;
    NodoEmpleado izquierda;
    NodoEmpleado derecha;

    public NodoEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}

// Clase del Árbol Binario de Búsqueda
class ArbolEmpleados {
    private NodoEmpleado raiz;

    public void insertar(Empleado empleado) {
        raiz = insertarRecursivo(raiz, empleado);
    }

    private NodoEmpleado insertarRecursivo(NodoEmpleado raiz, Empleado empleado) {
        if (raiz == null) {
            return new NodoEmpleado(empleado);
        }
        
        int comparacion = empleado.getDepartamento().compareTo(raiz.empleado.getDepartamento());

        if (comparacion < 0) {
            raiz.izquierda = insertarRecursivo(raiz.izquierda, empleado);
        } else if (comparacion > 0) {
            raiz.derecha = insertarRecursivo(raiz.derecha, empleado);
        } else {
            raiz.derecha = insertarRecursivo(raiz.derecha, empleado);
        }

        return raiz;
    }

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

        if (comparacion <= 0) {
            buscarRecursivo(nodo.izquierda, departamento, resultado);
        }
        
        if (comparacion == 0) {
            if (!nodo.empleado.isOcupado()) {
                 resultado.add(nodo.empleado);
            }
        }
        
        if (comparacion >= 0) {
            buscarRecursivo(nodo.derecha, departamento, resultado);
        }
    }
}

public class RestaurantePro {
    private HashMap<String, Empleado> empleadosMap = new HashMap<>();
    private ArbolEmpleados empleadosPorDepartamento = new ArbolEmpleados();
    private int contadorTemporalID = 0;
    private int contadorListaID = 0; // Nuevo contador para las tareas de la lista

    private final PilaTareas pila = new PilaTareas();
    private final ColaPrioridades cola = new ColaPrioridades(); 
    private final ListaTareas lista = new ListaTareas();

    public RestaurantePro() {
        inicializarEmpleados();
    }

    private void inicializarEmpleados() {
        Empleado[] empleados = {
            new Empleado("C001", "Roberto de León", "Cocina"),
            new Empleado("C002", "Marta Sánchez", "Cocina"),
            new Empleado("C003", "Jorge Ramírez", "Cocina"),
            new Empleado("C004", "Sofía Delgado", "Cocina"),
            new Empleado("C005", "Ricardo López", "Cocina"),
            new Empleado("C006", "Valeria Jiménez", "Cocina"),
            new Empleado("C007", "Daniel Pineda", "Cocina"),
            new Empleado("C008", "Fernanda Cruz", "Cocina"),
            new Empleado("C009", "Sebastián Morales", "Cocina"),
            new Empleado("C010", "Luis Hernández", "Cocina"),
            new Empleado("B001", "Jevick Florez", "Barra"),
            new Empleado("B002", "Andrés Navarro", "Barra"),
            new Empleado("B003", "Paola Vázquez", "Barra"),
            new Empleado("B004", "Felipe Castillo", "Barra"),
            new Empleado("B005", "Natalia Ríos", "Barra"),
            new Empleado("B006", "Julián Mendoza", "Barra"),
            new Empleado("B007", "Lorena Aguirre", "Barra"),
            new Empleado("B008", "Tomás Herrera", "Barra"),
            new Empleado("B009", "Gabriela Flores", "Barra"),
            new Empleado("B010", "Manuel Díaz", "Barra"),
            new Empleado("X001", "Karla Romero", "Caja"),
            new Empleado("X002", "Diego Pérez", "Caja"),
            new Empleado("X003", "Alejandra Soto", "Caja"),
            new Empleado("X004", "Iván Guerrero", "Caja"),
            new Empleado("X005", "Mariana Salinas", "Caja"),
            new Empleado("X006", "Óscar Villalobos", "Caja"),
            new Empleado("X007", "Patricia Domínguez", "Caja"),
            new Empleado("X008", "Eduardo Chávez", "Caja"),
            new Empleado("X009", "Andrea Silva", "Caja"),
            new Empleado("X010", "Rodrigo Castañeda", "Caja")
        };

        for (Empleado emp : empleados) {
            empleadosMap.put(emp.getId(), emp);
            empleadosPorDepartamento.insertar(emp);
        }
    }

    private String generarIDTemporal() {
        contadorTemporalID++;
        return String.format("T%03d", contadorTemporalID);
    }
    
    public int generarIdLista() {
        contadorListaID++;
        return contadorListaID;
    }

    public Empleado crearEmpleadoTemporal(String nombre) {
        String id = generarIDTemporal();
        Empleado nuevoEmpleado = new Empleado(id, nombre, "Otro");
        empleadosMap.put(id, nuevoEmpleado);
        return nuevoEmpleado;
    }

    public Empleado buscarEmpleado(String query) {
        return empleadosMap.get(query);
    }

    public Empleado asignarEmpleado(String departamento) {
        List<Empleado> empleadosDisponibles = new ArrayList<>();

        if (departamento.equalsIgnoreCase("Otro")) {
            return null;
        } else {
            empleadosDisponibles = empleadosPorDepartamento.buscarPorDepartamento(departamento);
        }

        if (empleadosDisponibles.isEmpty()) {
            return null;
        }

        Random rand = new Random();
        Empleado empleadoAsignado = empleadosDisponibles.get(rand.nextInt(empleadosDisponibles.size()));
        return empleadoAsignado;
    }
    
    public void agregarTareaConDependencia(Tarea tarea, Tarea tareaPrevia) {
        if (tareaPrevia != null) {
            tarea.setTareaPrevia(tareaPrevia);
        }
        cola.add(tarea);
    }

    public void liberarEmpleado(Empleado emp) {
        if (emp != null) {
            emp.setOcupado(false);
            emp.setTareaActual(null);
        }
    }

    public Tarea buscarTareaPorDescripcion(String desc) {
        for (Tarea t : pila.getAll()) {
            if (t.getDescripcion().equalsIgnoreCase(desc)) return t;
        }
        for (Tarea t : new ArrayList<>(cola)) {
            if (t.getDescripcion().equalsIgnoreCase(desc)) return t;
        }
        for (Tarea t : lista.getAll()) {
            if (t.getDescripcion().equalsIgnoreCase(desc)) return t;
        }
        return null;
    }
    
    public Tarea buscarTareaPorIdLista(int id) {
        return lista.buscarPorId(id);
    }

    public List<Empleado> getTodosLosEmpleados() {
        return new ArrayList<>(empleadosMap.values());
    }
    
    public PilaTareas getPila() { return pila; }
    public ColaPrioridades getCola() { return cola; }
    public ListaTareas getLista() { return lista; }
}