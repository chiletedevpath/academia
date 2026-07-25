# Glosario - Gestión de Ventas con Patrones de Diseño

Este glosario resume los conceptos técnicos usados en el proyecto académico **Gestión de Ventas con Patrones de Diseño**. Su objetivo es servir como apoyo para la lectura del código y la sustentación del trabajo.

## Conceptos de dominio

| Término | Definición | Aplicación en el proyecto |
|---|---|---|
| Gestión de ventas | Sistema académico de consola para modelar ventas e inventario. | Se ejecuta desde `Main` y coordina clientes, usuarios, productos, ventas, stock y comprobantes. |
| Cliente | Persona que realiza una compra. | Se modela con `Cliente`, heredando datos comúnes desde `Persona`. |
| Usuario | Actor interno que registra o gestiona la venta. | Se modela con `Usuario` y se clasifica con `RolUsuario`. |
| Producto | Artículo incluido en el catálogo comercial. | `Producto` concentra nombre, categoría, precio, stock y stock mínimo. |
| Categoria | Clasificacion funcional del producto. | `Categoria` permite diferenciar herramientas, productos eléctricos, construcción y pintura. |
| Stock | Cantidad disponible de un producto. | Se descuenta al agregar productos a una venta y permite disparar alertas de stock mínimo. |
| Stock mínimo | Umbral desde el cual el sistema debe advertir baja disponibilidad. | `AlertaStockObserver` notifica cuando un producto llega al limite configurado. |
| Venta | Operación comercial que agrupa cliente, usuario y productos vendidos. | `Venta` calcula subtotal, IGV, total y administra cambios de estado. |
| Detalle de venta | Linea que representa un producto y cantidad dentro de una venta. | `DetalleVenta` calcula el subtotal por producto vendido. |
| Comprobante | Documento generado como resultado de una venta. | `ComprobanteVenta` se construye mediante `ComprobanteVentaBuilder`. |
| Facturacion externa | Servicio externo simulado para exportar una venta. | `ServicioFacturacionExterna` no factura realmente; sirve para demostrar Adapter. |

## Conceptos de diseño orientado a objetos

| Término | Definición | Aplicación en el proyecto |
|---|---|---|
| Clase | Molde que define atributos y comportamientos de un objeto. | Aparece en entidades como `Producto`, `Venta`, `Cliente` y `Usuario`. |
| Objeto | Instancia concreta de una clase en ejecución. | Los productos, clientes y ventas creados en `Main` son objetos. |
| Encapsulamiento | Proteccion de datos internos mediante métodos controlados. | `Producto` controla el cambio de stock y `Venta` controla operaciónes de venta. |
| Herencia | Mecanismo para reutilizar atributos y comportamientos comúnes. | `Cliente` y `Usuario` heredan de `Persona`. |
| Polimorfismo | Capacidad de usar una interfaz común con implementaciones distintas. | Se aplica en `EstadoVenta`, `ComandoVenta`, `StockObserver` y `ExportadorVenta`. |
| Abstracción | Representar lo esencial de un concepto sin exponer detalles innecesarios. | Las interfaces y clases abstractas separan contratos de implementaciones concretas. |
| Bajo acoplamiento | Diseño donde las clases dependen lo menos posible unas de otras. | La venta delega creación, estados, comandos, exportación y alertas a componentes separados. |
| Alta cohesion | Diseño donde cada clase tiene una responsabilidad clara. | `Venta` gestiona la venta, `ProductoFactory` crea productos y `ComprobanteVentaBuilder` arma comprobantes. |

## Principios GRASP

| Término | Definición | Aplicación en el proyecto |
|---|---|---|
| Experto en información | La responsabilidad debe asignarse a la clase que posee la información necesaria. | `Producto` maneja stock, `DetalleVenta` calcula subtotal y `Venta` calcula totales. |
| Controlador | Clase que recibe o coordina un caso de uso. | `VentaController` coordina el flujo de venta sin cargar esa responsabilidad en `Main`. |
| Bajo acoplamiento | Principio que reduce dependencias innecesarias entre clases. | Los patrones separan creación, comandos, estados y exportación. |
| Alta cohesion | Principio que mantiene clases enfocadas en una tarea principal. | Cada paquete agrupa responsabilidades específicas del flujo. |

## Patrones de diseño aplicados

| Patron | Definición | Aplicación en el proyecto |
|---|---|---|
| Singleton | Garantiza una única instancia de una clase. | `ConfiguracionSistema` centraliza valores de configuración del sistema. |
| Factory Method | Delega la creación de objetos a factories concretas. | `ProductoFactory` y sus factories crean productos por categoría. |
| Builder | Construye un objeto complejo paso a paso. | `ComprobanteVentaBuilder` arma el comprobante final de una venta. |
| Command | Encapsula una acción como objeto ejecutable. | `ConfirmarVentaCommand` y `AnularVentaCommand` encapsulan acciónes sobre la venta. |
| Adapter | Convierte una interfaz o formato en otro esperado por un servicio. | `FacturacionExternaAdapter` adapta una venta al servicio externo simulado. |
| Facade | Ofrece una entrada simple a un conjunto de operaciónes internas. | `VentaFacade` simplifica registro, comprobante y exportación de ventas. |
| Observer | Permite notificar cambios a objetos interesados. | `AlertaStockObserver` recibe alertas cuando el stock llega al mínimo. |
| State | Permite cambiar el comportamiento según el estado interno de un objeto. | `EstadoVentaCreada`, `EstadoVentaConfirmada` y `EstadoVentaAnulada` definen reglas de la venta. |

## Términos de sustentación

| Término | Definición | Aplicación en el proyecto |
|---|---|---|
| Consola | Interfaz textual para ejecutar el sistema. | El proyecto prioriza arquitectura y patrones, por eso no incluye interfaz gráfica. |
| Simulacion | Representacion controlada de un comportamiento sin integracion real. | La facturacion externa es simulada para explicar Adapter. |
| Flujo de venta | Secuencia desde creación de actores y productos hasta comprobante y exportación. | Se demuestra en `Main` mediante una venta completa. |
| Fuera de alcance | Elementos que no forman parte de la entrega. | No incluye base de datos, autenticación, interfaz gráfica ni facturacion real. |


