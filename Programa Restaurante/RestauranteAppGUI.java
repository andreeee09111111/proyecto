import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

// Esta es la clase principal que crea la ventana, los botones y maneja todo lo que el usuario toca
public class RestauranteAppGUI extends JFrame {
    // Referencias a las estructuras de datos y al cerebro de la aplicación
    private final PilaTareas pila;
    private final ColaPrioridades cola;
    private final ListaTareas lista;
    private final RestaurantePro restaurante;

    // Áreas de texto donde se muestran las listas de tareas
    private final JTextArea areaPila = new JTextArea(12, 30);
    private final JTextArea areaCola = new JTextArea(12, 30);
    private final JTextArea areaLista = new JTextArea(12, 30);
    private final JTextArea areaInfoEmpleado = new JTextArea(5, 25);

    // Campos de texto y botones de opción (radio buttons) para la creación de tareas
    private JTextField campoDescripcion = new JTextField(20);
    private JTextField campoDuracion = new JTextField(5);
    private JTextField campoBuscarEmpleado = new JTextField(15);
    private ButtonGroup grupoUrgencia = new ButtonGroup();
    private JRadioButton rbAlta = new JRadioButton("Alta (1)");
    private JRadioButton rbMedia = new JRadioButton("Media (2)");
    private JRadioButton rbBaja = new JRadioButton("Baja (3)");

    private ButtonGroup grupoDepartamento = new ButtonGroup();
    private JRadioButton rbCocina = new JRadioButton("Cocina");
    private JRadioButton rbBarra = new JRadioButton("Barra");
    private JRadioButton rbCaja = new JRadioButton("Caja");
    private JRadioButton rbOtro = new JRadioButton("Otro");
    
    // Guarda la tarea que el usuario seleccionó como requisito previo
    private Tarea tareaDependiente;

    public RestauranteAppGUI(RestaurantePro restaurante) {
        // Esto hace que los mensajes de alerta (JOptionPanes) salgan en español
        Locale.setDefault(new Locale("es", "ES"));
        UIManager.put("OptionPane.yesButtonText", "Sí");
        UIManager.put("OptionPane.noButtonText", "No");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");
        UIManager.put("OptionPane.okButtonText", "Aceptar");
        
        this.restaurante = restaurante;
        this.pila = restaurante.getPila();
        this.cola = restaurante.getCola();
        this.lista = restaurante.getLista();

        setTitle("Restaurante - Gestor de Tareas PRO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Configuración de la zona de entrada de datos (arriba) 
        JPanel panelEntrada = new JPanel(new GridBagLayout());
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Crear Nueva Tarea"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: Descripción
        gbc.gridx = 0; gbc.gridy = 0; panelEntrada.add(new JLabel("Descripción de la Tarea:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 4; panelEntrada.add(campoDescripcion, gbc);
        gbc.gridwidth = 1;

        // Fila 1: Duración y Urgencia
        gbc.gridx = 0; gbc.gridy = 1; panelEntrada.add(new JLabel("Duración (minutos):"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 2; panelEntrada.add(campoDuracion, gbc);
        gbc.gridx = 3; gbc.gridy = 1; gbc.gridwidth = 1; panelEntrada.add(new JLabel("Nivel de Urgencia:"), gbc);
        gbc.gridx = 4; gbc.gridy = 1; 
        JPanel panelUrgencia = new JPanel(new GridLayout(1, 3));
        panelUrgencia.add(rbAlta); panelUrgencia.add(rbMedia); panelUrgencia.add(rbBaja);
        grupoUrgencia.add(rbAlta); grupoUrgencia.add(rbMedia); grupoUrgencia.add(rbBaja);
        panelEntrada.add(panelUrgencia, gbc);
        
        // Fila 2: Departamento
        gbc.gridx = 0; gbc.gridy = 2; panelEntrada.add(new JLabel("Departamento:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 4;
        JPanel panelDepto = new JPanel(new GridLayout(1, 4));
        panelDepto.add(rbCocina); panelDepto.add(rbBarra); panelDepto.add(rbCaja); panelDepto.add(rbOtro);
        grupoDepartamento.add(rbCocina); grupoDepartamento.add(rbBarra); grupoDepartamento.add(rbCaja); grupoDepartamento.add(rbOtro);
        panelEntrada.add(panelDepto, gbc);
        gbc.gridwidth = 1;
        
        // Fila 3: Botón de Dependencia (Grafo)
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 5;
        JButton btnDependencia = new JButton("Asignar Dependencia (por ID)");
        panelEntrada.add(btnDependencia, gbc);
        gbc.gridwidth = 1;

        // Fila 4: Botones de Agregar Tarea (Pila, Cola, Lista)
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 5;
        JPanel panelBotonesAgregar = new JPanel(new GridLayout(1, 3, 5, 5));
        JButton btnPila = new JButton("Añadir Urgente (Pila)");
        JButton btnCola = new JButton("Añadir Programada (Cola)");
        JButton btnLista = new JButton("Añadir a Departamento (Lista)");
        panelBotonesAgregar.add(btnPila); panelBotonesAgregar.add(btnCola); panelBotonesAgregar.add(btnLista);
        panelEntrada.add(panelBotonesAgregar, gbc);
        gbc.gridwidth = 1;
        
        // Fila 5: Búsqueda de Empleado (Hash Map)
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 1;
        panelEntrada.add(new JLabel("ID del Empleado:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; panelEntrada.add(campoBuscarEmpleado, gbc);
        gbc.gridx = 3; gbc.gridy = 5; gbc.gridwidth = 2;
        JButton btnBuscarEmpleado = new JButton("Buscar Empleado");
        panelEntrada.add(btnBuscarEmpleado, gbc);

        // Fila 6: Área de información de Empleado
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 5;
        areaInfoEmpleado.setEditable(false);
        JScrollPane scrollInfoEmpleado = new JScrollPane(areaInfoEmpleado);
        scrollInfoEmpleado.setBorder(BorderFactory.createTitledBorder("Información del Empleado"));
        panelEntrada.add(scrollInfoEmpleado, gbc);
        
        // Panel Central: Las 3 cajas de visualización 
        JPanel panelVista = new JPanel(new GridLayout(1, 3, 10, 10));
        
        // Configuramos las áreas de texto para que no se puedan editar y les ponemos un borde con título
        areaPila.setEditable(false); areaCola.setEditable(false); areaLista.setEditable(false);
        
        // Esto permite que las áreas de texto tengan scroll si hay muchas tareas
        JScrollPane scrollPila = new JScrollPane(areaPila); scrollPila.setBorder(BorderFactory.createTitledBorder("TAREAS URGENTES (PILA)"));
        JScrollPane scrollCola = new JScrollPane(areaCola); scrollCola.setBorder(BorderFactory.createTitledBorder("TAREAS PROGRAMADAS (COLA DE PRIORIDAD)"));
        JScrollPane scrollLista = new JScrollPane(areaLista); scrollLista.setBorder(BorderFactory.createTitledBorder("TAREAS POR DEPARTAMENTO (LISTA)"));

        panelVista.add(scrollPila); panelVista.add(scrollCola); panelVista.add(scrollLista);

        // Panel Sur: Botones de Acción y Algoritmos 
        JPanel panelAcciones = new JPanel(new FlowLayout());
        // Botones de atención (pop/poll/eliminar)
        JButton btnAtenderPila = new JButton("Completar Urgente (Pila)");
        JButton btnAtenderCola = new JButton("Completar Programada (Cola)");
        JButton btnEliminarLista = new JButton("Eliminar de Lista");
        
        
        
        // Boton que permite completar una tarea Para evitar un deadlock
        JButton btnCompletarLista = new JButton("Completar de Lista");

        
        // Botones que ejecutan los algoritmos avanzados
        JButton btnTiempoEstimado = new JButton("Calcular Tiempo Total (Recursividad)");
        JButton btnInicioTemprano = new JButton("Calcular Inicio Temprano (Grafo)");
        JButton btnOrdenarLista = new JButton("Ordenar Lista (Merge Sort)");
        
        // Agregamos todos los botones al panel de acciones
        panelAcciones.add(btnAtenderPila); 
        panelAcciones.add(btnAtenderCola); 
        panelAcciones.add(btnEliminarLista);
        
        // AÑADIDO EL NUEVO BOTÓN AL PANEL
        panelAcciones.add(btnCompletarLista);
        
        panelAcciones.add(btnTiempoEstimado); 
        panelAcciones.add(btnInicioTemprano); 
        panelAcciones.add(btnOrdenarLista);
        
        JButton btnVerEmpleados = new JButton("Ver Empleados");
        panelAcciones.add(btnVerEmpleados);
        panelAcciones.setBorder(BorderFactory.createTitledBorder("Acciones"));

        // Añadimos las tres grandes secciones (Norte, Centro, Sur) a la ventana principal
        add(panelEntrada, BorderLayout.NORTH);
        add(panelVista, BorderLayout.CENTER);
        add(panelAcciones, BorderLayout.SOUTH);

        // --- Lógica de los Eventos (Qué pasa cuando presionamos un botón) ---

        // Esto asegura que solo se puedan escribir números en el campo de duración
        campoDuracion.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });

        // Este es el código que se ejecuta cuando apretamos "Añadir Urgente", "Añadir Programada" o "Añadir a Departamento"
        ActionListener agregar = e -> {
            String descripcion = campoDescripcion.getText().trim();
            String duracionStr = campoDuracion.getText().trim();
            int urgencia, duracion;
            String departamento = "";
            Empleado empleadoAsignado = null;

            // ... (Código de validación de campos, asignación de urgencia y departamento) ...

            if (descripcion.isEmpty() || duracionStr.isEmpty() || grupoDepartamento.getSelection() == null || grupoUrgencia.getSelection() == null) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos", "Error", JOptionPane.PLAIN_MESSAGE);
                return;
            }

            try {
                duracion = Integer.parseInt(duracionStr);
                if (duracion <= 0) {
                     JOptionPane.showMessageDialog(this, "La duración debe ser un número positivo", "Error", JOptionPane.PLAIN_MESSAGE);
                     return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La duración debe ser un número válido", "Error", JOptionPane.PLAIN_MESSAGE);
                return;
            }

            if (rbAlta.isSelected()) urgencia = 1;
            else if (rbMedia.isSelected()) urgencia = 2;
            else urgencia = 3;

            if (rbCocina.isSelected()) departamento = "Cocina";
            else if (rbBarra.isSelected()) departamento = "Barra";
            else if (rbCaja.isSelected()) departamento = "Caja";
            else departamento = "Otro";

            // Asignación de empleado (usa el Árbol Binario o crea un temporal)
            if (departamento.equals("Otro")) {
                String nombreEmpleado = JOptionPane.showInputDialog(this, "Ingrese el nombre del nuevo empleado:");
                if (nombreEmpleado == null || nombreEmpleado.trim().isEmpty()) return;
                empleadoAsignado = restaurante.crearEmpleadoTemporal(nombreEmpleado.trim());
            } else {
                empleadoAsignado = restaurante.asignarEmpleado(departamento);
                if (empleadoAsignado == null) {
                    JOptionPane.showMessageDialog(this, "No hay empleados disponibles en ese departamento", "Error", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
            }
            
            // Lógica de ID y asignación de la tarea al empleado
            int idUnico = restaurante.generarIdLista();
            empleadoAsignado.setOcupado(true);
            Tarea t = new Tarea(descripcion, departamento, urgencia, empleadoAsignado, duracion); 
            t.setIdLista(idUnico);
            empleadoAsignado.setTareaActual(t);
            
            // Lógica de Dependencia y prevención de ciclos (Grafo)
            if (tareaDependiente != null) {
                // Llama a la función que revisa si se forma un ciclo
                if (restaurante.creaCiclo(t, tareaDependiente)) {
                     JOptionPane.showMessageDialog(this, "ERROR: La dependencia crea un ciclo", "Error de Grafo", JOptionPane.ERROR_MESSAGE);
                     empleadoAsignado.setOcupado(false);
                     empleadoAsignado.setTareaActual(null);
                     return;
                }
                 // Si no hay ciclo, establece la dependencia
                 t.setTareaPrevia(tareaDependiente);
                 tareaDependiente = null;
                 JOptionPane.showMessageDialog(this, "Dependencia establecida con: " + t.getTareaPrevia().getDescripcion());
            }
            
            // Decide a qué estructura agregar la tarea según el botón presionado
            if (e.getSource() == btnPila) pila.push(t);
            else if (e.getSource() == btnCola) cola.add(t);
            else if (e.getSource() == btnLista) lista.insertar(t);

            // Limpiamos los campos y actualizamos la vista
            campoDescripcion.setText("");
            campoDuracion.setText("");
            grupoUrgencia.clearSelection();
            grupoDepartamento.clearSelection();
            actualizarAreas();
        };

        btnPila.addActionListener(agregar);
        btnCola.addActionListener(agregar);
        btnLista.addActionListener(agregar);
        
        // Este botón permite al usuario seleccionar qué tarea será la previa para la siguiente que cree
        btnDependencia.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID (Número) de la tarea previa:");
            
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int idPrevia = Integer.parseInt(idStr.trim());
                    Tarea tareaPrevia = restaurante.buscarTareaPorId(idPrevia);
                    
                    if (tareaPrevia != null) {
                        tareaDependiente = tareaPrevia;
                        JOptionPane.showMessageDialog(this, "Dependencia establecida con la tarea: " + tareaPrevia.getDescripcion() + " (ID: " + idPrevia + "). La próxima tarea que cree dependerá de esta");
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró la tarea previa con el ID " + idPrevia, "Error", JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido", "Error", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        // Botón "Completar Urgente"
        btnAtenderPila.addActionListener(e -> {
            Tarea t = pila.pop();
            if (t == null) {
                JOptionPane.showMessageDialog(this, "No hay tareas urgentes pendientes");
            } else if (t.getTareaPrevia() != null && t.getTareaPrevia().getEmpleadoAsignado().isOcupado()) {
                // Revisión de Grafo: Si la previa está ocupada, no se puede empezar
                JOptionPane.showMessageDialog(this, "La tarea urgente '" + t.getDescripcion() + 
                                                    "' no se puede atender hasta que se complete la tarea previa: '" + 
                                                    t.getTareaPrevia().getDescripcion() + "'",
                                                    "Tarea con Dependencia Activa", JOptionPane.PLAIN_MESSAGE);
                pila.push(t); // La volvemos a meter para que no se pierda
            } else {
                // Si la previa está lista o no existe, la completamos y liberamos al empleado
                JOptionPane.showMessageDialog(this, "Completando tarea urgente: " + t);
                restaurante.liberarEmpleado(t.getEmpleadoAsignado());
            }
            actualizarAreas();
        });

        // Botón "Completar Programada"
        btnAtenderCola.addActionListener(e -> {
            Tarea t = cola.poll();
            if (t == null) {
                JOptionPane.showMessageDialog(this, "No hay tareas programadas pendientes");
            } else if (t.getTareaPrevia() != null && t.getTareaPrevia().getEmpleadoAsignado().isOcupado()) {
                JOptionPane.showMessageDialog(this, "No se puede atender la tarea '" + t.getDescripcion() + 
                                                    "' hasta que se complete la tarea previa: '" + 
                                                    t.getTareaPrevia().getDescripcion() + "'",
                                                    "Tarea con Dependencia Activa", JOptionPane.PLAIN_MESSAGE);
                cola.add(t); // La volvemos a meter para que no se pierda
            } else {
                JOptionPane.showMessageDialog(this, "Completando tarea programada: " + t);
                restaurante.liberarEmpleado(t.getEmpleadoAsignado());
            }
            actualizarAreas();
        });
        

        // Lógica del nuevo botón "Completar de Lista"
        // Esto permite liberar al empleado de la Tarea 2
        // y la saca de la lista, lo que desbloquea la Tarea 3
        btnCompletarLista.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la tarea a COMPLETAR de la lista:");
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    // Buscamos la tarea
                    Tarea tareaCompletar = restaurante.buscarTareaPorId(id); 

                    if (tareaCompletar != null) {
                        
                        // Solo permitimos completar si está realmente en la ListaTareas
                        if (lista.buscarPorId(id) == null) {
                            JOptionPane.showMessageDialog(this, "La tarea con ID " + id + " no se encuentra en la Lista de Departamento", "Error", JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                        
                        // 1. Liberar al empleado (esto resuelve el bloqueo de T3)
                        restaurante.liberarEmpleado(tareaCompletar.getEmpleadoAsignado());
                        
                        // 2. Eliminar la tarea de la lista (simula la finalización)
                        if (lista.eliminar(id)) {
                             JOptionPane.showMessageDialog(this, "Tarea con ID " + id + " completada y empleado liberado");
                        } else {
                             // Advertencia si la eliminación falló por alguna razón
                             JOptionPane.showMessageDialog(this, "Error al eliminar la tarea de la lista, pero el empleado fue liberado", "Advertencia", JOptionPane.PLAIN_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró la tarea con ese ID", "Error", JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido", "Error", JOptionPane.PLAIN_MESSAGE);
                }
            }
            actualizarAreas();
        });

        // Botón "Eliminar de Lista"
        btnEliminarLista.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la tarea a eliminar de la lista:");
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    // Usar el método general de búsqueda
                    Tarea tareaEliminar = restaurante.buscarTareaPorId(id); 
                    
                    if (tareaEliminar != null) {
                         // Revisión crítica de Grafo: ¿Alguien depende de esta tarea
                         Tarea tareaDependiente = restaurante.esTareaPreviaDeAlguien(tareaEliminar);
                
                         if (tareaDependiente != null) {
                            JOptionPane.showMessageDialog(this, "ERROR: La tarea '" + tareaEliminar.getDescripcion() + 
                                                                "' no puede ser eliminada porque otra tarea depende de ella: '" + 
                                                                tareaDependiente.getDescripcion() + "'", 
                                                                "Error de Dependencia", JOptionPane.PLAIN_MESSAGE);
                            return; 
                         }
                        
                        // Si nadie depende de ella, la eliminamos y liberamos al empleado
                        restaurante.liberarEmpleado(tareaEliminar.getEmpleadoAsignado());
                        if (lista.eliminar(id)) {
                             JOptionPane.showMessageDialog(this, "Tarea con ID " + id + " eliminada correctamente");
                        } else {
                            JOptionPane.showMessageDialog(this, "No se encontró la tarea con ese ID en la lista", "Error", JOptionPane.PLAIN_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró la tarea con ese ID", "Error", JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido", "Error", JOptionPane.PLAIN_MESSAGE);
                }
            }
            actualizarAreas();
        });
        
        // Botón "Calcular Tiempo Total" (Usa Recursividad)
        btnTiempoEstimado.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la ÚLTIMA tarea en la cadena de dependencia:");
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Tarea tareaFinal = restaurante.buscarTareaPorId(id);
                    
                    if (tareaFinal != null) {
                        int tiempoTotal = restaurante.calcularTiempoEstimadoRecursivo(tareaFinal);
                        int horas = tiempoTotal / 60;
                        int minutos = tiempoTotal % 60;
                        JOptionPane.showMessageDialog(this, "Tiempo estimado total para completar la cadena de tareas a partir de '" + tareaFinal.getDescripcion() + "':\n" + 
                                                                horas + " hora(s) y " + minutos + " minuto(s) (" + tiempoTotal + " minutos)");
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró la tarea con ese ID", "Error", JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido", "Error", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        
        // Botón "Calcular Inicio Temprano" (Usa el algoritmo de Grafo/CPM)
        btnInicioTemprano.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la Tarea a examinar:");
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Tarea tarea = restaurante.buscarTareaPorId(id);
                    
                    if (tarea != null) {
                        int inicioTemprano = restaurante.calcularInicioMasTemprano(tarea);
                        int horas = inicioTemprano / 60;
                        int minutos = inicioTemprano % 60;
                        
                        String mensaje = "La tarea '" + tarea.getDescripcion() + "' (ID " + id + ") tiene un Tiempo de Inicio Más Temprano de:\n" +
                                         horas + " hora(s) y " + minutos + " minuto(s) (" + inicioTemprano + " minutos)";
                        
                        JOptionPane.showMessageDialog(this, mensaje, "Cálculo de Inicio Temprano", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró la tarea con ese ID", "Error", JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido", "Error", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        // Botón "Ordenar Lista" (Usa Merge Sort - Divide y Vencerás)
        btnOrdenarLista.addActionListener(e -> {
            lista.ordenarPorPrioridad();
            JOptionPane.showMessageDialog(this, "La lista de tareas ha sido ordenada por prioridad");
            actualizarAreas();
        });

        // Botón "Buscar Empleado" (Usa el Hash Map para búsqueda rápida)
        btnBuscarEmpleado.addActionListener(e -> {
            String consulta = campoBuscarEmpleado.getText().trim();
            if (consulta.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un ID para buscar", "Error", JOptionPane.PLAIN_MESSAGE);
                return;
            }

            // Usa el Hash Map para buscar al empleado en O(1)
            Empleado empleadoEncontrado = restaurante.buscarEmpleado(consulta);

            if (empleadoEncontrado != null) {
                String info = "Empleado encontrado:\n" +
                        "ID: " + empleadoEncontrado.getId() + "\n" +
                        "Nombre: " + empleadoEncontrado.getNombre() + "\n" +
                        "Departamento: " + empleadoEncontrado.getDepartamento() + "\n" +
                        "Estado: " + (empleadoEncontrado.isOcupado() ? "Ocupado" : "Disponible");
                
                if (empleadoEncontrado.isOcupado()) {
                    Tarea tarea = empleadoEncontrado.getTareaActual();
                    info += "\n\nTarea actual:\n" +
                            "Descripción: " + tarea.getDescripcion() + "\n" +
                            "Urgencia: " + (tarea.getUrgencia() == 1 ? "Alta" : tarea.getUrgencia() == 2 ? "Media" : "Baja") + "\n" +
                            "Duración: " + tarea.getDuracionMinutos() + " minutos";
                }
                areaInfoEmpleado.setText(info);
            } else {
                areaInfoEmpleado.setText("Empleado no encontrado");
            }
        });

        // Botón "Ver Empleados"
        btnVerEmpleados.addActionListener(e -> {
            // Obtiene la lista completa de empleados del Hash Map y la muestra en una tabla
            List<Empleado> todosEmpleados = restaurante.getTodosLosEmpleados();
            StringBuilder infoEmpleados = new StringBuilder();

            infoEmpleados.append(String.format("%-10s %-20s %-15s %-15s %s\n", "ID", "Nombre", "Departamento", "Estatus", "Urgencia Tarea"));
            infoEmpleados.append("---------------------------------------------------------------------------\n");

            for (Empleado emp : todosEmpleados) {
                String estatus = emp.isOcupado() ? "Ocupado" : "Disponible";
                String urgenciaTarea = "N/A";
                if (emp.isOcupado() && emp.getTareaActual() != null) {
                    urgenciaTarea = switch (emp.getTareaActual().getUrgencia()) {
                        case 1 -> "Alta";
                        case 2 -> "Media";
                        default -> "Baja";
                    };
                }
                infoEmpleados.append(String.format("%-10s %-20s %-15s %-15s %s\n", emp.getId(), emp.getNombre(), emp.getDepartamento(), estatus, urgenciaTarea));
            }

            JTextArea textArea = new JTextArea(infoEmpleados.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(600, 300));

            JOptionPane.showMessageDialog(this, scrollPane, "Lista de Empleados", JOptionPane.PLAIN_MESSAGE);
        });

        pack(); // Ajusta la ventana al tamaño de todos los componentes
        setLocationRelativeTo(null); // Centra la ventana
        setVisible(true);
        actualizarAreas(); // Muestra las listas por primera vez
    }

    //Función que revisa las 3 estructuras de datos y actualiza el texto en la GUI
    private void actualizarAreas() {

        
        // Función auxiliar para obtener la línea de formato detallada
        TareaFormato formato = (t) -> {
            String urgTxt = switch (t.getUrgencia()) {
                case 1 -> "Alta";
                case 2 -> "Media";
                default -> "Baja";
            };
            String dependencia = t.getTareaPrevia() != null ? " (DEPENDE DE ID " + t.getTareaPrevia().getIdLista() + ")" : "";
            return "ID: " + t.getIdLista() + " | Tarea: " + t.getDescripcion() + " | Depto: " + t.getDepartamento() + 
                   " | Empleado: " + t.getEmpleadoAsignado().getNombre() + " (ID " + t.getEmpleadoAsignado().getId() + 
                   ") | Urgencia: " + urgTxt + " | Duración: " + t.getDuracionMinutos() + " min" + dependencia + "\n";
        };
        
        // Pila
        areaPila.setText("TAREAS URGENTES (PILA)\n");
        // El formato de la Pila muestra la última tarea agregada (cima) primero
        for (Tarea t : pila.getAll()) {
            areaPila.append(formato.format(t));
        }

        // Cola de Prioridad
        areaCola.setText("TAREAS PROGRAMADAS (COLA DE PRIORIDAD)\n");
        // El formato de la Cola muestra la tarea de más alta prioridad primero
        for (Tarea t : cola.getAll()) {
            areaCola.append(formato.format(t));
        }

        // Lista
        areaLista.setText("TAREAS POR DEPARTAMENTO (LISTA)\n");
        // El formato de la Lista (sin orden específico, a menos que se ordene con Merge Sort)
        for (Tarea t : lista.getAll()) {
            areaLista.append(formato.format(t));
        }

    }
    
    // Interfaz funcional para definir el formato de texto de la tarea de forma limpia (Lambda)
    @FunctionalInterface
    private interface TareaFormato {
        String format(Tarea t);
    }
    
    public static void main(String[] args) {
        // Esto arranca la aplicación
        RestaurantePro restaurante = new RestaurantePro();
        SwingUtilities.invokeLater(() -> new RestauranteAppGUI(restaurante));
    }
}