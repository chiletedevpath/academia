# SUNAT Consulta

Backend acadÃ©mico desarrollado con Spring Boot para consultar informaciÃ³n de RUC mediante un proveedor externo y registrar historial local de consultas.

## Contexto acadÃ©mico

| Dato | Detalle |
|---|---|
| InstituciÃ³n | Tecsup |
| Curso | Fullstack con Java |
| Enfoque | Backend |
| Tipo | Proyecto acadÃ©mico |
| Lenguaje | Java 17 |
| Base local | H2 en memoria |

## Objetivo

Practicar integraciÃ³n con APIs externas, validaciÃ³n de parÃ¡metros, persistencia con JPA y manejo de errores en un backend Java.

## Funcionalidades

- Consulta de datos por RUC.
- ValidaciÃ³n de RUC de 11 dÃ­gitos.
- IntegraciÃ³n HTTP mediante OpenFeign.
- Registro de empresas consultadas.
- Historial de consultas por RUC.
- Registro de consultas exitosas y fallidas.
- Manejo de errores del proveedor externo.
- Base de datos H2 en memoria para prÃ¡ctica local.

## Endpoints principales

| MÃ©todo | Ruta | Uso |
|---|---|---|
| GET | `/api/sunat/ruc/{ruc}` | Consultar informaciÃ³n de una empresa por RUC. |
| GET | `/api/sunat/ruc/{ruc}/consultas` | Consultar historial de bÃºsquedas de un RUC. |

## ConfiguraciÃ³n local

```env
DECOLECTA_TOKEN=token_local
```

## EjecuciÃ³n y validaciÃ³n

```powershell
.\mvnw.cmd spring-boot:run
.\mvnw.cmd test
```

ValidaciÃ³n actual: los tests se ejecutan correctamente usando H2 en memoria.

## Mejoras futuras

- Agregar ejemplos de respuesta JSON.
- Documentar casos de error del proveedor.
- Agregar pruebas de controlador y servicio con mocks.
- Evaluar cachÃ© o control de reintentos para llamadas externas.
