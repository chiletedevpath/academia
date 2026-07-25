# ms-pedidos

Microservicio backend desarrollado con Spring Boot para gestionar pedidos. Forma parte del **Backend Final Exam** de Tecsup junto con `ms-productos`.

## PropÃ³sito

Permitir el registro, consulta, eliminaciÃ³n y actualizaciÃ³n de estado de pedidos, aplicando validaciones y cÃ¡lculo automÃ¡tico del total.

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
POST   /api/pedidos
GET    /api/pedidos
GET    /api/pedidos/{id}
DELETE /api/pedidos/{id}
PATCH  /api/pedidos/{id}/estado
```

## Variables de entorno

```env
DB_URL=jdbc:postgresql://localhost:5432/nombre_bd
DB_USERNAME=usuario_local
DB_PASSWORD=password_local
PORT=8081
```

## ValidaciÃ³n actual

El test de contexto falla cuando `${DB_URL}` no se resuelve durante pruebas. Para hacerlo reproducible se recomienda agregar un perfil `test` con H2 o documentar una base PostgreSQL local para validaciÃ³n.

## Estado

Proyecto acadÃ©mico finalizado, con deuda pendiente en configuraciÃ³n de pruebas locales.
