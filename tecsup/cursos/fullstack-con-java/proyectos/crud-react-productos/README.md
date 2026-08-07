# Gestor de Catálogo de Productos

Frontend académico desarrollado en React para administrar un catálogo de productos desde una interfaz conectada a una API backend.

## Contexto académico

| Dato | Detalle |
|---|---|
| Institución | Tecsup |
| Curso | Fullstack con Java |
| Enfoque | Frontend |
| Tipo | Práctica académica |
| Stack | React, Vite y JavaScript |

## Objetivo

Practicar consumo de API REST desde React, manejo de estado, formularios controlados y operaciones CRUD en una interfaz web.

## Funcionalidades

- Listar productos desde una API.
- Registrar productos con formulario.
- Editar productos existentes.
- Eliminar productos con confirmación.
- Mostrar estados de carga y error.
- Centralizar llamadas HTTP en `productService.js`.

## Conexión con la API

Crear un archivo `.env.local` a partir de `.env.example`:

```env
VITE_API_BASE_URL=http://localhost:8080/api
```

## Instalación y ejecución

```powershell
npm install
npm run dev
```

## Scripts disponibles

```powershell
npm run build
npm run lint
npm run preview
```

## Validación local

Para validar build o lint se requiere instalar dependencias con `npm install`. En la auditoría actual no se ejecutó build porque `node_modules` no estaba presente.

## Mejoras futuras

- Mejorar validaciones del formulario.
- Agregar mensajes visuales de éxito y error.
- Incorporar búsqueda y filtro por estado.
- Confirmar integración con el backend definitivo del catálogo.
- Agregar pruebas básicas de componentes o flujo principal.
