# Academia

Índice académico de Chilete DevPath.

Este repositorio organiza proyectos académicos formales realizados en UTP, Tecsup y otros espacios de formación. No guarda el código de los proyectos: cada entrega, examen o proyecto publicado vive en su repositorio independiente.

Su función es servir como mapa de consulta para entender el contexto académico, la institución, el curso, el estado y el enlace oficial de cada proyecto.

## Rol dentro de Chilete DevPath

| Repositorio | Rol |
|---|---|
| `aprendizaje` | Ejercicios, laboratorios, fundamentos y práctica progresiva. |
| `academia` | Índice de proyectos académicos formales publicados. |
| `portafolio` | Selección futura de proyectos destacados para presentación profesional. |

En una web pública, esta sección debe mostrarse como **Proyectos académicos**.

## Criterio de organización

```txt
academia/
├── utp/
│   └── ciclo-XX/
│       └── nombre-del-curso/
│           └── ficha-del-proyecto.md
├── tecsup/
│   └── cursos/
│       └── nombre-del-curso/
│           └── area/
│               └── ficha-del-proyecto.md
└── otros/
```

## Índice actual

### UTP

| Ciclo | Curso | Proyecto | Repositorio | Estado |
|---|---|---|---|---|
| 04 | Base de Datos I | Ferretería Soto DB | [utp-ferreteria-soto-db](https://github.com/chiletedevpath/utp-ferreteria-soto-db) | Publicado |
| 05 | Base de Datos II | La Lucha BD Backend | [utp-la-lucha-bd-backend](https://github.com/chiletedevpath/utp-la-lucha-bd-backend) | Publicado |
| 05 | Algoritmos y Estructuras de Datos | Clínica Salud Vital | [utp-clinica-salud-vital](https://github.com/chiletedevpath/utp-clinica-salud-vital) | Publicado |
| 05 | Patrones de Diseño | Ferretería Sys Patrones | [utp-ferreteria-sys-patrones](https://github.com/chiletedevpath/utp-ferreteria-sys-patrones) | Publicado |

### Tecsup

| Curso | Enfoque | Proyecto | Repositorio | Estado |
|---|---|---|---|---|
| Fullstack con Java | Backend | SUNAT Consulta | [tecsup-sunat-consulta](https://github.com/chiletedevpath/tecsup-sunat-consulta) | Publicado |
| Fullstack con Java | Backend | Backend Final Exam | [tecsup-backend-final-exam](https://github.com/chiletedevpath/tecsup-backend-final-exam) | Publicado |
| Fullstack con Java | Frontend | CRUD React Productos | [tecsup-crud-react-productos](https://github.com/chiletedevpath/tecsup-crud-react-productos) | Publicado |
| Fullstack con Java | Frontend | Frontend Retos | [tecsup-frontend-retos](https://github.com/chiletedevpath/tecsup-frontend-retos) | Publicado |

## Estados usados

| Estado | Significado |
|---|---|
| Planificado | Existe la idea o el curso, pero aún no hay avance formal. |
| En desarrollo | El proyecto sigue cambiando activamente. |
| Publicado | El proyecto ya tiene repositorio propio visible en GitHub. |
| En revisión | Necesita limpieza, pruebas, README final o evidencias. |
| Cerrado | El proyecto quedó estable para consulta académica. |

Un proyecto publicado no significa necesariamente que sea parte del portafolio profesional. Solo indica que el repositorio existe, está visible y puede consultarse desde GitHub.

## Cómo actualizar este repositorio

Cuando se agregue un nuevo proyecto académico:

1. Crear o actualizar su repositorio independiente.
2. Crear una ficha dentro de la institución, ciclo o curso correspondiente.
3. Agregar el proyecto al índice de este README.
4. Mantener el estado actualizado.
5. No copiar código completo dentro de `academia`.
6. Si el contenido corresponde a ejercicios, clases o laboratorios, registrarlo en `aprendizaje`, no en `academia`.

## Plantilla breve para nuevas fichas

```md
# Nombre del Proyecto

## Resumen

Descripción corta del proyecto.

## Contexto académico

- Institución:
- Ciclo o curso:
- Curso o enfoque:
- Tipo:

## Repositorio

[Nombre del repo](URL)

## Estado

Estado actual.

## Observaciones

- Contexto relevante.
- Restricciones o notas de publicación.
```

## Nota de mantenimiento

Este README debe cambiar cada vez que cambie la estructura académica, se publique un nuevo repositorio o un proyecto pase a otro estado.
