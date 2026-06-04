# Academia

Mapa académico de Chilete DevPath.

Este repositorio no busca guardar todo el código de los proyectos. Su función es organizar el recorrido académico, registrar contexto y enlazar los repositorios independientes donde vive cada proyecto.

## Criterio de organización

```txt
academia/
├── utp/
│   └── ciclo-XX/
│       └── area-del-curso/
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

| Ciclo | Área | Proyecto | Repositorio | Estado |
|---|---|---|---|---|
| 04 | Base de Datos I | Ferretería Soto DB | [utp-ferreteria-soto-db](https://github.com/chiletedevpath/utp-ferreteria-soto-db) | Migrado |
| 05 | Base de Datos II | La Lucha BD Backend | [utp-la-lucha-bd-backend](https://github.com/chiletedevpath/utp-la-lucha-bd-backend) | Migrado |
| 05 | Algoritmos y Estructuras de Datos | Clínica Salud Vital | [utp-clinica-salud-vital](https://github.com/chiletedevpath/utp-clinica-salud-vital) | Migrado |
| 05 | Patrones de Diseño | Ferretería Sys Patrones | [utp-ferreteria-sys-patrones](https://github.com/chiletedevpath/utp-ferreteria-sys-patrones) | Migrado |

### Tecsup

| Curso | Área | Proyecto | Repositorio | Estado |
|---|---|---|---|---|
| Fullstack con Java | Backend | SUNAT Consulta | [tecsup-sunat-consulta](https://github.com/chiletedevpath/tecsup-sunat-consulta) | Migrado |
| Fullstack con Java | Backend | Backend Final Exam | [tecsup-backend-final-exam](https://github.com/chiletedevpath/tecsup-backend-final-exam) | Migrado |
| Fullstack con Java | Frontend | CRUD React Productos | [tecsup-crud-react-productos](https://github.com/chiletedevpath/tecsup-crud-react-productos) | Migrado |
| Fullstack con Java | Frontend | Frontend Retos | [tecsup-frontend-retos](https://github.com/chiletedevpath/tecsup-frontend-retos) | Migrado |

## Estados usados

| Estado | Significado |
|---|---|
| Planificado | Existe la idea o el curso, pero aún no hay avance formal. |
| En desarrollo | El proyecto sigue cambiando activamente. |
| Migrado | Fue separado desde una estructura anterior y ya tiene repositorio propio. |
| En revisión | Necesita limpieza, pruebas, README final o evidencias. |
| Cerrado | El proyecto quedó estable para consulta académica. |

## Cómo actualizar este repositorio

Cuando se agregue un nuevo proyecto académico:

1. Crear o actualizar su repositorio independiente.
2. Crear una ficha dentro de la institución, ciclo o curso correspondiente.
3. Agregar el proyecto al índice de este README.
4. Mantener el estado actualizado.
5. No copiar código completo dentro de `academia`, salvo que sea una evidencia pequeña y justificada.

## Plantilla breve para nuevas fichas

```md
# Nombre del Proyecto

## Resumen

Descripción corta del proyecto.

## Contexto académico

- Institución:
- Ciclo o curso:
- Área:
- Tipo:

## Repositorio

[Nombre del repo](URL)

## Estado

Estado actual.

## Pendiente

- Punto pendiente 1.
- Punto pendiente 2.
```

## Nota de mantenimiento

Este README debe cambiar cada vez que cambie la estructura académica, se publique un nuevo repositorio o un proyecto pase a otro estado.
