# Tecsup CRUD React Productos

Frontend academico para gestionar productos desde una interfaz React conectada a una API backend.

## Contexto academico

- Institucion: Tecsup
- Curso: Fullstack con Java
- Enfoque: Frontend
- Tipo: Proyecto academico o practica guiada

## Por que se hizo

Este proyecto se desarrollo para practicar consumo de API REST desde React, manejo de estado, formularios controlados y operaciones CRUD en una interfaz web.

El frontend esta pensado para conectarse con un backend de productos que expone endpoints bajo `/productos`.

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
- Crear productos con formulario.
- Editar productos existentes.
- Eliminar productos con confirmacion.
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
| Creacion de productos | Implementada |
| Edicion de productos | Implementada |
| Eliminacion de productos | Implementada |
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
- Confirmar backend definitivo de productos.
- Agregar capturas de pantalla cuando el proyecto este integrado.

## Relacion con Chilete DevPath

Este proyecto forma parte de `academia` porque conserva evidencia de aprendizaje aplicada a React, consumo de API y organización de frontend.
