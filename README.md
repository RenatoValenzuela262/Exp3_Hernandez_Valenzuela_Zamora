Proyecto EDU TECH SPA

# API REST - Proyecto Spring Boot

Este proyecto es una API REST desarrollada con **Spring Boot** que permite gestionar recursos como `Curso, Usuario, Incidencia, Contenido, Cliente`. Incluye operaciones CRUD, pruebas automatizadas y documentación interactiva.

## ⚙️ Tecnologías utilizadas

- Java 17-24
- Spring Boot
- Maven
- Spring Data JPA
- Swagger (Springdoc OpenAPI)
- JUnit (motor de pruebas)
- Mockito (mocks en pruebas unitarias)
- MockMvc (pruebas de integración de endpoints REST)

## Testing

El proyecto incluye pruebas:

### Pruebas Unitarias
- Realizadas con **Mockito** y **JUnit**.
- Se prueban métodos individuales de las clases de servicio usando objetos simulados (mocks).
- No se accede a la base de datos real.

### Pruebas de Integración
- Realizadas con **MockMvc** y **JUnit**.
- Se simulan peticiones HTTP (`GET`, `POST`, etc.) contra los controladores.
- En estas pruebas sí se puede acceder a una base de datos real (usualmente H2 en memoria).

## Servicio: Curso

Este servicio permite gestionar cursos dentro del sistema. Ofrece operaciones para crear, consultar, actualizar y eliminar cursos mediante una API REST.

### Modelo de datos

```json
{
  "id": 1,
  "titulo": "Introducción a Spring Boot",
  "descripcion": "Curso básico para aprender Spring Boot desde cero",
  "publicado": true
}
```

## Endpoints disponibles

### Obtener todos los cursos
- **Método:** `GET`
- **Endpoint:** `/cursos`
- **Descripción:** Retorna una lista con todos los cursos disponibles.

### Obtener curso por ID
- **Método:** `GET`
- **Endpoint:** `/cursos/{id}`
- **Descripción:** Retorna los datos del curso correspondiente al ID entregado.

### Crear nuevo curso
- **Método:** `POST`
- **Endpoint:** `/cursos`
- **Descripción:** Crea un nuevo curso con los datos proporcionados.

#### Cuerpo del request (JSON):
```json
{
  "titulo": "Curso de Java Avanzado",
  "descripcion": "Profundización en conceptos de Java",
  "publicado": false
}
```

#### Respuesta esperada:
```json
{
  "id": 4,
  "titulo": "Curso de Java Avanzado",
  "descripcion": "Profundización en conceptos de Java",
  "publicado": false
}
```

### Actualizar curso
- **Método:** `PUT`
- **Endpoint:** `/cursos/{id}`
- **Descripción:** Actualiza la información del curso especificado por ID.

#### 📥 Cuerpo del request (JSON):
```json
{
  "titulo": "Curso de Java Avanzado",
  "descripcion": "Actualización de contenido",
  "publicado": true
}
```

### Eliminar curso
- **Método:** `DELETE`
- **Endpoint:** `/cursos/{id}`
- **Descripción:** Elimina el curso identificado por el ID especificado.

## Documentación Swagger

La API cuenta con documentación generada automáticamente con **Springdoc OpenAPI** (Swagger).

Una vez que la aplicación está en ejecución, puedes acceder a:

🔗 [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html)  

## Servicio: Usuario

Este servicio permite gestionar usuarios dentro del sistema. Ofrece operaciones para crear, consultar, actualizar y eliminar usuarios mediante una API REST.

## Modelo de datos

```json
{
  "rut": 123456789,
  "nombre": "Juan Pérez",
  "correo": "juan@mail.com",
  "contrasenia": "123456"
}
```

## Endpoints disponibles

### Obtener todos los usuarios
- **Método:** `GET`
- **Endpoint:** `/usuarios`
- **Descripción:** Retorna una lista con todos los usuarios registrados.

### Obtener usuario por RUT
- **Método:** `GET`
- **Endpoint:** `/usuarios/{rut}`
- **Descripción:** Retorna los datos del usuario correspondiente al RUT entregado.

### Crear nuevo usuario
- **Método:** `POST`
- **Endpoint:** `/usuarios`
- **Descripción:** Crea un nuevo usuario con los datos proporcionados.

#### Cuerpo del request (JSON):
```json
{
  "rut": 123456789,
  "nombre": "María González",
  "correo": "maria@mail.com",
  "contrasenia": "abcdef"
}
```

#### Respuesta esperada:
```json
{
  "rut": 123456789,
  "nombre": "María González",
  "correo": "maria@mail.com",
  "contrasenia": "abcdef"
}
```

### Actualizar usuario
- **Método:** `PUT`
- **Endpoint:** `/usuarios/{rut}`
- **Descripción:** Actualiza la información del usuario especificado por su RUT.

#### Cuerpo del request (JSON):
```json
{
  "nombre": "María G. López",
  "correo": "maria.lopez@mail.com",
  "contrasenia": "nuevaClave123"
}
```

### Eliminar usuario
- **Método:** `DELETE`
- **Endpoint:** `/usuarios/{rut}`
- **Descripción:** Elimina el usuario identificado por el RUT especificado.

## Documentación Swagger

Puedes explorar y probar los endpoints de la API en la documentación Swagger disponible en:

🔗 [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html)

## Servicio: Incidencia

Este servicio permite gestionar incidencias dentro del sistema. Ofrece operaciones para crear, consultar, actualizar y eliminar incidencias mediante una API REST.

## Modelo de datos

```json
{
  "id": 1,
  "descripcion": "Problema con la red",
  "estado": "Pendiente",
  "prioridad": "Alta"
}
```

## Endpoints disponibles

### Obtener todas las incidencias
- Método: GET
- Endpoint: /incidencias
- Descripción: Retorna una lista con todas las incidencias registradas.

### Obtener incidencia por ID
- Método: GET
- Endpoint: /incidencias/{id}
- Descripción: Retorna los datos de la incidencia correspondiente al ID entregado.

### Crear nueva incidencia
- Método: POST
- Endpoint: /incidencias
- Descripción: Crea una nueva incidencia con los datos proporcionados.

Cuerpo del request (JSON):
```json
{
  "descripcion": "Falla en el servidor",
  "estado": "Pendiente",
  "prioridad": "Alta"
}
```

Respuesta esperada:
```json
{
  "id": 5,
  "descripcion": "Falla en el servidor",
  "estado": "Pendiente",
  "prioridad": "Alta"
}
```

### Actualizar incidencia
- Método: PUT
- Endpoint: /incidencias/{id}
- Descripción: Actualiza la información de la incidencia especificada por ID.

Cuerpo del request (JSON):
```json
{
  "descripcion": "Falla crítica en servidor principal",
  "estado": "En proceso",
  "prioridad": "Crítica"
}
```

### Eliminar incidencia
- Método: DELETE
- Endpoint: /incidencias/{id}
- Descripción: Elimina la incidencia identificada por el ID especificado.

## Documentación Swagger

Puedes explorar y probar los endpoints de la API en la documentación Swagger disponible en:

http://localhost:8080/swagger-ui.html

# API REST - Servicio Contenido

Este servicio permite gestionar contenidos dentro del sistema. Ofrece operaciones para crear, consultar, actualizar y eliminar contenidos mediante una API REST.

## Modelo de datos

```json
{
  "id": 1,
  "nombre": "Video Introductorio",
  "tipo": "Video"
}
```

## Endpoints disponibles

### Obtener todos los contenidos
- Método: GET
- Endpoint: /contenidos
- Descripción: Retorna una lista con todos los contenidos registrados.

### Obtener contenido por ID
- Método: GET
- Endpoint: /contenidos/{id}
- Descripción: Retorna los datos del contenido correspondiente al ID entregado.

### Crear nuevo contenido
- Método: POST
- Endpoint: /contenidos
- Descripción: Crea un nuevo contenido con los datos proporcionados.

Cuerpo del request (JSON):
```json
{
  "nombre": "Guía PDF Introducción",
  "tipo": "Documento"
}
```

Respuesta esperada:
```json
{
  "id": 4,
  "nombre": "Guía PDF Introducción",
  "tipo": "Documento"
}
```

### Actualizar contenido
- Método: PUT
- Endpoint: /contenidos/{id}
- Descripción: Actualiza la información del contenido especificado por ID.

Cuerpo del request (JSON):
```json
{
  "nombre": "Video avanzado",
  "tipo": "Video"
}
```

### Eliminar contenido
- Método: DELETE
- Endpoint: /contenidos/{id}
- Descripción: Elimina el contenido identificado por el ID especificado.

## Documentación Swagger

Puedes explorar y probar los endpoints de la API en la documentación Swagger disponible en:

http://localhost:8080/swagger-ui.html

