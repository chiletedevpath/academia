# Gestor de CatÃ¡logo de Productos

Frontend acadÃ©mico desarrollado en React para administrar un catÃ¡logo de productos desde una interfaz conectada a una API backend.

## Contexto acadÃ©mico

| Dato | Detalle |
|---|---|
| InstituciÃ³n | Tecsup |
| Curso | Fullstack con Java |
| Enfoque | Frontend |
| Tipo | PrÃ¡ctica acadÃ©mica |
| Stack | React, Vite y JavaScript |

## Objetivo

Practicar consumo de API REST desde React, manejo de estado, formularios controlados y operaciones CRUD en una interfaz web.

## Funcionalidades

- Listar productos desde una API.
- Registrar productos con formulario.
- Editar productos existentes.
- Eliminar productos con confirmaciÃ³n.
- Mostrar estados de carga y error.
- Centralizar llamadas HTTP en `productService.js`.

## ConexiÃ³n con la API

Crear un archivo `.env.local` a partir de `.env.example`:

```env
VITE_API_BASE_URL=http://localhost:8080/api
```

## InstalaciÃ³n y ejecuciÃ³n

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

## ValidaciÃ³n local

Para validar build o lint se requiere instalar dependencias con `npm install`. En la auditorÃ­a actual no se ejecutÃ³ build porque `node_modules` no estaba presente.

## Mejoras futuras

- Mejorar validaciones del formulario.
- Agregar mensajes visuales de Ã©xito y error.
- Incorporar bÃºsqueda y filtro por estado.
- Confirmar integraciÃ³n con el backend definitivo del catÃ¡logo.
- Agregar pruebas bÃ¡sicas de componentes o flujo principal.
