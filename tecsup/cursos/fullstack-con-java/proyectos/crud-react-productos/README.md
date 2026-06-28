# Gestor de Catálogo de Productos

Frontend académico desarrollado en React para administrar un catálogo de productos desde una interfaz conectada a una API backend.

## Contexto academico

- Institucion: Tecsup
- Curso: Fullstack con Java
- Enfoque: Frontend
- Tipo: Proyecto académico o práctica guiada

## Por que se hizo

Este proyecto se desarrolló para practicar consumo de API REST desde React, manejo de estado, formularios controlados y operaciones CRUD en una interfaz web.

El frontend está pensado como base para un sistema de catálogo e inventario: lista productos, permite registrar nuevos ítems, editar información y retirar registros cuando ya no correspondan.

La API esperada expone endpoints bajo `/productos`.

## Tecnologias usadas

- React
- Vite
- JavaScript
- Fetch API
- CSS
- ESLint

## Que contiene el proyecto

```txt
academia/tecsup/cursos/fullstack-con-java/proyectos/crud-react-productos/
|-- public/
|-- src/
|   |-- components/ProductForm.jsx
|   |-- services/productService.js
|   |-- styles/app.css
|   |-- App.jsx
|   `-- main.jsx
|-- .env.example
|-- package.json
`-- README.md
```

## Funcionalidades principales

- Listar productos desde una API.
- Registrar productos con formulario.
- Editar productos existentes.
- Eliminar productos con confirmación.
- Mostrar estados de carga y error.
- Centralizar llamadas HTTP en `productService.js`.

## Campos del producto

El formulario trabaja con:

```txt
nombre
descripcion
precio
stock
estado
```

## Conexion con la API

Crear un archivo `.env.local` a partir de `.env.example`:

```env
VITE_API_BASE_URL=http://localhost:8080/api
```

El servicio construye la URL de productos asi:

```txt
${VITE_API_BASE_URL}/productos
```

## Instalacion

```powershell
npm install
```

## Ejecucion local

```powershell
npm run dev
```

## Scripts disponibles

```powershell
npm run build
npm run lint
npm run preview
```

## Estado del proyecto

| Punto | Estado |
|---|---|
| Listado de productos | Implementado |
| Registro de productos | Implementado |
| Edición de productos | Implementada |
| Eliminación de productos | Implementada |
| Configuracion de API por entorno | Implementada |

## Aprendizajes aplicados

- Componentes funcionales en React.
- `useState` y `useEffect`.
- Formularios controlados.
- Consumo de API con `fetch`.
- Separacion entre UI y servicio HTTP.
- Uso de variables de entorno con Vite.

## Mejoras futuras

- Mejorar validaciones del formulario.
- Agregar mensajes visuales de exito y error.
- Agregar validaciones por regla de negocio: precio mayor a cero, stock no negativo y nombre único.
- Incorporar búsqueda y filtro por estado del producto.
- Mejorar la experiencia visual para diferenciar carga, error, registro exitoso y edición activa.
- Confirmar integración con el backend definitivo del catálogo.

## Relacion con Chilete DevPath

Este proyecto forma parte de `academia` porque conserva evidencia de aprendizaje aplicada a React, consumo de API y organización de frontend.
