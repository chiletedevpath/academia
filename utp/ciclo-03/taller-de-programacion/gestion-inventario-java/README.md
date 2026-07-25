# Gestión de Inventario Java

Proyecto académico en Java para el curso de **Taller de Programación** de la UTP. La aplicación administra por consola un inventario pequeño de productos y permite demostrar fundamentos de programación, arreglos y Programación Orientada a Objetos.

El proyecto usa una identidad académica neutral. No representa una empresa, un inventario real ni un sistema listo para producción.

## Contexto académico

| Campo | Detalle |
|---|---|
| Institución | UTP |
| Curso | Taller de Programación |
| Ciclo | 03 |
| Tipo | Proyecto final académico revisado |
| Lenguaje | Java |
| Interfaz | Consola |
| Persistencia | Memoria durante la ejecución |

## Objetivo

Aplicar los contenidos principales del curso mediante un inventario capaz de registrar, consultar, modificar, eliminar y vender productos, con validaciones de entrada y reportes básicos.

## Alcance funcional

| Funcionalidad | Descripción |
|---|---|
| Inventario | Muestra los productos registrados y sus datos principales. |
| Registro | Agrega productos con código, nombre, categoría, precio y stock. |
| Búsqueda | Localiza un producto por su código. |
| Modificación | Actualiza nombre, categoría, precio o stock. |
| Eliminación | Retira un producto y reorganiza el arreglo. |
| Venta | Valida disponibilidad, descuenta unidades y calcula el total. |
| Resumen | Agrupa productos y unidades disponibles por categoría. |

## Conceptos aplicados

- Variables, operadores y estructuras condicionales.
- Ciclos `for`, `while` y `do-while`.
- Arreglos unidimensionales.
- Arreglos bidimensionales.
- Métodos que reciben arreglos como parámetros.
- Clases, objetos, encapsulamiento y constructores.
- Sobrescritura de `toString()`.
- Procesamiento de cadenas con `String` y `StringBuilder`.
- Manejo de excepciones con `try`, `catch` y `finally`.
- Excepción personalizada para reglas de validación.

## Arquitectura

```text
gestion-inventario-java/
|-- src/pe/edu/utp/inventario/
|   |-- app/            # Punto de entrada y menú
|   |-- excepciones/    # Excepción de validación
|   |-- modelo/         # Entidad Producto
|   |-- servicio/       # Operaciones del inventario
|   `-- util/           # Lectura segura desde consola
|-- .gitignore
`-- README.md
```

## Componentes principales

| Componente | Responsabilidad |
|---|---|
| `Main` | Presenta el menú y coordina las operaciones del usuario. |
| `Producto` | Encapsula datos, validaciones y operación de venta. |
| `Inventario` | Administra el arreglo, el CRUD y los reportes. |
| `Consola` | Lee y valida texto, enteros, decimales y opciones. |
| `DatoInvalidoException` | Comunica datos inválidos y reglas incumplidas. |

## Ejecución local

Desde la raíz del proyecto:

```powershell
$fuentes = Get-ChildItem -Path src -Recurse -Filter *.java |
    Select-Object -ExpandProperty FullName

javac -encoding UTF-8 -d out $fuentes
java -cp out pe.edu.utp.inventario.app.Main
```

Requiere JDK 17 o una versión posterior.

## Validación académica

El código fue compilado y se verificaron los siguientes recorridos por consola:

- Listado del inventario.
- Registro y búsqueda de productos.
- Modificación y eliminación.
- Venta con actualización de stock.
- Resumen mediante arreglo bidimensional.
- Control de datos numéricos inválidos.

## Estado

Proyecto académico revisado y funcional.

| Punto | Estado |
|---|---|
| Menú por consola | Implementado |
| CRUD de productos | Implementado |
| Venta y control de stock | Implementado |
| Arreglos unidimensionales | Implementado |
| Arreglo bidimensional para resumen | Implementado |
| Validaciones y excepciones | Implementado |
| Compilación local | Verificada |

## Fuera de alcance

El inventario tiene una capacidad fija de 20 productos y trabaja únicamente en memoria. No incluye base de datos, archivos, interfaz gráfica, autenticación, facturación ni integración con servicios externos.

## Mejoras futuras

- Incorporar persistencia mediante archivos cuando corresponda al nivel del curso.
- Agregar pruebas automatizadas con un framework.
- Migrar a colecciones cuando se estudie `ArrayList`.
- Separar la interfaz de consola de la lógica de aplicación.

## Relación con Chilete DevPath

Este proyecto forma parte de `academia` porque conserva evidencia de aprendizaje en fundamentos de Java, arreglos, validaciones y Programación Orientada a Objetos. Se presenta como una entrega formativa revisada, no como producto comercial ni como proyecto profesional final.
