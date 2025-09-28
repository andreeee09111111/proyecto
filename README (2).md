# Proyecto: Gestión de Tareas en Restaurante
![Java](https://img.shields.io/badge/Java-17-blue) 
![Status](https://img.shields.io/badge/Status-Avanzado%20Proyecto-success) 
![License: MIT](https://img.shields.io/badge/License-MIT-yellow)

**Materia:** Estructura de Datos  
**Maestra:** Blanca Aracely Aranda Machorro  
**Fecha:** 3/10/25

---

## Índice
1. [Alcance](#1-alcance)
2. [Análisis](#2-análisis)  
3. [Diseño](#3-diseño)  
4. [Desarrollo](#4-desarrollo)  
5. [Implementación](#5-implementación)  
6. [Pruebas](#6-pruebas)  
7. [Resultados de pruebas](#7-resultados-de-pruebas)  
8. [Glosario de términos](#8-glosario-de-términos)  
9. [Diagramas](#9-diagramas)  
10. [Conclusión](#10-conclusión)  
11. [Pasos del proyecto](#11-pasos-del-proyecto)  
12. [Rúbrica de evaluación](#12-rúbrica-de-evaluación)  
13. [Autores](#13-autores)  

---

## 1. Alcance
El proyecto busca proporcionar una solución sencilla y funcional para la **gestión de tareas en un restaurante**.  

Funciones principales:  
- Registrar tareas con nivel de urgencia y departamento.  
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
Un restaurante maneja simultáneamente órdenes de clientes, preparación de alimentos, pagos y organización de personal. Si no se gestionan de forma ordenada, se generan retrasos y errores.  

### Justificación
El uso de **estructuras de datos** permite organizar las tareas de acuerdo con su naturaleza y prioridad.  
- Pila: emergencias inmediatas.  
- Cola: tareas programadas en orden de llegada.  
- Cola de prioridades: manejo flexible de importancia.  
- Lista: clasificación departamental.  

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
- `RestauranteAppGUI`: interfaz gráfica del sistema.  
- `RestaurantePro`: versión extendida con varias estructuras.  
- `Main`: punto de inicio de la aplicación.  

### Diagrama conceptual (simplificado)
```
Main → inicia RestaurantePro o RestauranteAppGUI
RestaurantePro → usa PilaTareas / ColaTareas / ColaPrioridades / ListaTareas
RestauranteAppGUI → interfaz gráfica con menús
Tarea y Empleado → entidades base
```

---

## 4. Desarrollo
- Lenguaje: **Java**.  
- Uso de **POO y estructuras de datos**.  
- Se implementaron pilas, colas, listas y colas de prioridad.  
- Dos modos de uso: **consola (texto)** y **interfaz gráfica con Swing**.  

---

## 5. Implementación

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
java Main
```

#### Ejecución con interfaz gráfica
```bash
# Ejecutar versión con GUI
java RestauranteAppGUI
```

---

## 6. Pruebas

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

## 7. Resultados de pruebas
- Se comprobó el correcto funcionamiento de pila, cola, lista y cola de prioridades.  
- El sistema funciona en consola y GUI.  
- La asignación de tareas a empleados fue validada.  

---

## 8. Glosario de términos
- **Pila (Stack):** estructura LIFO.  
- **Cola (Queue):** estructura FIFO.  
- **Cola con prioridad:** estructura que organiza por importancia.  
- **Lista (List):** colección dinámica.  
- **Empleado:** entidad que recibe asignación de tareas.  
- **GUI:** interfaz gráfica de usuario.  

---

## 9. Diagramas
*(Agregar aquí diagramas UML o de flujo si están disponibles).*  

---

## 10. Conclusión
- **André:** Aprendí a aplicar pilas, colas, listas y colas de prioridad en un sistema real.  
- **Jevick:** Entendí cómo combinar estructuras de datos con GUI en Java.  
- **Jordán:** Reforcé conceptos de diseño modular y programación en capas.  
- **Roberto:** Valoro la importancia de planificar antes de implementar.  

---

## 11. Pasos del proyecto
1. Definir el alcance del sistema.  
2. Analizar las necesidades del restaurante.  
3. Diseñar clases y estructuras de datos.  
4. Implementar código en Java con POO.  
5. Separar versiones: consola y GUI.  
6. Probar con casos de uso reales.  
7. Documentar resultados y aprendizajes.  

---

## 12. Rúbrica de evaluación
| Criterio | Puntos |
|----------|--------|
| Alcance y análisis | 15 |
| Diseño y diagramas | 15 |
| Implementación funcional | 25 |
| Pruebas y resultados | 20 |
| Documentación y README | 15 |
| Presentación y claridad | 10 |

---

## 13. Autores
- André  
- Jevick  
- Jordán  
- Roberto  
