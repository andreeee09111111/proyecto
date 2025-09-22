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
9. [Diagrama](#9-diagrama)
10. [Conclusión](#10-conclusión)
11. [Pasos del proyecto](#11-pasos-del-proyecto)  
12. [Rúbrica de evaluación](#12-rúbrica-de-evaluación)  
13. [autores](#13-autores)

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
    
El alcance está delimitado a una interfaz por consola, con menús de texto 
interactivos, sin incluir gráficos ni conexión a base de datos.

 **Nota:** Se limita a una **interfaz por consola** con menús de texto interactivos.  
 
## 1.5. CASO

En un restaunrante de hamburguesas necesita un programa para gestionar todo su negocio.

La gestión de tareas en el restaurante puede ser un desafío constante coordinar al personal, organizar pedidos, supervisar inventarios y asegurar un servicio eficiente requiere de un sistema claro y funcional. Este proyecto surge con el propósito de ofrecer una solución sencilla pero efectiva que facilite la organización diaria de las actividades dentro del restaurante.

Con esta herramienta buscamos optimizar el flujo de trabajo, mejorar la comunicación entre el equipo y garantizar una mejor experiencia tanto para los empleados como para los clientes.

---

## 2. Análisis

### Situación problema
En un restaurante existen múltiples tareas simultáneas: preparación de platillos, atención a clientes, cobros en caja y organización del personal.  
Si no se gestionan con orden, pueden acumularse, olvidarse o atenderse en un orden ineficiente.  

### Justificación
La elección de **estructuras de datos (pila, cola, lista)** responde a la necesidad de organizar tareas según su naturaleza:  
- **Pila (LIFO):** para emergencias o actividades críticas.  
- **Cola (FIFO):** para secuencia de actividades programadas.  
- **Lista:** flexible para clasificar tareas por departamento.  

### Requerimientos funcionales
1. Registrar una nueva tarea (descripción, departamento, urgencia).  
2. Guardar la tarea en la estructura correspondiente.  
3. Mostrar todas las tareas clasificadas.  
4. Atender la tarea urgente más reciente (pila).  
5. Atender la tarea programada más antigua (cola).  
6. Eliminar tareas de la lista por descripción.  

### Requerimientos no funcionales
- Lenguaje: **Java**  
- Ejecución: **consola**  
- Organización modular con paquetes (`estructuras`, `ui`)  

---

## 3. Diseño

### Clases principales
- `Tarea`: contiene la descripción, departamento y nivel de urgencia.  
- `PilaTareas`: administra las tareas urgentes (Stack).  
- `ColaTareas`: administra las tareas programadas (Queue).  
- `ListaTareas`: administra las tareas por departamento (ArrayList).  
- `ConsolaUI`: provee el menú interactivo.  
- `RestauranteApp`: inicia el sistema.  

### Diagrama conceptual
```
RestauranteApp → inicia ConsolaUI
ConsolaUI → gestiona la interacción y distribuye las tareas
PilaTareas / ColaTareas / ListaTareas → almacenan y gestionan
Tarea → clase base
```

### Interfaz de usuario
Ejemplo de menú:  

```
Restaurante La Buena Mesa

1. Agregar tarea urgente (PILA)
2. Agregar tarea programada (COLA)
3. Agregar tarea por departamento (LISTA)
4. Ver todas las tareas
5. Atender tarea urgente
6. Atender tarea programada
7. Eliminar tarea de lista
8. Salir
```

---

## 4. Desarrollo
- Programado en **Java** con **POO**.  
- Paquetes para separar lógica (`estructuras`) e interfaz (`ui`).  
- Uso de `Stack`, `Queue`, `ArrayList`.  
- Validaciones para evitar errores.  

---

## 5. Implementación

### Entorno de ejecución
- Windows / Linux / MacOS  
- JDK 17+  

### Pasos de compilación y ejecución
```bash
# Compilar
javac main/RestauranteApp.java

# Ejecutar
java main.RestauranteApp
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

### Resultados de las pruebas
✔ La pila respeta LIFO  
✔ La cola respeta FIFO  
✔ La lista permite búsquedas y eliminaciones  
✔ El menú interactivo facilita la gestión  

---

## 7. Resultados de pruebas
- Todas las estructuras se comportaron como se esperaba.  
- La gestión de tareas se realizó de manera ordenada y eficiente.  

---

## 8. Glosario de términos
- **Pila (Stack):** estructura LIFO.  
- **Cola (Queue):** estructura FIFO.  
- **Lista (List):** colección dinámica.  
- **POO:** Programación Orientada a Objetos.  
- **JDK:** Kit de desarrollo de Java.  
- **Consola:** interfaz de texto.  

---
## 9. Diagrama
<img width="921" height="380" alt="image" src="https://github.com/user-attachments/assets/9f3970ca-e101-4303-9460-9ea437cfeedc" />
---

## 10. Conclusión
- **André:** Aplicar pilas, colas y listas me ayudó a comprender mejor estructuras de datos y POO.  
- **Jevick:** Aprendí cómo cada estructura organiza tareas en distintas situaciones del restaurante.  
- **Jordán:** Comprendí la importancia del diseño modular en Java y la relación LIFO/FIFO.  
- **Roberto:** Reforcé conceptos de estructuras de datos y validé que la planificación es clave.  

---

## 11. Pasos del proyecto
1. **Definir el alcance del proyecto**  
   Se estableció que el objetivo es gestionar tareas de un restaurante mediante estructuras de datos.  

2. **Analizar la situación problema del restaurante**  
   Se identificaron tareas críticas, programadas y por departamento que requieren organización.  

3. **Justificar el uso de estructuras de datos**  
   Se seleccionaron pila (urgentes), cola (programadas) y lista (departamentales) para optimizar la gestión.  

4. **Identificar requerimientos funcionales y no funcionales**  
   Se detallaron las operaciones, el lenguaje y la plataforma de ejecución.  

5. **Diseñar clases principales y diagrama conceptual**  
   Se definieron `Tarea`, `PilaTareas`, `ColaTareas`, `ListaTareas`, `ConsolaUI` y `RestauranteApp`.  

6. **Desarrollar el código en Java usando POO**  
   Se implementaron las clases y estructuras de datos, incluyendo métodos de agregar, eliminar y atender tareas.  

7. **Implementar en consola y preparar la ejecución**  
   Se creó un menú interactivo para que el usuario pueda manipular las tareas fácilmente.  

8. **Realizar pruebas con casos reales**  
   Se verificó el funcionamiento de pila (LIFO), cola (FIFO) y lista, así como el flujo del menú.  

9. **Documentar resultados, glosario y conclusiones**  
   Se recopilaron observaciones, terminología y aprendizajes obtenidos.  

10. **Entregar el proyecto con README, rúbrica y repositorio en GitHub**  
   Se preparó este README completo, incluyendo pasos, resultados y evaluación.  

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

