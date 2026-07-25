# Glosario - Gestión Comercial DB

Términos principales utilizados en el proyecto académico de Base de Datos I.

| Término | Definición | Aplicación en el proyecto |
|---|---|---|
| Base de datos relacional | Organización de información mediante tablas relacionadas. | `GestionComercialDB` conecta usuarios, clientes, productos, inventario y ventas. |
| Tabla | Estructura que almacena registros de una entidad. | El proyecto utiliza tablas para productos, clientes, ventas y otros módulos. |
| Clave primaria | Campo que identifica de forma única cada registro. | Evita registros duplicados dentro de una tabla. |
| Clave foránea | Campo que referencia la clave primaria de otra tabla. | Relaciona ventas con clientes y detalles con productos. |
| Restricción | Regla aplicada por la base de datos para proteger los datos. | Valida estados, valores obligatorios y relaciones. |
| Normalización | Organización de datos para reducir duplicación y dependencias incorrectas. | Separa catálogos, clientes, productos y operaciones comerciales. |
| Procedimiento almacenado | Bloque T-SQL reutilizable que ejecuta una operación controlada. | Registra operaciones y aplica reglas del dominio. |
| Trigger | Código ejecutado automáticamente ante determinados cambios. | Conserva evidencia de auditoría y controla operaciones relacionadas. |
| Auditoría | Registro de cambios u operaciones relevantes. | Permite rastrear acciones realizadas sobre datos críticos. |
| Inventario por lotes | Control de existencias separado por entradas o grupos de unidades. | Permite aplicar salidas según el orden de ingreso. |
| PEPS/FIFO | Criterio que utiliza primero las unidades que ingresaron antes. | Determina el orden de salida de los lotes de inventario. |
| Política de precios | Regla que condiciona el precio aplicable a una operación. | Centraliza criterios comerciales demostrativos. |
| Script modular | Archivo SQL dedicado a una parte específica del sistema. | Los archivos numerados permiten construir la base en orden. |
| Script consolidado | Archivo que reúne la implementación principal. | `codigo-fuente-gestion-comercial-db.sql` agrupa el proyecto. |
| Consulta de validación | Consulta preparada para comprobar estructura o comportamiento. | `07-consultas-validacion.sql` aporta evidencia técnica. |
| Datos demostrativos | Información ficticia utilizada para ejecutar ejemplos. | Los registros no representan clientes ni operaciones reales. |
| Proyecto académico | Entrega destinada a demostrar aprendizaje técnico. | El proyecto pertenece a `academia` y no representa un producto comercial. |

