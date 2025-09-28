#  Proyecto: Gestión de Tareas en Restaurante
![Java](https://img.shields.io/badge/Java-17-blue) 
![Status](https://img.shields.io/badge/Status-avanzado%20Proyecto-success) 
![License: MIT](https://img.shields.io/badge/License-MIT-yellow)

<img width="866" height="650" alt="image" src="https://github.com/user-attachments/assets/a990e1ac-c300-4c9c-b54a-fc1ab2062eeb" />

**Materia:** Estructura de Datos  
**Maestra:** Blanca Aracely Aranda Machorro  
**Fecha:** 3/10/25  

---

##  Índice
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
- Registrar tareas según el nivel de urgencia.  
- Asignar tareas a diferentes departamentos (cocina, meseros, caja, etc.).  
- Visualizar todas las tareas pendientes en un formato estructurado.  
- Atender las tareas según la prioridad definida:  
  - **Urgentes:** mediante una pila (LIFO).  
  - **Programadas:** mediante una cola (FIFO).  
  - **Departamentales:** mediante una lista.  
  - **Con prioridades:** mediante una cola de prioridades.  
- Asignar tareas a **empleados** registrados.  

### Nota:  
El proyecto comenzó como una **interfaz por consola** con menús de texto interactivos.  
Actualmente incluye también un archivo `RestauranteAppGUI.java` con **interfaz gráfica inicial** (fase experimental).  

---

## 1.5. Caso
El cliente necesita gestionar las tareas de su negocio de hamburguesas.  

Coordinar al personal, organizar pedidos, supervisar inventarios y asegurar un servicio eficiente requiere de un sistema claro y funcional.  
Este proyecto surge con el propósito de ofrecer una solución sencilla pero efectiva que facilite la organización diaria de las actividades.  

Con esta herramienta buscamos optimizar el flujo de trabajo, mejorar la comunicación entre el equipo y garantizar una mejor experiencia para empleados y clientes.  

---

## 2. Análisis  

### Situación problema  
En un restaurante existen múltiples tareas simultáneas: preparación de platillos, atención de clientes, cobros en caja, organización del personal.  
Si no se gestionan con orden, pueden acumularse, olvidarse o atenderse en un orden ineficiente.  

### Justificación  
El uso de **estructuras de datos (pila, cola, lista, cola de prioridades)** responde a la necesidad de organizar tareas según su naturaleza:  
- **Pila (LIFO):** emergencias.  
- **Cola (FIFO):** actividades programadas.  
- **Lista:** clasificación por departamento.  
- **Cola de prioridades:** manejo avanzado de urgencias.  

### Requerimientos funcionales  
1. Registrar una nueva tarea (descripción, departamento, urgencia).  
2. Guardar la tarea en la estructura correspondiente.  
3. Mostrar todas las tareas clasificadas.  
4. Atender la tarea urgente más reciente (pila).  
5. Atender la tarea programada más antigua (cola).  
6. Eliminar tareas de la lista por descripción.  
7. Gestionar tareas por prioridad (`ColaPrioridades`).  
8. Registrar empleados y asignarles tareas.  

### Requerimientos no funcionales  
- Lenguaje: **Java 17+**  
- Ejecución: **consola y GUI (opcional)**  
- Organización modular con paquetes (`estructuras`, `ui`).  

---

## 3. Diseño  

### Clases principales  
- `Tarea`: contiene la descripción, departamento y urgencia.  
- `Empleado`: representa a los trabajadores del restaurante.  
- `PilaTareas`: administra las tareas urgentes.  
- `ColaTareas`: administra las tareas programadas.  
- `ColaPrioridades`: administra tareas con diferentes niveles de prioridad.  
- `ListaTareas`: administra tareas por departamento.  
- `ConsolaUI`: provee el menú interactivo en consola.  
- `RestauranteAppGUI`: interfaz gráfica inicial del sistema.  
- `RestaurantePro`: versión extendida de la aplicación.  
- `Main`: punto de inicio del programa.  

### Diagrama conceptual  
```
RestaurantePro/Main → inicia ConsolaUI o GUI
ConsolaUI → gestiona interacción en consola
RestauranteAppGUI → gestiona interacción gráfica
PilaTareas / ColaTareas / ColaPrioridades / ListaTareas → almacenan y gestionan
Empleado → clase para asignar tareas a trabajadores
Tarea → clase base
```  

### Interfaz de usuario (consola)  
```
Restaurante La Buena Mesa

1. Agregar tarea urgente (PILA)
2. Agregar tarea programada (COLA)
3. Agregar tarea por departamento (LISTA)
4. Agregar tarea con prioridad (COLA DE PRIORIDADES)
5. Registrar empleado
6. Ver todas las tareas
7. Atender tarea urgente
8. Atender tarea programada
9. Atender tarea con prioridad
10. Eliminar tarea de lista
11. Salir
```  

---

## 4. Desarrollo  
- Programado en **Java con POO**.  
- Separación por paquetes: `estructuras`, `ui`.  
- Uso de `Stack`, `Queue`, `ArrayList`, `PriorityQueue`.  
- Validaciones para evitar errores.  
- Implementación de consola + prototipo GUI (`RestauranteAppGUI`).  

---

## 5. Implementación  

### Entorno de ejecución  
- Windows / Linux / MacOS  
- JDK 17+  

### Pasos de compilación y ejecución  
```bash
# Compilar versión de consola
javac Codigo\ del\ avance\ del\ proyecto\ Estructura\ de\ datos3/Main.java
java Codigo\ del\ avance\ del\ proyecto\ Estructura\ de\ datos3.Main

# Ejecutar versión GUI
javac Codigo\ del\ avance\ del\ proyecto\ Estructura\ de\ datos3/RestauranteAppGUI.java
java Codigo\ del\ avance\ del\ proyecto\ Estructura\ de\ datos3.RestauranteAppGUI
```  

---

## 6. Pruebas  

### Casos de prueba  
1. Agregar tarea urgente → aparece en la pila.  
2. Atender tarea urgente → extrae la última ingresada.  
3. Agregar tarea programada → aparece en la cola.  
4. Atender tarea programada → extrae la primera ingresada.  
5. Agregar tarea por departamento → aparece en la lista.  
6. Eliminar tarea de lista → desaparece correctamente.  
7. Agregar tarea con prioridad → insertada en la cola prioritaria según nivel.  
8. Registrar empleado → aparece en la lista de empleados.  

### Resultados de las pruebas  
✔ Pila respeta LIFO  
✔ Cola respeta FIFO  
✔ ColaPrioridades respeta orden de prioridades  
✔ Lista permite búsquedas y eliminaciones  
✔ Empleados se registran correctamente  
✔ Menú interactivo y GUI básica funcionan  

---

## 7. Resultados de pruebas  
- Todas las estructuras se comportaron según lo esperado.  
- La gestión de tareas y empleados fue ordenada y eficiente.  

---

## 8. Glosario de términos  
- **Pila (Stack):** estructura LIFO.  
- **Cola (Queue):** estructura FIFO.  
- **Cola de Prioridades:** estructura que atiende primero elementos más importantes.  
- **Lista (List):** colección dinámica.  
- **POO:** Programación Orientada a Objetos.  
- **JDK:** Kit de desarrollo de Java.  
- **Consola:** interfaz de texto.  
- **GUI:** Interfaz gráfica de usuario.  

---

## 9. Diagramas  

<img width="1280" height="1052" alt="image" src="https://github.com/user-attachments/assets/202a4b3c-9ac9-4015-83d9-33cfad3be2a1" />


---

## 10. Conclusión  
- **André:** Aprendí cómo integrar pilas, colas, listas y ahora colas de prioridades en un sistema real.  
- **Jevick:** Comprendí cómo asignar tareas a empleados y la importancia de organizar prioridades.  
- **Jordán:** Valoro la diferencia entre una app de consola y los primeros pasos hacia una GUI en Java.  
- **Roberto:** Reforcé la importancia de planear la arquitectura modular y probar diferentes estructuras.  

---

## 11. Pasos del proyecto  
1. Definir alcance del proyecto.  
2. Analizar situación problema.  
3. Justificar uso de estructuras de datos.  
4. Identificar requerimientos funcionales y no funcionales.  
5. Diseñar clases y diagrama conceptual.  
6. Implementar código en Java con POO.  
7. Implementar consola y prototipo GUI.  
8. Realizar pruebas con casos reales.  
9. Documentar resultados, glosario y conclusiones.  
10. Entregar proyecto con README, rúbrica y repositorio.  

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
<img width="478" height="612" alt="image" src="https://github.com/user-attachments/assets/22edbdd6-2f3e-4124-b40f-642ffae592ec" />  
