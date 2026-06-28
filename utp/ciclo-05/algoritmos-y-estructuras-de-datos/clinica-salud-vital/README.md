# UTP Clínica Salud Vital

Sistema academico en Java para simular la gestion basica de una clinica y demostrar la aplicacion de estructuras de datos, algoritmos y Programacion Orientada a Objetos.

El proyecto corresponde al curso **Algoritmos y Estructuras de Datos** de la Universidad Tecnologica del Peru. Esta desarrollado como una aplicacion por consola orientada a sustentacion academica.

## Contexto academico

| Dato | Detalle |
|---|---|
| Institucion | Universidad Tecnologica del Peru |
| Curso | Algoritmos y Estructuras de Datos |
| Ciclo | V |
| Tipo | Proyecto final academico |
| Caso de estudio | Clinica Salud Vital |
| Lenguaje | Java |
| Interfaz | Consola |

## Objetivo del proyecto

Representar procesos clinicos y administrativos mediante estructuras de datos vistas en el curso. El sistema permite trabajar con pacientes, doctores, citas, horarios, sala de espera, historial reciente, pagos, tratamientos, reportes y exportacion CSV.

El objetivo principal no es reemplazar un sistema clinico real, sino demostrar criterio tecnico al seleccionar y aplicar estructuras de datos dentro de un caso practico.

## Alcance funcional

| Modulo | Responsabilidad |
|---|---|
| Pacientes | Registrar, mostrar, buscar, actualizar y eliminar pacientes. |
| Citas y horarios | Registrar citas y contabilizar horarios disponibles por doctor y dia. |
| Sala de espera | Simular atencion de pacientes mediante una cola FIFO. |
| Historial reciente | Registrar seguimiento de citas mediante una pila LIFO. |
| Doctores | Organizar doctores mediante un arbol binario de busqueda. |
| Busquedas | Aplicar busqueda lineal sobre arreglos y colecciones del sistema. |
| Ordenamientos | Aplicar Bubble Sort, QuickSort y MergeSort sobre datos del dominio. |
| Tratamientos y pagos | Modelar recetas, tratamientos, boletas y facturas. |
| Reportes | Mostrar informacion consolidada para sustentacion. |
| Persistencia | Exportar pacientes, citas y pagos a archivos CSV. |

## Conceptos aplicados

- Programacion Orientada a Objetos.
- Encapsulamiento, herencia, abstraccion, interfaces y polimorfismo.
- Arreglos unidimensionales.
- Matrices.
- Tipos abstractos de datos.
- Listas enlazadas.
- Colas FIFO.
- Pilas LIFO.
- Arbol binario de busqueda.
- Busqueda lineal.
- Recursividad.
- Bubble Sort.
- QuickSort generico con `Comparator`.
- MergeSort.
- Persistencia simple mediante archivos CSV.

Para estudiar los conceptos desde el enfoque de Chilete DevPath, revisar el [glosario tecnico](GLOSARIO.md).

## Arquitectura del proyecto

El codigo se organiza por responsabilidad para separar dominio, estructuras, algoritmos, servicios, persistencia y utilidades.

```txt
utp-clinica-salud-vital/
|-- src/pe/com/utp/
|   |-- app/              # Punto de entrada y menu de sustentacion
|   |-- algoritmos/       # Busqueda, ordenamiento y recursividad
|   |-- estructuras/      # Listas, colas, pilas, matrices y arboles
|   |-- interfaces/       # Contratos y TAD usados por las estructuras
|   |-- modelo/           # Entidades del dominio clinico
|   |-- persistencia/     # Exportacion simple a CSV
|   |-- servicios/        # Logica de aplicacion
|   `-- util/             # Apoyo para consola, fechas, codigos y validaciones
|-- GLOSARIO.md
|-- README.md
`-- .gitignore
```

## Componentes principales

| Componente | Descripcion tecnica |
|---|---|
| `Main` | Coordina el menu por consola y la demostracion completa del sistema. |
| `PacienteService` | Administra pacientes y operaciones principales sobre el arreglo base. |
| `CitaService` | Gestiona citas medicas usando almacenamiento en memoria. |
| `MatrizHorarios` | Representa disponibilidad o carga de citas por doctor y dia. |
| `ListaPacientes` / `ListaCitas` | Implementan listas enlazadas simples para datos del dominio. |
| `ColaPacientes` | Implementa la sala de espera con comportamiento FIFO. |
| `PilaHistorialCitas` | Implementa seguimiento reciente con comportamiento LIFO. |
| `ArbolDoctoresBusqueda` | Implementa insercion, busqueda, eliminacion y recorridos de doctores. |
| `QuickSort`, `BubbleSortPacientes`, `MergeSortPagos` | Demuestran algoritmos de ordenamiento aplicados al caso clinico. |
| `ArchivoPaciente`, `ArchivoCita`, `ArchivoPago` | Exportan datos del sistema a archivos CSV. |

## Menu de sustentacion

El punto de entrada es:

```txt
src/pe/com/utp/app/Main.java
```

Al ejecutar el programa se muestra un menu por consola:

```txt
1. Presentacion del sistema y datos base
2. Gestion de pacientes
3. Gestion de citas y horarios
4. Sala de espera de pacientes
5. Historial reciente y seguimiento clinico
6. Busquedas y ordenamientos
7. Arbol binario de doctores
8. Tratamientos, recetas y pagos
9. Persistencia, reportes y utilidades
10. Demostracion completa AED
0. Salir
```

Cada opcion explica el concepto aplicado antes de ejecutar la demostracion, lo que facilita la exposicion oral del proyecto.

## Ejecucion local

Desde la raiz del repositorio, compilar los archivos Java:

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java).FullName
```

Ejecutar la clase principal:

```powershell
java -cp out pe.com.utp.app.Main
```

Para revisar todo el flujo academico, seleccionar la opcion `10`.

## Evidencia esperada

Durante la ejecucion deben poder observarse:

- Datos base cargados en memoria.
- Operaciones sobre pacientes y citas.
- Uso de matriz para horarios.
- Cola de sala de espera.
- Pila de historial reciente.
- Arbol binario de busqueda de doctores.
- Busquedas y ordenamientos.
- Reportes por consola.
- Archivos CSV generados en `out`.

## Estado actual

| Punto | Estado |
|---|---|
| Modelos principales | Implementado |
| Servicios de pacientes, citas, pagos y reportes | Implementado |
| Menu por consola | Implementado |
| Arreglos y matrices | Implementado |
| Listas enlazadas | Implementado |
| Cola de pacientes | Implementado |
| Pila de historial de citas | Implementado |
| Arbol binario de doctores | Implementado |
| Busqueda lineal | Implementado |
| Bubble Sort, QuickSort y MergeSort | Implementado |
| Recursividad | Implementado |
| Interfaces y TAD | Implementado |
| Exportacion CSV | Implementado |
| Validacion manual por menu | Implementado |

## Alcance técnico

Este proyecto trabaja principalmente en memoria y usa exportación CSV como evidencia académica. No incluye base de datos, interfaz gráfica, autenticación de usuarios ni lectura completa desde archivos.

## Posibles mejoras futuras

- Agregar pruebas automatizadas si el alcance del curso lo permite.
- Permitir carga de datos desde archivos CSV.
- Fortalecer validaciones de entrada para pacientes, citas y pagos.

## Relacion con Chilete DevPath

Este proyecto forma parte de `academia` porque conserva evidencia de aprendizaje aplicada a Java, POO, algoritmos y estructuras de datos. En la web aparece como proyecto académico terminado.
