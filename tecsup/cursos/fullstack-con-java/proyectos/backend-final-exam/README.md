# Backend Final Exam

Proyecto académico de microservicios backend desarrollado como evaluación del curso **Fullstack con Java** de Tecsup.

La entrega agrupa dos servicios relacionados: `ms-productos` y `ms-pedidos`. Se mantienen juntos porque comparten contexto académico, stack técnico y objetivo de evaluación.

## Objetivo

Practicar construcción de servicios REST con Spring Boot, persistencia con JPA, validaciones, manejo de excepciones y configuración mediante variables de entorno.

## Microservicios

| Servicio | Responsabilidad | Puerto |
|---|---|---|
| `ms-productos` | Gestión de productos | `${PORT:8080}` |
| `ms-pedidos` | Gestión de pedidos | `8081` |

## Variables de entorno

```env
DB_URL=jdbc:postgresql://localhost:5432/nombre_bd
DB_USERNAME=usuario_local
DB_PASSWORD=password_local
```

## Ejecución local

```powershell
cd ms-productos
.\mvnw.cmd spring-boot:run
```

```powershell
cd ms-pedidos
.\mvnw.cmd spring-boot:run
```

## Validación actual

Durante la auditoría, ambos servicios compilaron hasta iniciar contexto de pruebas, pero los tests fallaron por configuración de datasource:

| Servicio | Resultado | Motivo |
|---|---|---|
| `ms-productos` | Falla en tests | La URL de datasource no se resuelve como JDBC válido. |
| `ms-pedidos` | Falla en tests | `${DB_URL}` queda sin resolver durante pruebas. |

Esto no invalida la entrega académica, pero sí deja una deuda clara para que la validación local sea reproducible sin depender de variables manuales.

## Mejoras futuras

- Agregar perfil `test` con H2 o PostgreSQL de prueba.
- Documentar ejemplos de request y response.
- Documentar modelo de datos de productos y pedidos.
- Definir si existirá comunicación real entre servicios.
- Agregar comandos Docker completos con variables de entorno.
