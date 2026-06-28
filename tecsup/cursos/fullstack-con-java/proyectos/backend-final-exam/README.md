# Tecsup Backend Final Exam

Microservicios backend desarrollados como evaluacion del curso Fullstack con Java en Tecsup.

## Contexto academico

- Institucion: Tecsup
- Curso: Fullstack con Java
- Tipo: Evaluacion academica

## Por que se hizo

Este proyecto se realizo para practicar el desarrollo de microservicios backend con Spring Boot, persistencia en PostgreSQL y separacion de responsabilidades entre servicios.

La carpeta agrupa dos servicios relacionados: productos y pedidos. Se mantienen juntos porque pertenecen a una misma evaluacion y comparten contexto funcional.

## Tecnologias usadas

- Java 17
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Spring Validation
- PostgreSQL
- Lombok
- Maven Wrapper
- Dockerfile por microservicio

## Microservicios

```txt
academia/tecsup/cursos/fullstack-con-java/proyectos/backend-final-exam/
|-- ms-productos/
|-- ms-pedidos/
|-- .gitignore
`-- README.md
```

| Servicio | Responsabilidad | Puerto |
|---|---|---|
| `ms-productos` | Gestion de productos | `${PORT:8080}` |
| `ms-pedidos` | Gestion de pedidos | `8081` |

## Funcionalidades principales

### ms-productos

- Crear productos.
- Listar productos.
- Buscar productos por ID.
- Actualizar productos.
- Eliminar productos.

### ms-pedidos

- Crear pedidos.
- Listar pedidos.
- Buscar pedidos por ID.
- Eliminar pedidos.
- Actualizar estado de pedidos.

## Endpoints principales

### ms-productos

| Metodo | Ruta | Uso |
|---|---|---|
| POST | `/api/productos` | Crear producto |
| GET | `/api/productos` | Listar productos |
| GET | `/api/productos/{id}` | Buscar producto por ID |
| PUT | `/api/productos/{id}` | Actualizar producto |
| DELETE | `/api/productos/{id}` | Eliminar producto |

### ms-pedidos

| Metodo | Ruta | Uso |
|---|---|---|
| POST | `/api/pedidos` | Crear pedido |
| GET | `/api/pedidos` | Listar pedidos |
| GET | `/api/pedidos/{id}` | Buscar pedido por ID |
| DELETE | `/api/pedidos/{id}` | Eliminar pedido |
| PATCH | `/api/pedidos/{id}/estado` | Actualizar estado del pedido |

## Variables de entorno

Ambos microservicios usan PostgreSQL mediante variables de entorno:

```env
DB_URL=jdbc:postgresql://localhost:5432/nombre_bd
DB_USERNAME=usuario_local
DB_PASSWORD=password_local
```

Para `ms-productos` tambien se puede configurar:

```env
PORT=8080
```

`ms-pedidos` tiene el puerto configurado directamente:

```properties
server.port=8081
```

## Ejecucion local

### ms-productos

```powershell
cd ms-productos
.\mvnw.cmd spring-boot:run
```

### ms-pedidos

```powershell
cd ms-pedidos
.\mvnw.cmd spring-boot:run
```

## Estado del proyecto

| Punto | Estado |
|---|---|
| Microservicio productos | Implementado |
| Microservicio pedidos | Implementado |
| Persistencia PostgreSQL | Configurada por variables de entorno |
| Dockerfile por servicio | Incluido |

## Aprendizajes aplicados

- Creacion de microservicios con Spring Boot.
- Separacion por capas: controller, service, repository y entity.
- Manejo de validaciones y excepciones.
- Uso de PostgreSQL en proyectos backend.
- Configuracion mediante variables de entorno.
- Preparacion inicial para despliegue con Docker.

## Mejoras futuras

- Agregar ejemplos de request y response.
- Documentar modelo de datos de productos y pedidos.
- Confirmar flujo de comunicacion entre servicios, si aplica.
- Documentar comandos Docker definitivos.
- Agregar evidencias del funcionamiento si el curso lo solicita.

## Relacion con Chilete DevPath

Este proyecto forma parte de `academia` porque conserva evidencia de aprendizaje aplicada a backend, microservicios y Spring Boot.
