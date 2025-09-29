# Proyecto: Gestión de Tareas en Restaurante
![Java](https://img.shields.io/badge/Java-17-blue) 
![Status](https://img.shields.io/badge/Status-Avanzado%20Proyecto-success) 
![License: MIT](https://img.shields.io/badge/License-MIT-yellow)

<img width="866" height="650" alt="image" src="https://github.com/user-attachments/assets/a990e1ac-c300-4c9c-b54a-fc1ab2062eeb" />

**Materia:** Estructura de Datos  
**Maestra:** Blanca Aracely Aranda Machorro  
**Fecha:** 3/10/25  

---

## Índice
1. [Alcance](#1-alcance)
2. [Análisis](#2-análisis)  
3. [Diseño](#3-diseño)  
4. [Resumen de clases](#4-resumen-de-clases)  
5. [Flujo del programa](#5-flujo-del-programa)  
6. [Desarrollo](#6-desarrollo)  
7. [Implementación](#7-implementación)  
8. [Pruebas](#8-pruebas)  
9. [Resultados de pruebas](#9-resultados-de-pruebas)  
10. [Glosario de términos](#10-glosario-de-términos)  
11. [Diagramas](#11-diagramas)  
12. [Conclusión](#12-conclusión)  
13. [Pasos del proyecto](#13-pasos-del-proyecto)  
14. [Rúbrica de evaluación](#14-rúbrica-de-evaluación)  
15. [Autores](#15-autores)  

---

## 1. Alcance
El proyecto busca proporcionar una solución sencilla y funcional para la **gestión de tareas en un restaurante**.  

Funciones principales:  
- Registrar tareas con nivel de urgencia, prioridad y departamento.  
- Asignar tareas a empleados del restaurante.  
- Administrar tareas en diferentes estructuras de datos:  
  - **Pila (LIFO):** tareas urgentes.  
  - **Cola (FIFO):** tareas programadas.  
  - **Cola con prioridades:** tareas con distintos niveles de importancia.  
  - **Lista:** organización por departamentos.  
- Visualizar y atender tareas desde **consola** o desde una **interfaz gráfica (Swing GUI)**.  

---

## 2. Análisis

### Situación problema
Un restaurante maneja simultáneamente órdenes de clientes, preparación de alimentos, pagos y organización de personal. Sin un sistema, las tareas pueden perderse o gestionarse en un orden ineficiente.  

### Justificación
El uso de **estructuras de datos** permite organizar las tareas de acuerdo con su naturaleza y prioridad:  
- **Pila:** emergencias inmediatas.  
- **Cola:** tareas programadas en orden de llegada.  
- **Cola de prioridades:** manejo flexible de importancia.  
- **Lista:** clasificación departamental.  

### Requerimientos funcionales
1. Crear y registrar una tarea con descripción, prioridad, urgencia y departamento.  
2. Asignar empleados a tareas.  
3. Visualizar todas las tareas clasificadas.  
4. Atender tareas en pila, cola o cola con prioridades.  
5. Eliminar tareas de la lista por descripción.  
6. Ejecutar desde consola o GUI.  

### Requerimientos no funcionales
- Lenguaje: **Java (JDK 17+)**  
- Ejecución: **consola y GUI**  
- Modularidad con clases separadas (`Tarea`, `Empleado`, `ColaTareas`, `ColaPrioridades`, etc.).  

---

## 3. Diseño

### Clases principales
- `Tarea`: representa cada tarea del restaurante.  
- `Empleado`: administra la información de los empleados.  
- `PilaTareas`: gestiona tareas urgentes.  
- `ColaTareas`: gestiona tareas programadas.  
- `ColaPrioridades`: gestiona tareas por importancia.  
- `ListaTareas`: organiza tareas por departamentos.  
- `RestauranteAppGUI`: interfaz gráfica del sistema (581 líneas, incluye menús, botones y eventos).  
- `RestaurantePro`: versión consola con menú interactivo (231 líneas).  
- `Main`: punto de inicio de la aplicación.  

---

## 4. Resumen de clases

### `Tarea`  
Clase base que representa una actividad dentro del restaurante.  
**Atributos principales:**  
- Descripción de la tarea.  
- Departamento asignado.  
- Nivel de urgencia o prioridad.  

---

### `Empleado`  
Clase que modela a un trabajador del restaurante.  
**Características:**  
- Contiene nombre, rol y lista de tareas asignadas.  
- Permite relacionar empleados con tareas específicas.  

---

### `PilaTareas`  
Estructura basada en **pila (Stack)**.  
- Administra tareas urgentes.  
- Sigue el principio **LIFO (Last In, First Out)**: la última tarea registrada es la primera en atenderse.  

---

### `ColaTareas`  
Estructura basada en **cola (Queue)**.  
- Administra tareas programadas.  
- Sigue el principio **FIFO (First In, First Out)**: la primera tarea registrada es la primera en atenderse.  

---

### `ColaPrioridades`  
Estructura de **cola con prioridad**.  
- Ordena las tareas según su nivel de importancia (alta, media, baja).  
- Garantiza que las tareas críticas se atiendan primero.  

---

### `ListaTareas`  
Estructura dinámica basada en lista.  
- Permite agregar, buscar y eliminar tareas.  
- Ideal para organizar tareas por departamento o categoría.  

---

### `RestaurantePro`  
Versión **consola completa** del sistema.  
- Integra pila, cola, lista y cola de prioridades.  
- Presenta un menú interactivo para gestionar todas las tareas.  
- Se probó con casos de uso reales para validar su funcionamiento.  

---

### `RestauranteAppGUI`  
Versión con **interfaz gráfica en Java Swing**.  
- Más de 500 líneas de código para construir la interfaz.  
- Incluye menús, botones, ventanas emergentes y tablas.  
- Permite la gestión de tareas de manera más intuitiva.  

---

### `Main`  
Clase principal del proyecto.  
- Punto de entrada al programa.  
- Permite iniciar tanto la versión **consola** (`RestaurantePro`) como la versión **gráfica** (`RestauranteAppGUI`).  

---

## 5. Flujo del programa

1. El usuario inicia el programa desde **Main.java**.  
2. Puede elegir entre ejecutar la versión **consola (`RestaurantePro`)** o **gráfica (`RestauranteAppGUI`)**.  
3. En consola se despliega un **menú textual** con opciones para agregar, atender o eliminar tareas.  
4. En GUI se muestran **botones y ventanas**, que cumplen las mismas funciones que el menú de consola.  
5. Las estructuras (`Pila`, `Cola`, `Lista`, `ColaPrioridades`) gestionan las tareas internamente.  
6. El programa finaliza al seleccionar la opción de salir.  

---

## 6. Desarrollo
- Lenguaje: **Java**.  
- Uso de **POO y estructuras de datos**.  
- Se implementaron pilas, colas, listas y colas de prioridad.  
- Dos modos de uso: **consola (texto)** y **interfaz gráfica con Swing**.  

---

## 7. Implementación

### Entorno de ejecución
- **Windows, Linux, MacOS**  
- **JDK 17+**  

### Pasos de compilación y ejecución

#### Ejecución en consola
```bash
# Entrar al directorio
cd "Programa Restaurante"

# Compilar todos los archivos
javac *.java

# Ejecutar versión consola
java RestaurantePro
```

#### Ejecución con interfaz gráfica
```bash
# Ejecutar versión con GUI
java RestauranteAppGUI
```

---

## 8. Pruebas

### Casos de prueba
1. Agregar tarea urgente → aparece en la pila.  
2. Atender tarea urgente → se retira la última ingresada.  
3. Agregar tarea programada → aparece en la cola.  
4. Atender tarea programada → se retira la primera ingresada.  
5. Agregar tarea con prioridad → aparece en la cola con prioridad.  
6. Eliminar tarea de lista → desaparece correctamente.  
7. Probar con GUI → operaciones accesibles mediante botones.  

### Resultados esperados
✔ Pila respeta LIFO.  
✔ Cola respeta FIFO.  
✔ Cola de prioridades respeta orden de importancia.  
✔ Lista permite búsquedas y eliminaciones.  
✔ Interfaz gráfica funciona correctamente.  

---

## 9. Resultados de pruebas
- Se comprobó el correcto funcionamiento de pila, cola, lista y cola de prioridades.  
- El sistema funciona en consola y GUI.  
- La asignación de tareas a empleados fue validada.  
- La interfaz gráfica respondió correctamente a eventos y entradas del usuario.  

---

## 10. Glosario de términos
- **Pila (Stack):** estructura LIFO.  
- **Cola (Queue):** estructura FIFO.  
- **Cola con prioridad:** estructura que organiza por importancia.  
- **Lista (List):** colección dinámica.  
- **Empleado:** entidad que recibe asignación de tareas.  
- **GUI:** interfaz gráfica de usuario.  

---

## 11. Diagramas

<img width="921" height="380" alt="image" src="https://github.com/user-attachments/assets/9f3970ca-e101-4303-9460-9ea437cfeedc" />

<img width="1280" height="1052" alt="image" src="https://github.com/user-attachments/assets/1615d301-3362-4d1c-9b5c-3b1be8e30616" />

---

## 12. Conclusión
- **André:** Aprendí a aplicar pilas, colas, listas y colas de prioridad en un sistema real.  
- **Jevick:** Entendí cómo combinar estructuras de datos con GUI en Java.  
- **Jordán:** Reforcé conceptos de diseño modular y programación en capas.  
- **Roberto:** Valoro la importancia de planificar antes de implementar.  

---

## 13. Pasos del proyecto
1. Definir el alcance del sistema.  
2. Analizar las necesidades del restaurante.  
3. Diseñar clases y estructuras de datos.  
4. Implementar código en Java con POO.  
5. Separar versiones: consola y GUI.  
6. Probar con casos de uso reales.  
7. Documentar resultados y aprendizajes.  

---

## 14. Rúbrica de evaluación
| Criterio | Puntos |
|----------|--------|
| Alcance y análisis | 15 |
| Diseño y diagramas | 15 |
| Implementación funcional | 25 |
| Pruebas y resultados | 20 |
| Documentación y README | 15 |
| Presentación y claridad | 10 |

---

## 15. Autores
- André  
- Jevick  
- Jordán  
- Roberto  
