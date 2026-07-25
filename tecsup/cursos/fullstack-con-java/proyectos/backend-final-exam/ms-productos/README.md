# ms-productos

Microservicio backend desarrollado con Spring Boot para gestionar productos. Forma parte del **Backend Final Exam** de Tecsup junto con `ms-pedidos`.

## PropÃ³sito

Permitir el registro, consulta, actualizaciÃ³n y eliminaciÃ³n lÃ³gica de productos, aplicando validaciones, persistencia en PostgreSQL y manejo centralizado de errores.

## Arquitectura

| Paquete | Responsabilidad |
|---|---|
| `controller` | Expone endpoints REST. |
| `service` | Contiene reglas de negocio. |
| `repository` | Accede a datos con Spring Data JPA. |
| `dto` | Define objetos de entrada. |
| `entity` | Representa el modelo persistente. |
| `exception` | Centraliza errores de la API. |

## Endpoints principales

```http
POST   /api/productos
GET    /api/productos
GET    /api/productos/{id}
PUT    /api/productos/{id}
DELETE /api/productos/{id}
```

## Variables de entorno

```env
DB_URL=jdbc:postgresql://localhost:5432/nombre_bd
DB_USERNAME=usuario_local
DB_PASSWORD=password_local
PORT=8080
```

## ValidaciÃ³n actual

El test de contexto falla si no existe una URL JDBC vÃ¡lida para `DB_URL`. Para hacerlo reproducible se recomienda agregar un perfil `test` con H2 o una configuraciÃ³n de PostgreSQL local documentada.

## Estado

Proyecto acadÃ©mico finalizado, con deuda pendiente en configuraciÃ³n de pruebas locales.
