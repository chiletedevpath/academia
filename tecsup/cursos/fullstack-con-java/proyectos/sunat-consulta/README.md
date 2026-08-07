# SUNAT Consulta

Backend académico desarrollado con Spring Boot para consultar información de RUC mediante un proveedor externo y registrar historial local de consultas.

## Contexto académico

| Dato | Detalle |
|---|---|
| Institución | Tecsup |
| Curso | Fullstack con Java |
| Enfoque | Backend |
| Tipo | Proyecto académico |
| Lenguaje | Java 17 |
| Base local | H2 en memoria |

## Objetivo

Practicar integración con APIs externas, validación de parámetros, persistencia con JPA y manejo de errores en un backend Java.

## Funcionalidades

- Consulta de datos por RUC.
- Validación de RUC de 11 dígitos.
- Integración HTTP mediante OpenFeign.
- Registro de empresas consultadas.
- Historial de consultas por RUC.
- Registro de consultas exitosas y fallidas.
- Manejo de errores del proveedor externo.
- Base de datos H2 en memoria para práctica local.

## Endpoints principales

| Método | Ruta | Uso |
|---|---|---|
| GET | `/api/sunat/ruc/{ruc}` | Consultar información de una empresa por RUC. |
| GET | `/api/sunat/ruc/{ruc}/consultas` | Consultar historial de búsquedas de un RUC. |

## Configuración local

```env
DECOLECTA_TOKEN=token_local
```

## Ejecución y validación

```powershell
.\mvnw.cmd spring-boot:run
.\mvnw.cmd test
```

Validación actual: los tests se ejecutan correctamente usando H2 en memoria.

## Mejoras futuras

- Agregar ejemplos de respuesta JSON.
- Documentar casos de error del proveedor.
- Agregar pruebas de controlador y servicio con mocks.
- Evaluar caché o control de reintentos para llamadas externas.
