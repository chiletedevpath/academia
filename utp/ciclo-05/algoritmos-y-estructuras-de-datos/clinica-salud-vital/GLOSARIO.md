# Glosario Tecnico | Chilete DevPath

Este glosario forma parte del enfoque de **Chilete DevPath**: documentar el aprendizaje con ejemplos reales, lenguaje claro y relacion directa con el codigo.

El objetivo no es repetir teoria de memoria, sino explicar los conceptos que se aplican en **Clinica Salud Vital** para que cualquier estudiante pueda entender que problema resuelve cada estructura, donde aparece en el proyecto y como defenderla tecnicamente.

## Como usar este glosario

- Si estas aprendiendo, revisa primero la explicacion y luego ubica la clase relacionada.
- Si estas sustentando, usa la columna de aplicacion para conectar teoria con codigo.
- Si estas revisando el proyecto, compara cada concepto con el modulo donde se implementa.
- Si quieres practicar, intenta explicar cada concepto con tus propias palabras antes de leer la aplicacion.

## Conceptos de Programacion Orientada a Objetos

| Concepto | Explicacion | Aplicacion en el proyecto |
|---|---|---|
| Clase | Molde que define datos y comportamientos de una entidad. | `Paciente`, `Doctor`, `Cita`, `Pago`, `Tratamiento`. |
| Objeto | Elemento concreto creado a partir de una clase. | Cada paciente, cita, doctor o pago cargado durante la ejecucion. |
| Encapsulamiento | Protege los datos internos y controla el acceso mediante metodos. | Modelos con atributos privados y metodos de acceso. |
| Herencia | Reutiliza atributos y comportamientos comunes desde una clase base. | `Paciente`, `Doctor` y `Administrador` heredan de `Persona`. |
| Abstraccion | Representa lo esencial de una entidad sin cargar detalles innecesarios. | `Persona` concentra datos comunes de las personas del sistema. |
| Polimorfismo | Permite manejar objetos relacionados mediante comportamientos compatibles. | `Boleta` y `Factura` especializan el comportamiento de `Pago`. |
| Interfaz | Contrato que define operaciones que una clase debe cumplir. | `Registrable`, `Pagable`, `Reportable` y los TAD del proyecto. |

## Estructuras de datos

| Concepto | Explicacion | Aplicacion en el proyecto |
|---|---|---|
| Arreglo | Estructura de tamano fijo que guarda elementos por posicion. | Base de pacientes, citas y pagos en los servicios principales. |
| Matriz | Arreglo bidimensional organizado en filas y columnas. | `MatrizHorarios` relaciona doctores con dias de atencion. |
| Nodo | Unidad que guarda un dato y una referencia hacia otro elemento. | `NodoPaciente`, `NodoCita` y `NodoDoctor`. |
| Lista enlazada | Estructura dinamica formada por nodos conectados. | `ListaPacientes` y `ListaCitas`. |
| Cola | Estructura FIFO: atiende primero al dato que ingreso primero. | `ColaPacientes` simula la sala de espera. |
| Pila | Estructura LIFO: atiende primero al dato que ingreso ultimo. | `PilaHistorialCitas` gestiona citas recientes. |
| Arbol binario de busqueda | Estructura no lineal que organiza datos para insertar, buscar y recorrer con criterio. | `ArbolDoctoresBusqueda` organiza doctores. |
| TAD | Tipo Abstracto de Datos que define operaciones sin depender de una implementacion unica. | Interfaces `TADListaPacientes`, `TADListaCitas`, `TADColaPacientes`, `TADPilaHistorialCitas` y `TADArbolDoctores`. |

## Algoritmos

| Concepto | Explicacion | Aplicacion en el proyecto |
|---|---|---|
| Busqueda lineal | Recorre elementos uno por uno hasta encontrar una coincidencia. | Busqueda de pacientes y doctores. |
| Recursividad | Resuelve una tarea dividiendola en llamadas mas pequenas del mismo metodo. | `ReporteRecursivo` recorre y cuenta elementos. |
| Bubble Sort | Ordena comparando elementos vecinos e intercambiandolos si corresponde. | `BubbleSortPacientes` ordena pacientes. |
| QuickSort | Ordena usando un pivote y dividiendo el problema en partes menores. | `QuickSort` ordena datos usando `Comparator`. |
| MergeSort | Ordena dividiendo, resolviendo y fusionando subconjuntos. | `MergeSortPagos` ordena pagos por monto. |
| Comparator | Interfaz de Java que define como comparar dos objetos. | Permite ordenar objetos por atributos especificos. |

## Conceptos del dominio clinico

| Concepto | Explicacion | Aplicacion en el proyecto |
|---|---|---|
| Paciente | Persona que recibe atencion medica. | Entidad principal para registros, busquedas y reportes. |
| Doctor | Profesional medico asociado a especialidades y atenciones. | Se organiza mediante el arbol binario de busqueda. |
| Cita | Registro de una atencion programada. | Relaciona paciente, doctor, fecha, hora y estado. |
| Horario | Distribucion de atenciones por doctor y dia. | Se representa mediante una matriz. |
| Sala de espera | Orden de atencion de pacientes antes de una consulta. | Se simula con una cola FIFO. |
| Historial reciente | Registro temporal de citas o atenciones consultadas recientemente. | Se simula con una pila LIFO. |
| Tratamiento | Indicacion medica asociada a una atencion. | Modelado dentro del dominio clinico. |
| Receta | Documento simple con indicaciones o medicamentos. | Relacionada con el tratamiento del paciente. |
| Pago | Registro economico asociado a una atencion. | Base para boletas y facturas. |

## Persistencia y ejecucion

| Concepto | Explicacion | Aplicacion en el proyecto |
|---|---|---|
| Persistencia | Conservacion de datos fuera de la ejecucion del programa. | Exportacion simple a archivos CSV. |
| CSV | Archivo de texto con valores separados por comas. | Salida generada para pacientes, citas y pagos. |
| Consola | Interfaz textual para interactuar con el programa. | Menu principal de sustentacion. |
| Datos en memoria | Informacion que existe mientras el programa esta en ejecucion. | Datos base cargados al iniciar el sistema. |
| Fuera de alcance | Elementos que el proyecto no implementa por decision academica. | Base de datos, interfaz grafica, autenticacion y lectura completa desde archivos. |

## Para seguir practicando

Estas preguntas sirven para repasar el proyecto desde una mirada Chilete DevPath:

- Que estructura conviene usar si necesito atender pacientes por orden de llegada?
- Por que una pila funciona para revisar el historial reciente?
- Que diferencia practica hay entre un arreglo, una lista enlazada y un arbol?
- Que ventaja tiene separar una interfaz TAD de su implementacion concreta?
- Que algoritmo de ordenamiento seria mas facil de explicar en una sustentacion y cual seria mas eficiente?
- Que parte del sistema demuestra mejor la relacion entre problema real y estructura de datos?
