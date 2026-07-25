# Gestión Comercial DB

Proyecto académico de **Base de Datos I** orientado a modelar operaciones comerciales: usuarios, clientes, productos, inventario, ventas, auditoría y políticas de precios.

El objetivo es practicar diseño relacional y T-SQL sobre un caso de negocio comprensible. El proyecto usa una identidad académica neutral y no representa una empresa ni un sistema listo para producción.

## Contexto académico

| Campo | Detalle |
|---|---|
| Institución | UTP |
| Curso | Base de Datos I |
| Ciclo | 04 |
| Tipo | Proyecto académico |
| Motor | SQL Server |
| Lenguaje | T-SQL |
| Base de datos | `GestionComercialDB` |

## Objetivo

Construir una base de datos relacional capaz de representar inventario, ventas y control básico de usuarios, aplicando integridad referencial, procedimientos almacenados, triggers, auditoría e inventario con criterio PEPS/FIFO.

## Contenido principal

| Archivo | Propósito |
|---|---|
| `00-create-database.sql` | Crea `GestionComercialDB`. |
| `01-usuarios.sql` | Define usuarios, roles, estados y auditoría. |
| `02-clientes.sql` | Modela clientes persona y empresa. |
| `03-clasificacion-productos.sql` | Organiza familias, grupos, marcas, unidades y líneas. |
| `04-productos.sql` | Crea productos y registra entradas de inventario por lotes. |
| `05-ventas-detalle-auditoria.sql` | Registra ventas, detalles, auditoría y salidas de inventario PEPS/FIFO. |
| `06-politicas-precios.sql` | Define reglas de precios aplicadas al dominio. |
| `07-consultas-validacion.sql` | Reúne consultas para validar módulos, relaciones y evidencia técnica. |
| `codigo-fuente-gestion-comercial-db.sql` | Consolida la implementación del proyecto. |
| `reset-inventario-ventas.sql` | Reinicia datos de inventario y ventas para pruebas. |

## Conceptos aplicados

- Modelo relacional.
- Normalización básica.
- Claves primarias y foráneas.
- Restricciones de integridad.
- Procedimientos almacenados.
- Triggers de auditoría.
- Inventario por lotes.
- Salidas PEPS/FIFO.
- Políticas de precios.
- Consultas de validación.

Para repasar la terminología utilizada, consultar el [glosario técnico](GLOSARIO.md).

## Ejecución sugerida

Abrir los scripts en SQL Server Management Studio o Azure Data Studio y ejecutarlos en orden:

```text
00 -> 01 -> 02 -> 03 -> 04 -> 05 -> 06 -> 07
```

También se puede revisar o ejecutar el script consolidado:

```text
codigo-fuente-gestion-comercial-db.sql
```

## Validación académica

El archivo `07-consultas-validacion.sql` permite revisar relaciones, datos cargados, ventas, auditoría y comportamiento del inventario.

## Estado

Proyecto terminado como entrega académica.

| Punto | Estado |
|---|---|
| Creación de base de datos | Implementado |
| Usuarios y auditoría | Implementado |
| Clientes | Implementado |
| Catálogos de productos | Implementado |
| Productos | Implementado |
| Entradas de inventario | Implementado |
| Ventas y detalle | Implementado |
| Salidas de inventario PEPS/FIFO | Implementado |
| Políticas de precios | Implementado |
| Consultas de validación | Implementado |
| Script consolidado | Implementado |

## Fuera de alcance

El proyecto no incluye aplicación cliente, autenticación empresarial, despliegue productivo ni integración con sistemas externos. Los datos incluidos tienen únicamente propósito académico y demostrativo.

## Mejoras futuras

- Agregar una guía completa de restauración desde cero.
- Incorporar consultas analíticas de ventas e inventario.
- Documentar los resultados esperados de cada script de validación.
- Agregar pruebas automatizadas de procedimientos y reglas críticas.

## Relación con Chilete DevPath

Este proyecto forma parte de `academia` porque conserva evidencia de aprendizaje en modelado relacional, T-SQL, inventario, ventas y auditoría. Se presenta como evidencia formativa terminada, no como producto comercial ni como proyecto profesional final.

