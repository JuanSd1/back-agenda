# Agendamiento de Eventos - Backend

## Descripción
Este proyecto es el backend de una aplicación de gestión de eventos que permite crear, editar, eliminar y notificar eventos a los usuarios. Está desarrollado con **Java** y **Spring Boot**, siguiendo buenas prácticas de arquitectura en capas y utilizando una base de datos **PostgreSQL** para el almacenamiento de datos.

El backend expone una API REST para interactuar con el frontend y realizar operaciones CRUD, además de notificar eventos importantes por correo electrónico.

## Características
- **CRUD de Eventos**: Crear, leer, actualizar y eliminar eventos.
- **Notificaciones**: Envío de notificaciones por correo electrónico.
- **Documentación**: Integración con **Swagger** para documentar y probar los endpoints.
- **Manejo de Errores**: Implementación de controladores de errores para respuestas consistentes.
- **Seguridad**: Configuración básica para proteger los endpoints.

## Tecnologías Utilizadas
- **Java**: Lenguaje principal.
- **Spring Boot**: Framework para el desarrollo del backend.
  - **Spring Data JPA**: Manejo de datos.
  - **Spring Web**: Creación de la API REST.
  - **Spring Mail**: Envío de correos electrónicos.
- **PostgreSQL**: Base de datos relacional.
- **Swagger**: Documentación y pruebas de la API.
- **Docker**: Contenedorización opcional.
- **Maven**: Gestión de dependencias.

## Estructura del Proyecto
```plaintext
src/
├── main/
│   ├── java/
│   │   └── com/agendaeventos/
│   │       ├── controller/         # Controladores REST
│   │       ├── service/            # Lógica de negocio
│   │       ├── repository/         # Repositorios JPA
│   │       ├── model/              # Entidades (clases de dominio)
│   │       └── config/             # Configuraciones (Swagger, Mail)
│   └── resources/
│       ├── application.properties  # Configuración de la aplicación
│       └── schema.sql              # Script de inicialización de la base de datos
└── test/                           # Pruebas unitarias
```

## Instalación y Configuración

Requisitos

- Java 17
- Maven
- PostgreSQL
  
## Configuración

Clona el repositorio:

```plaintext
git clone https://github.com/tu-usuario/agenda-eventos-backend.git
cd agenda-eventos-backend
```

## Configura las variables de entorno o modifica application.properties para la conexión con PostgreSQL:

```plaintext
spring.datasource.url=jdbc:postgresql://localhost:5432/agendaeventos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.mail.host=smtp.example.com
spring.mail.port=587
spring.mail.username=correo@example.com
spring.mail.password=tu_contraseña
```

## Ejecuta la aplicación:

```plaintext
mvn spring-boot:run
```
La API estará disponible en http://localhost:8080.

Accede a la documentación de Swagger en:
```plaintext
http://localhost:8080/swagger-ui/index.html
```

## Endpoints

Gestión de Eventos

- GET /api/eventos: Obtener todos los eventos.
- GET /api/eventos/{id}: Obtener un evento por ID.
- POST /api/eventos: Crear un nuevo evento.
- PUT /api/eventos/{id}: Actualizar un evento existente.
- DELETE /api/eventos/{id}: Eliminar un evento.
  
## Notificaciones

- POST /api/eventos/{id}/notificar: Enviar una notificación sobre un evento.

## Documentación
- GET /swagger-ui/index.html: Documentación interactiva de los endpoints.
  
## Base de Datos

El esquema principal incluye las siguientes tablas:

- eventos:
    id: Identificador único.
    tipo: Tipo del evento.
    encargado: Persona responsable del evento.
    fechaHora: Fecha y hora del evento.
    ubicacion: Ubicación del evento.
  
## Script de inicialización

```plaintext
CREATE TABLE eventos (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(255) NOT NULL,
    encargado VARCHAR(255) NOT NULL,
    fechaHora TIMESTAMP NOT NULL,
    ubicacion VARCHAR(255) NOT NULL
);
```

## Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.
