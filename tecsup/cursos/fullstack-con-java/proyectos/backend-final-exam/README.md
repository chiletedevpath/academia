# Backend Final Exam

Proyecto acadÃ©mico de microservicios backend desarrollado como evaluaciÃ³n del curso **Fullstack con Java** de Tecsup.

La entrega agrupa dos servicios relacionados: `ms-productos` y `ms-pedidos`. Se mantienen juntos porque comparten contexto acadÃ©mico, stack tÃ©cnico y objetivo de evaluaciÃ³n.

## Objetivo

Practicar construcciÃ³n de servicios REST con Spring Boot, persistencia con JPA, validaciones, manejo de excepciones y configuraciÃ³n mediante variables de entorno.

## Microservicios

| Servicio | Responsabilidad | Puerto |
|---|---|---|
| `ms-productos` | GestiÃ³n de productos | `${PORT:8080}` |
| `ms-pedidos` | GestiÃ³n de pedidos | `8081` |

## Variables de entorno

```env
DB_URL=jdbc:postgresql://localhost:5432/nombre_bd
DB_USERNAME=usuario_local
DB_PASSWORD=password_local
```

## EjecuciÃ³n local

```powershell
cd ms-productos
.\mvnw.cmd spring-boot:run
```

```powershell
cd ms-pedidos
.\mvnw.cmd spring-boot:run
```

## ValidaciÃ³n actual

Durante la auditorÃ­a, ambos servicios compilaron hasta iniciar contexto de pruebas, pero los tests fallaron por configuraciÃ³n de datasource:

| Servicio | Resultado | Motivo |
|---|---|---|
| `ms-productos` | Falla en tests | La URL de datasource no se resuelve como JDBC vÃ¡lido. |
| `ms-pedidos` | Falla en tests | `${DB_URL}` queda sin resolver durante pruebas. |

Esto no invalida la entrega acadÃ©mica, pero sÃ­ deja una deuda clara para que la validaciÃ³n local sea reproducible sin depender de variables manuales.

## Mejoras futuras

- Agregar perfil `test` con H2 o PostgreSQL de prueba.
- Documentar ejemplos de request y response.
- Documentar modelo de datos de productos y pedidos.
- Definir si existirÃ¡ comunicaciÃ³n real entre servicios.
- Agregar comandos Docker completos con variables de entorno.
