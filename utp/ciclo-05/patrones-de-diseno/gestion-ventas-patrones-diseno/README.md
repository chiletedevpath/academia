# Gestión de Ventas con Patrones de Diseño

Proyecto académico en Java para el curso de **Diseño de Patrones** de la UTP. El sistema modela un flujo comercial de venta e inventario aplicando POO, GRASP y patrones GOF sobre una aplicación de consola.

El valor del proyecto está en la separación de responsabilidades: creación de productos, registro de venta, control de stock, estados, comandos, comprobante y exportación se distribuyen en clases con responsabilidades claras.

## Contexto académico

| Campo | Detalle |
|---|---|
| Institución | UTP |
| Curso | Diseño de Patrones |
| Ciclo | 05 |
| Proyecto | Gestión de Ventas con Patrones de Diseño |
| Dominio | Gestión académica de ventas e inventario |
| Lenguaje | Java |
| Interfaz | Consola |

## Alcance funcional

El proyecto permite crear clientes, usuarios y productos, registrar una venta, validar stock disponible, calcular subtotal, IGV y total, confirmar o anular una venta, construir un comprobante y simular exportación a facturación externa.

La aplicación se ejecuta por consola porque el objetivo principal es demostrar diseño orientado a objetos y patrones, no construir una interfaz gráfica.

## Componentes principales

| Componente | Responsabilidad |
|---|---|
| `Persona`, `Cliente`, `Usuario` | Modelan actores del flujo de venta. |
| `Producto` | Controla precio, stock y stock mínimo. |
| `Venta` y `DetalleVenta` | Agrupan productos, calculan importes y aplican reglas de stock. |
| `ConfiguracionSistema` | Centraliza configuración única del sistema. |
| `VentaController` | Coordina el caso de uso desde la capa de aplicación. |
| `ProductoFactory` y factories concretas | Crean productos por categoría mediante Factory Method. |
| `VentaFacade` | Simplifica registro, comandos, comprobante y exportación de ventas. |
| `ConfirmarVentaCommand`, `AnularVentaCommand` | Encapsulan acciones ejecutables sobre la venta. |
| `ComprobanteVentaBuilder` | Construye el comprobante por partes. |
| `FacturacionExternaAdapter` | Adapta una venta al formato de un servicio externo simulado. |
| `AlertaStockObserver` | Notifica cuando un producto llega al stock mínimo. |
| Estados de venta | Controlan qué operaciones se permiten según el estado actual. |

## Patrones y principios aplicados

| Concepto | Aplicación en el proyecto |
|---|---|
| GRASP Experto | `Producto` controla stock, `Venta` calcula totales y `DetalleVenta` calcula subtotal. |
| GRASP Controlador | `VentaController` coordina el flujo sin cargar todo en `Main`. |
| Bajo acoplamiento | La aplicación delega creación, registro, estados y exportación a clases específicas. |
| Singleton | `ConfiguracionSistema` mantiene una única instancia de configuración. |
| Factory Method | Factories concretas crean productos por categoría. |
| Builder | `ComprobanteVentaBuilder` arma el comprobante final paso a paso. |
| Command | Confirmación y anulación quedan encapsuladas como comandos. |
| Adapter | La facturación externa simulada recibe un formato adaptado. |
| Facade | `VentaFacade` ofrece una entrada simple para operaciones de venta. |
| Observer | La venta notifica cambios relevantes de stock. |
| State | La venta delega reglas de confirmación, anulación y exportación al estado actual. |

## Flujo demostrado

1. `ConfiguracionSistema` entrega la configuración inicial.
2. Se crean cliente y usuario.
3. Las factories crean productos de ejemplo por categoría.
4. Se registra una venta.
5. Se agregan productos y se descuenta stock.
6. Se notifican alertas cuando corresponde.
7. Se confirma la venta mediante un comando.
8. Se calculan subtotal, IGV y total.
9. Se construye el comprobante con Builder.
10. Se simula exportación a un servicio externo mediante Adapter.

## Ejecución local

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java).FullName
java -cp out pe.edu.utp.gestionventas.app.Main
```

## Estado

Proyecto terminado como entrega académica.

## Fuera de alcance

Este proyecto no incluye base de datos, interfaz gráfica, autenticación ni facturación real. La facturación externa es una simulación para demostrar el patrón Adapter.

## Mejoras futuras

- Agregar pruebas automatizadas por patrón aplicado.
- Separar una futura versión con persistencia sin modificar la lógica base.
- Preparar una interfaz gráfica solo si aporta a una nueva etapa del proyecto.
- Mantener README e informe alineados con el código realmente sustentado.

## Relación con Chilete DevPath

Este proyecto forma parte de `academia` porque conserva evidencia de aprendizaje aplicada a arquitectura Java, GRASP y patrones de diseño. Dentro de Chilete DevPath se presenta como evidencia formativa revisada, no como producto comercial ni como proyecto de portafolio profesional final.
