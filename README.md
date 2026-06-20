# Academia

Índice académico de Chilete DevPath.

Este repositorio organiza proyectos académicos formales realizados en UTP, Tecsup y otros espacios de formación. Los proyectos con repositorio propio se referencian desde aquí; los trabajos académicos ya entregados que no requieren repositorio remoto independiente pueden conservarse dentro de la institución y curso correspondiente.

Su función es servir como mapa de consulta para entender el contexto académico, la institución, el curso, el estado y el enlace oficial de cada proyecto.

## Rol dentro de Chilete DevPath

| Repositorio | Rol |
|---|---|
| `aprendizaje` | Ejercicios, laboratorios, fundamentos y práctica progresiva. |
| `academia` | Índice y contenedor de proyectos académicos formales. |
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
| 04 | Base de Datos I | Ferretería Soto DB | `utp/ciclo-04/base-de-datos-i/ferreteria-soto-db` | Agrupado |
| 05 | Base de Datos II | La Lucha BD Backend | [utp-la-lucha-bd-backend](https://github.com/chiletedevpath/utp-la-lucha-bd-backend) | Publicado |
| 05 | Algoritmos y Estructuras de Datos | Clínica Salud Vital | [utp-clinica-salud-vital](https://github.com/chiletedevpath/utp-clinica-salud-vital) | Publicado |
| 05 | Patrones de Diseño | Ferretería Sys Patrones | [utp-ferreteria-sys-patrones](https://github.com/chiletedevpath/utp-ferreteria-sys-patrones) | Publicado |

### Tecsup

| Curso | Enfoque | Proyecto | Repositorio | Estado |
|---|---|---|---|---|
| Fullstack con Java | Backend | SUNAT Consulta | `tecsup/cursos/fullstack-con-java/proyectos/sunat-consulta` | Agrupado |
| Fullstack con Java | Backend | Backend Final Exam | `tecsup/cursos/fullstack-con-java/proyectos/backend-final-exam` | Agrupado |
| Fullstack con Java | Frontend | CRUD React Productos | `tecsup/cursos/fullstack-con-java/proyectos/crud-react-productos` | Agrupado |

## Estados usados

| Estado | Significado |
|---|---|
| Planificado | Existe la idea o el curso, pero aún no hay avance formal. |
| En desarrollo | El proyecto sigue cambiando activamente. |
| Publicado | El proyecto ya tiene repositorio propio visible en GitHub. |
| Agrupado | El proyecto se conserva dentro de `academia` y no requiere repositorio remoto propio. |
| En revisión | Necesita limpieza, pruebas, README final o evidencias. |
| Cerrado | El proyecto quedó estable para consulta académica. |

Un proyecto publicado no significa necesariamente que sea parte del portafolio profesional. Solo indica que el repositorio existe, está visible y puede consultarse desde GitHub.

## Criterio de publicación segura

Antes de agregar o enlazar un proyecto académico, se debe revisar que el contenido sea apto para publicación:

- No subir materiales institucionales de terceros, como guías, diapositivas, rúbricas, PDFs o enunciados completos.
- No usar logos, imágenes, capturas o marcas de instituciones sin autorización.
- No publicar datos personales, credenciales, tokens, claves, correos privados ni información sensible.
- No presentar como propio contenido desarrollado por compañeros, docentes o instituciones.
- Referenciar fuentes externas cuando se usen como base de estudio o consulta.
- Mantener fuera de la web pública los proyectos colaborativos que aún no tengan revisión de permisos, nombres, imágenes y participación.

## Cómo actualizar este repositorio

Cuando se agregue un nuevo proyecto académico:

1. Crear o actualizar su repositorio independiente o ubicarlo dentro de la institución y curso correspondiente.
2. Crear una ficha dentro de la institución, ciclo o curso correspondiente.
3. Agregar el proyecto al índice de este README.
4. Mantener el estado actualizado.
5. No copiar código completo dentro de `academia` salvo cuando se trate de proyectos académicos entregados que ya no requieren repositorio remoto independiente.
6. Si el contenido corresponde a ejercicios, clases o laboratorios pequeños, registrarlo en `aprendizaje`, no en `academia`.

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
