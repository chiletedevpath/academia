# Tecsup SUNAT Consulta

Backend academico para consultar informacion de RUC usando el servicio externo Decolecta.

## Autor

- Chilete DevPath
- Estudiante de Ingenieria de Sistemas e Informatica

## Contexto academico

- Institucion: Tecsup
- Curso: Fullstack con Java
- Enfoque: Backend
- Tipo: Proyecto academico o evaluacion practica

## Por que se hizo

Este proyecto se desarrollo para practicar integracion con APIs externas, validacion de datos, persistencia local de consultas y manejo de errores en un backend Java con Spring Boot.

El caso trabajado consiste en consultar datos de empresas por RUC, registrar historial de consultas y conservar informacion obtenida desde el proveedor externo.

## Tecnologias usadas

- Java 17
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Spring Validation
- Spring Cloud OpenFeign
- H2 Database
- Maven Wrapper
- Decolecta API

## Que contiene el proyecto

```txt
academia/tecsup/cursos/fullstack-con-java/proyectos/sunat-consulta/
|-- src/
|   |-- main/java/com/demo/sunat/
|   |-- main/resources/application.properties
|   `-- test/
|-- assets/img/evidencias/
|-- pom.xml
|-- mvnw
|-- mvnw.cmd
`-- README.md
```

## Funcionalidades principales

- Consulta de datos por RUC.
- Validacion de RUC de 11 digitos.
- Integracion HTTP con Decolecta mediante OpenFeign.
- Registro de empresas consultadas.
- Historial de consultas por RUC.
- Registro de consultas exitosas y fallidas.
- Manejo de errores del proveedor externo.
- Base de datos H2 en memoria para practica local.

## Endpoints principales

Base path:

```txt
/api/sunat
```

| Metodo | Ruta | Uso |
|---|---|---|
| GET | `/api/sunat/ruc/{ruc}` | Consultar informacion de una empresa por RUC |
| GET | `/api/sunat/ruc/{ruc}/consultas` | Consultar historial de busquedas de un RUC |

## Variables de entorno

```env
DECOLECTA_TOKEN=token_local
```

La URL base del proveedor esta configurada en `application.properties`:

```properties
decolecta.base-url=https://api.decolecta.com
```

## Base de datos local

El proyecto usa H2 en memoria:

```properties
spring.datasource.url=jdbc:h2:mem:sunatdb
spring.h2.console.path=/h2-console
```

## Ejecucion local

```powershell
.\mvnw.cmd spring-boot:run
```

En Git Bash o sistemas Unix:

```bash
./mvnw spring-boot:run
```

## Estado del proyecto

| Punto | Estado |
|---|---|
| Consulta por RUC | Implementada |
| Historial de consultas | Implementado |
| Integracion con Decolecta | Implementada |
| Persistencia H2 | Configurada |
| Evidencias | Incluidas |
| Ejemplos de request/response | Pendiente |
| Validacion completa en entorno limpio | Pendiente |

Las evidencias se conservan como material academico de entrega. Antes de publicar este proyecto en una web o repositorio remoto nuevo, deben revisarse capturas, colecciones y RUC usados para evitar exponer datos reales o dependencias de terceros sin contexto.

## Aprendizajes aplicados

- Consumo de API externa con OpenFeign.
- Manejo de variables de entorno para tokens.
- Validacion de parametros con anotaciones.
- Persistencia de historial con JPA.
- Manejo de errores de proveedor externo.
- Uso de H2 para pruebas academicas rapidas.

## Pendientes

- Agregar ejemplos de respuesta JSON.
- Documentar casos de error del proveedor.
- Confirmar si se usara una base persistente en una version posterior.
- Agregar instrucciones para configurar el token en Windows y Git Bash.
- Revisar evidencias y RUC de ejemplo antes de cualquier publicacion web.

## Relacion con Chilete DevPath

Este proyecto forma parte de la seccion academica de Chilete DevPath:

- [academia](https://github.com/chiletedevpath/academia)
- [aprendizaje](https://github.com/chiletedevpath/aprendizaje)
- [chiletedevpath](https://github.com/chiletedevpath/chiletedevpath)
