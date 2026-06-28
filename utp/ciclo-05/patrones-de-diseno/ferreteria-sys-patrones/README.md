# FerreSys - Sistema de gestión para ferretería

Proyecto académico en Java para el curso de Diseño de Patrones de la UTP. El sistema modela una venta de ferretería aplicando POO, bajo acoplamiento, GRASP y patrones GOF sobre un dominio simple pero defendible.

## Contexto académico

| Campo | Detalle |
|---|---|
| Institución | UTP |
| Curso | Diseño de Patrones |
| Ciclo | 05 |
| Proyecto | FerreSys |
| Dominio | Gestión de ventas e inventario para ferretería |

## Alcance actual

El proyecto permite crear clientes, usuarios y productos, registrar una venta, validar stock disponible, calcular subtotal, IGV y total, y emitir una alerta cuando un producto llega al stock minimo.

La aplicación se ejecuta por consola porque el objetivo principal es demostrar diseño orientado a objetos y patrones, no construir una interfaz gráfica.

## Estructura

```txt
src/
`-- pe/edu/utp/fersys/
    |-- app/
    |   `-- Main.java
    |-- builder/
    |   |-- ComprobanteVenta.java
    |   `-- ComprobanteVentaBuilder.java
    |-- command/
    |   |-- AnularVentaCommand.java
    |   |-- ComandoVenta.java
    |   `-- ConfirmarVentaCommand.java
    |-- config/
    |   `-- ConfiguracionSistema.java
    |-- controller/
    |   `-- VentaController.java
    |-- factory/
    |   `-- ProductoFactory.java
    |-- facade/
    |   `-- VentaFacade.java
    |-- adapter/
    |   |-- ExportadorVenta.java
    |   |-- FacturacionExternaAdapter.java
    |   `-- ServicioFacturacionExterna.java
    |-- observer/
    |   |-- AlertaStockObserver.java
    |   `-- StockObserver.java
    |-- state/
    |   |-- EstadoVenta.java
    |   |-- EstadoVentaAnulada.java
    |   |-- EstadoVentaConfirmada.java
    |   `-- EstadoVentaCreada.java
    `-- modelo/
        |-- cliente/
        |-- persona/
        |-- producto/
        |-- usuario/
        `-- venta/
```

## Componentes principales

| Componente | Responsabilidad |
|---|---|
| `Persona` | Abstrae datos comunes de clientes y usuarios |
| `Cliente` | Representa al comprador registrado |
| `Usuario` | Representa al trabajador que opera el sistema |
| `Producto` | Controla precio, stock y stock mínimo |
| `Venta` | Agrupa detalles, descuenta stock y calcula importes |
| `DetalleVenta` | Calcula el subtotal de una linea de venta |
| `ComprobanteVenta` | Representa el comprobante final construido |
| `ComprobanteVentaBuilder` | Construye el comprobante por partes |
| `ComandoVenta` | Define acciones ejecutables sobre una venta |
| `ConfirmarVentaCommand` | Encapsula la confirmación de una venta |
| `AnularVentaCommand` | Encapsula la anulación de una venta |
| `ConfiguracionSistema` | Centraliza configuración única del sistema |
| `VentaController` | Coordina el caso de uso de venta como GRASP Controlador |
| `ProductoFactory` | Centraliza la creación de productos de ejemplo |
| `VentaFacade` | Simplifica el registro y resumen de ventas |
| `FacturacionExternaAdapter` | Adapta una venta al formato de un servicio externo simulado |
| `StockObserver` | Define el contrato de notificación por stock |
| `AlertaStockObserver` | Muestra alerta cuando un producto llega al stock mínimo |
| `EstadoVenta` | Define las operaciones dependientes del estado de una venta |
| `EstadoVentaCreada` | Permite confirmar o anular una venta registrada |
| `EstadoVentaConfirmada` | Controla reglas de una venta ya confirmada |
| `EstadoVentaAnulada` | Bloquea operaciones invalidas sobre una venta anulada |

## Conceptos aplicados

| Concepto | Aplicacion en el proyecto |
|---|---|
| POO | Clases, encapsulamiento, herencia, abstracción y polimorfismo |
| GRASP Experto | `Producto` controla stock, `Venta` calcula totales y `DetalleVenta` calcula su subtotal |
| Bajo acoplamiento | `Main` delega creacion, registro y alertas a clases especializadas |
| Singleton | `ConfiguracionSistema` mantiene una única instancia de configuración |
| Factory | `ProductoFactory` crea productos sin exponer datos de construcción en `Main` |
| Builder | `ComprobanteVentaBuilder` arma un comprobante por encabezado, detalle, totales y mensaje |
| Command | `ConfirmarVentaCommand` y `AnularVentaCommand` encapsulan acciones de venta |
| Adapter | `FacturacionExternaAdapter` traduce una `Venta` al servicio externo simulado |
| Facade | `VentaFacade` ofrece una entrada simple para registrar productos y mostrar resumen |
| Observer | `Venta` notifica cambios de stock a observadores como `AlertaStockObserver` |
| State | `Venta` delega confirmación y anulación al estado actual |
| GRASP Controlador | `VentaController` coordina el flujo de venta y reduce responsabilidades del `Main` |

## Flujo demostrado

1. `ConfiguracionSistema` entrega la configuración única del sistema.
2. Se crean cliente y usuario.
3. `ProductoFactory` crea productos de ejemplo.
4. Se crea una `Venta`.
5. Se registra `AlertaStockObserver` como observador de stock.
6. `VentaController` coordina el caso de uso de venta.
7. `VentaFacade` registra productos en la venta.
8. `Venta` descuenta stock y notifica observadores.
9. `ConfirmarVentaCommand` confirma la venta.
10. `Venta` cambia de `CREADA` a `CONFIRMADA`.
11. Se muestra el resumen con subtotal, IGV y total.
12. `ComprobanteVentaBuilder` construye un comprobante con datos reales.
13. `FacturacionExternaAdapter` simula el envío a un servicio externo.

## Ejecución local

Desde la raiz del repositorio:

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java).FullName
java -cp out pe.edu.utp.fersys.app.Main
```

## Estado

Avance del proyecto: **100% como entrega académica**.

| Item | Estado |
|---|---|
| Modelo base POO | Implementado |
| Modelo de productos y ventas | Implementado |
| Validacion de stock | Implementado |
| Calculo de subtotal, IGV y total | Implementado |
| Singleton | Implementado |
| Factory | Implementado |
| Builder | Implementado |
| Command | Implementado |
| Adapter | Implementado |
| Facade | Implementado |
| Observer | Implementado |
| State | Implementado |
| GRASP Controlador | Implementado |
| Comentarios tecnicos de apoyo | Implementado |

## Cierre académico

El proyecto queda cerrado como evidencia académica de patrones de diseño. Cualquier mejora futura debe tratarse como una nueva versión de aprendizaje o una refactorización posterior, sin cambiar la lógica entregada.

## Relación con Chilete DevPath

Este proyecto forma parte de `academia` porque conserva evidencia de aprendizaje aplicada a arquitectura, POO, GRASP y patrones de diseño. En la web aparece como proyecto académico terminado.
