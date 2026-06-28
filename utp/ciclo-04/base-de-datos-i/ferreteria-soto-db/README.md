# Ferretería Soto DB

Proyecto académico de **Base de Datos I** orientado a modelar la información principal de una ferretería: usuarios, clientes, productos, inventario, ventas, auditoría y políticas de precios.

## Contexto académico

| Dato | Detalle |
|---|---|
| Institución | UTP |
| Curso | Base de Datos I |
| Ciclo | 04 |
| Tipo | Proyecto académico |
| Motor | SQL Server |
| Lenguaje | T-SQL |

## Objetivo

Construir una base de datos relacional que permita representar operaciones básicas de una ferretería y aplicar conceptos de modelado, integridad, consultas, procedimientos almacenados, triggers e inventario.

## Qué contiene

| Archivo | Propósito |
|---|---|
| `00-create-database.sql` | Crea la base de datos del proyecto. |
| `01-usuarios.sql` | Define usuarios, roles, estados y auditoría. |
| `02-clientes.sql` | Modela clientes persona y empresa. |
| `03-clasificacion-productos.sql` | Organiza familias, grupos, marcas, unidades y líneas. |
| `04-productos.sql` | Gestiona productos e inventario con criterio PEPS/FIFO. |
| `05-ventas-detalle-auditoria.sql` | Registra ventas, detalles y auditoría. |
| `06-politicas-precios.sql` | Define reglas de precios aplicadas al dominio. |
| `codigo-fuente-bd-ferreteria-soto.sql` | Script consolidado del proyecto. |
| `reset-inventario-ventas.sql` | Reinicia datos de inventario y ventas para pruebas. |

## Tecnologías y conceptos

- SQL Server.
- T-SQL.
- Modelo relacional.
- Restricciones de integridad.
- Procedimientos almacenados.
- Triggers.
- Índices.
- Auditoría de datos.
- Inventario con criterio PEPS/FIFO.

## Ejecución sugerida

Abrir los scripts en SQL Server Management Studio o Azure Data Studio y ejecutarlos en orden:

```txt
00 -> 01 -> 02 -> 03 -> 04 -> 05 -> 06
```

También se puede revisar el archivo consolidado:

```txt
codigo-fuente-bd-ferreteria-soto.sql
```

## Estado del proyecto

Proyecto terminado como entrega académica.

| Punto | Estado |
|---|---|
| Creación de base de datos | Implementado |
| Usuarios y auditoría | Implementado |
| Clientes | Implementado |
| Catálogos de productos | Implementado |
| Inventario PEPS/FIFO | Implementado |
| Ventas y detalle | Implementado |
| Políticas de precios | Implementado |
| Script consolidado | Implementado |

## Aprendizajes aplicados

- Separar una base de datos por módulos.
- Modelar entidades y relaciones de un caso de negocio.
- Aplicar restricciones para proteger consistencia.
- Usar triggers para registrar cambios relevantes.
- Crear procedimientos almacenados para operaciones controladas.
- Pensar el inventario como una regla de negocio, no solo como una tabla.

## Relación con Chilete DevPath

Este proyecto forma parte de `academia` porque conserva evidencia de aprendizaje aplicada a bases de datos. En la web aparece como proyecto académico terminado.
