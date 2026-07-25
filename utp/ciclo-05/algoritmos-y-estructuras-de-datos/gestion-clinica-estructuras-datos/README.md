# Gestión Clínica con Estructuras de Datos

Sistema académico en Java para simular la gestión básica de una clínica y demostrar la aplicación de estructuras de datos, algoritmos y Programación Orientada a Objetos.

El proyecto corresponde al curso **Algoritmos y Estructuras de Datos** de la UTP. Está desarrollado como aplicación de consola orientada a sustentación académica.

## Aviso de publicación

Este proyecto documenta una práctica académica desarrollada por Adrián Pisco como parte de su proceso formativo. No representa una publicación oficial de la universidad ni de una institución de salud.

Los datos usados son ficticios y existen solo para demostrar estructuras, algoritmos y organización de código. No deben interpretarse como información clínica real.

## Contexto académico

| Dato | Detalle |
|---|---|
| Institución | Universidad Tecnológica del Perú |
| Curso | Algoritmos y Estructuras de Datos |
| Ciclo | V |
| Tipo | Proyecto final académico |
| Caso de estudio | Gestión clínica académica |
| Lenguaje | Java |
| Interfaz | Consola |

## Objetivo

Representar procesos clínicos y administrativos mediante estructuras de datos vistas en el curso. El sistema trabaja con pacientes, doctores, citas, horarios, sala de espera, historial reciente, pagos, tratamientos, reportes y exportación CSV.

El objetivo no es reemplazar un sistema clínico real, sino demostrar criterio técnico al elegir estructuras de datos para un caso práctico.

## Alcance funcional

| Módulo | Responsabilidad |
|---|---|
| Pacientes | Registrar, mostrar, buscar, actualizar y eliminar pacientes. |
| Citas y horarios | Registrar citas y contabilizar horarios por doctor y día. |
| Sala de espera | Simular atención de pacientes mediante una cola FIFO. |
| Historial reciente | Registrar seguimiento de citas mediante una pila LIFO. |
| Doctores | Organizar doctores mediante un árbol binario de búsqueda. |
| Búsquedas | Aplicar búsqueda lineal sobre datos del dominio. |
| Ordenamientos | Aplicar Bubble Sort, QuickSort y MergeSort. |
| Tratamientos y pagos | Modelar recetas, tratamientos, boletas y facturas. |
| Reportes | Mostrar información consolidada para sustentación. |
| Persistencia | Exportar pacientes, citas y pagos a archivos CSV. |

## Conceptos aplicados

- Programación Orientada a Objetos.
- Encapsulamiento, herencia, abstracción, interfaces y polimorfismo.
- Arreglos y matrices.
- Tipos abstractos de datos.
- Listas enlazadas.
- Colas FIFO.
- Pilas LIFO.
- Árbol binario de búsqueda.
- Búsqueda lineal.
- Recursividad.
- Bubble Sort, QuickSort y MergeSort.
- Persistencia simple mediante CSV.

Para estudiar los conceptos desde el enfoque de Chilete DevPath, revisar el [glosario técnico](GLOSARIO.md).

## Arquitectura

```txt
gestion-clinica-estructuras-datos/
|-- src/pe/com/utp/
|   |-- app/              # Punto de entrada y menú de sustentación
|   |-- algoritmos/       # Búsqueda, ordenamiento y recursividad
|   |-- estructuras/      # Listas, colas, pilas, matrices y árboles
|   |-- interfaces/       # Contratos y TAD usados por las estructuras
|   |-- modelo/           # Entidades del dominio clínico
|   |-- persistencia/     # Exportación simple a CSV
|   |-- servicios/        # Lógica de aplicación
|   `-- util/             # Apoyo para consola, fechas, códigos y validaciones
|-- GLOSARIO.md
|-- README.md
`-- .gitignore
```

## Componentes principales

| Componente | Responsabilidad técnica |
|---|---|
| `Main` | Coordina el menú por consola y la demostración completa del sistema. |
| `PacienteService` | Administra pacientes y operaciones principales sobre el arreglo base. |
| `CitaService` | Gestiona citas usando almacenamiento en memoria. |
| `MatrizHorarios` | Representa disponibilidad o carga de citas por doctor y día. |
| `ListaPacientes` / `ListaCitas` | Implementan listas enlazadas simples para datos del dominio. |
| `ColaPacientes` | Implementa la sala de espera con comportamiento FIFO. |
| `PilaHistorialCitas` | Implementa seguimiento reciente con comportamiento LIFO. |
| `ArbolDoctoresBusqueda` | Implementa inserción, búsqueda, eliminación y recorridos de doctores. |
| `QuickSort`, `BubbleSortPacientes`, `MergeSortPagos` | Ordenan datos del dominio para sustentar algoritmos. |
| `ArchivoPaciente`, `ArchivoCita`, `ArchivoPago` | Exportan datos del sistema a archivos CSV. |

## Menú de sustentación

El punto de entrada es:

```txt
src/pe/com/utp/app/Main.java
```

La opción principal para revisar el flujo completo es:

```txt
10. Demostración completa AED
```

## Ejecución local

Desde la raíz del proyecto:

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java).FullName
java -cp out pe.com.utp.app.Main
```

## Estado actual

Proyecto terminado como entrega académica.

| Punto | Estado |
|---|---|
| Modelos principales | Implementado |
| Servicios de pacientes, citas, pagos y reportes | Implementado |
| Menú por consola | Implementado |
| Estructuras de datos | Implementado |
| Búsquedas y ordenamientos | Implementado |
| Exportación CSV | Implementado |
| Validación manual por menú | Implementado |

## Fuera de alcance

Este proyecto no incluye base de datos, interfaz gráfica, autenticación de usuarios ni persistencia completa con lectura desde archivos. Trabaja principalmente en memoria y usa exportación CSV como evidencia académica.

No debe usarse para registrar, procesar o almacenar información real de pacientes, doctores, pagos o atenciones médicas.

## Mejoras futuras

- Agregar pruebas automatizadas si el alcance del curso lo permite.
- Permitir carga inicial desde archivos CSV.
- Fortalecer validaciones de entrada.
- Separar mejor ejecución de demostración y operación interactiva.

## Relación con Chilete DevPath

Este proyecto forma parte de `academia` porque conserva evidencia de aprendizaje aplicada a Java, POO, algoritmos y estructuras de datos.
