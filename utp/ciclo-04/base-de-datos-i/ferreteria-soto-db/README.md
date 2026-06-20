# UTP Ferreteria Soto DB

Proyecto academico de base de datos para modelar usuarios, clientes, productos, inventario, ventas, auditoria y politicas de precios de una ferreteria.

## Autor

- Chilete DevPath
- Estudiante de Ingenieria de Sistemas e Informatica

## Contexto academico

- Institucion: UTP
- Ciclo: 04
- Curso: Base de Datos I
- Tipo: Proyecto academico

## Aviso de publicacion

Este repositorio se publica como evidencia academica y de aprendizaje dentro de Chilete DevPath. No representa una publicacion oficial de UTP ni una solucion productiva de una empresa real.

Los usuarios, correos, claves y datos usados en los scripts son ficticios o de demostracion. No deben reemplazar controles de seguridad, validaciones oficiales ni procesos reales de una organizacion.

## Por que se hizo

Este proyecto se desarrollo para aplicar modelado relacional, normalizacion, restricciones, procedimientos almacenados, triggers, auditoria y manejo de inventario dentro de un caso de negocio.

El caso trabajado representa una ferreteria con usuarios, clientes, catalogos de productos, ventas, detalle de ventas y politicas de precios.

## Tecnologia usada

- SQL Server
- T-SQL
- Procedimientos almacenados
- Triggers
- Indices
- Restricciones de integridad
- Auditoria de datos

## Que contiene el repositorio

```txt
ferreteria-soto-db/
|-- 00-create-database.sql
|-- 01-usuarios.sql
|-- 02-clientes.sql
|-- 03-clasificacion-productos.sql
|-- 04-productos.sql
|-- 05-ventas-detalle-auditoria.sql
|-- 06-politicas-precios.sql
|-- codigo-fuente-bd-ferreteria-soto.sql
|-- reset-inventario-ventas.sql
|-- .gitignore
`-- README.md
```

## Modulos principales

| Script | Modulo |
|---|---|
| `00-create-database.sql` | Creacion de base de datos |
| `01-usuarios.sql` | Usuarios, roles, estados y auditoria |
| `02-clientes.sql` | Clientes persona y empresa |
| `03-clasificacion-productos.sql` | Familias, grupos, marcas, unidades y lineas |
| `04-productos.sql` | Inventario con PEPS/FIFO |
| `05-ventas-detalle-auditoria.sql` | Ventas, detalle y auditoria |
| `06-politicas-precios.sql` | Politicas de precios |
| `reset-inventario-ventas.sql` | Limpieza de inventario y ventas para desarrollo |

## Ejecucion sugerida

Ejecutar en SQL Server Management Studio o Azure Data Studio, respetando el orden numerico de los scripts:

```txt
00 -> 01 -> 02 -> 03 -> 04 -> 05 -> 06
```

Tambien existe un archivo consolidado:

```txt
codigo-fuente-bd-ferreteria-soto.sql
```

## Advertencia

`reset-inventario-ventas.sql` esta pensado para ambiente de desarrollo. No debe ejecutarse en una base de datos productiva.

Las credenciales incluidas en los scripts son ejemplos para pruebas locales. Deben cambiarse antes de cualquier uso fuera de un entorno academico o de demostracion.

## Estado del proyecto

| Punto | Estado |
|---|---|
| Creacion de base de datos | Implementada |
| Usuarios y auditoria | Implementado |
| Clientes | Implementado |
| Catalogos de productos | Implementado |
| Inventario PEPS/FIFO | Implementado |
| Ventas y detalle | Implementado |
| Politicas de precios | Implementado |
| Diagrama entidad-relacion | Pendiente |
| Evidencias de consultas | Pendiente |

## Aprendizajes aplicados

- Modelado de base de datos relacional.
- Separacion de scripts por modulo.
- Uso de restricciones e indices.
- Uso de triggers para auditoria.
- Uso de procedimientos almacenados.
- Manejo de inventario con criterio PEPS/FIFO.

## Pendientes

- Agregar diagrama entidad-relacion.
- Documentar consultas principales.
- Agregar capturas de ejecucion si el curso lo solicita.
- Confirmar version de SQL Server usada.

## Relacion con Chilete DevPath

Este proyecto forma parte del registro academico de Chilete DevPath:

- [academia](https://github.com/chiletedevpath/academia)
- [aprendizaje](https://github.com/chiletedevpath/aprendizaje)
- [chiletedevpath](https://github.com/chiletedevpath/chiletedevpath)
