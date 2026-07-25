# Glosario técnico | Gestión Clínica con Estructuras de Datos

Este glosario explica los conceptos usados en **Gestión Clínica con Estructuras de Datos** con lenguaje claro y relación directa con el código.

No busca repetir teoría de memoria. Su objetivo es ayudar a responder tres preguntas durante la revisión o sustentación: qué significa el concepto, dónde aparece y qué problema ayuda a resolver.

## Cómo usar este glosario

- Para estudiar: lee la explicación y luego ubica la clase relacionada.
- Para sustentar: usa la aplicación como puente entre teoría y código.
- Para revisar el proyecto: confirma que cada concepto tenga una implementación visible.
- Para practicar: intenta explicar cada término con tus propias palabras antes de leer la aplicación.

## Programación Orientada a Objetos

| Concepto | Explicación | Aplicación en el proyecto |
|---|---|---|
| Clase | Molde que define datos y comportamientos de una entidad. | `Paciente`, `Doctor`, `Cita`, `Pago`, `Tratamiento`. |
| Objeto | Elemento concreto creado a partir de una clase. | Cada paciente, cita, doctor o pago usado durante la ejecución. |
| Encapsulamiento | Protege los datos internos y controla el acceso mediante métodos. | Modelos con atributos privados y métodos de acceso. |
| Herencia | Reutiliza atributos y comportamientos comunes desde una clase base. | `Paciente`, `Doctor` y `Administrador` heredan de `Persona`. |
| Abstracción | Representa lo esencial de una entidad sin cargar detalles innecesarios. | `Persona` concentra datos comunes de personas del sistema. |
| Polimorfismo | Permite manejar objetos relacionados mediante comportamientos compatibles. | `Boleta` y `Factura` especializan el comportamiento de `Pago`. |
| Interfaz | Contrato que define operaciones que una clase debe cumplir. | `Registrable`, `Pagable`, `Reportable` y los TAD del proyecto. |

## Estructuras de datos

| Concepto | Explicación | Aplicación en el proyecto |
|---|---|---|
| Arreglo | Estructura de tamaño fijo que guarda elementos por posición. | Base de pacientes, citas y pagos en los servicios principales. |
| Matriz | Arreglo bidimensional organizado en filas y columnas. | `MatrizHorarios` relaciona doctores con días de atención. |
| Nodo | Unidad que guarda un dato y una referencia hacia otro elemento. | `NodoPaciente`, `NodoCita` y `NodoDoctor`. |
| Lista enlazada | Estructura dinámica formada por nodos conectados. | `ListaPacientes` y `ListaCitas`. |
| Cola | Estructura FIFO: atiende primero al dato que ingresó primero. | `ColaPacientes` simula la sala de espera. |
| Pila | Estructura LIFO: atiende primero al dato que ingresó último. | `PilaHistorialCitas` gestiona citas recientes. |
| Árbol binario de búsqueda | Estructura no lineal que organiza datos para insertar, buscar y recorrer con criterio. | `ArbolDoctoresBusqueda` organiza doctores. |
| TAD | Tipo Abstracto de Datos que define operaciones sin depender de una implementación única. | Interfaces `TADListaPacientes`, `TADListaCitas`, `TADColaPacientes`, `TADPilaHistorialCitas` y `TADArbolDoctores`. |

## Algoritmos

| Concepto | Explicación | Aplicación en el proyecto |
|---|---|---|
| Búsqueda lineal | Recorre elementos uno por uno hasta encontrar una coincidencia. | Búsqueda de pacientes y doctores. |
| Recursividad | Resuelve una tarea dividiéndola en llamadas más pequeñas del mismo método. | `ReporteRecursivo` recorre y cuenta elementos. |
| Bubble Sort | Ordena comparando elementos vecinos e intercambiándolos si corresponde. | `BubbleSortPacientes` ordena pacientes. |
| QuickSort | Ordena usando un pivote y dividiendo el problema en partes menores. | `QuickSort` ordena datos usando `Comparator`. |
| MergeSort | Ordena dividiendo, resolviendo y fusionando subconjuntos. | `MergeSortPagos` ordena pagos por monto. |
| Comparator | Interfaz de Java que define cómo comparar dos objetos. | Permite ordenar objetos por atributos específicos. |

## Preguntas para sustentación

- ¿Por qué una cola sirve para representar una sala de espera?
- ¿Qué diferencia práctica hay entre lista enlazada y arreglo?
- ¿Por qué un árbol binario de búsqueda ayuda a organizar doctores?
- ¿Qué algoritmo de ordenamiento sería más fácil de explicar y cuál sería más eficiente?
- ¿Qué ventaja tiene separar una interfaz TAD de su implementación?
