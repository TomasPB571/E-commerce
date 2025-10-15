# 🛒 Aplicación E-commerce con Spring Boot

## 📘 Propósito y Alcance
Este proyecto ofrece una **API REST basada en Spring Boot** que implementa una arquitectura de **mercado electrónico (E-commerce)**.  
Incluye documentación sobre el diseño del sistema, sus componentes principales, subsistemas y la pila tecnológica utilizada.

Está diseñado para:
- Orientar a nuevos desarrolladores en el proyecto.
- Proporcionar una visión general de la arquitectura.
- Servir como punto de entrada para documentación técnica detallada.

Para más información:
- **Inicio Rápido** → Configuración del entorno y ejecución del proyecto.  
- **Arquitectura del Sistema** → Diseño y patrones arquitectónicos.  
- **Documentación de Subsistemas** → Gestión de Usuarios, Clientes y Comerciantes.

---

## 🧩 Descripción General de la Aplicación
La aplicación de E-commerce está estructurada en **tres dominios principales**:

| Dominio | Entidad | Propósito |
|----------|----------|-----------|
| **Gestión de Usuarios** | `UsersModel` | Autenticación y administración de cuentas |
| **Gestión de Clientes** | `Clientes` | Información y perfil del cliente |
| **Gestión de Comerciantes** | `Comerciantes` | Perfiles comerciales o de vendedores |

El diseño es **centrado en el usuario**, donde `UsersModel` actúa como entidad principal y se relaciona mediante claves foráneas con `Clientes` y `Comerciantes`, permitiendo un acceso flexible basado en roles.

---

## 🏗️ Arquitectura del Sistema

### Patrón de Arquitectura en Capas
La aplicación sigue una **arquitectura clásica en capas**, con separación clara de responsabilidades:

```
Controlador → Servicio → Repositorio → Entidad
```

| Capa | Anotación | Responsabilidad |
|-------|------------|-----------------|
| Controlador | `@RestController` | Expone endpoints REST |
| Servicio | `@Service` | Contiene la lógica de negocio |
| Repositorio | `extends JpaRepository` | Gestiona el acceso a datos |
| Entidad | `@Entity` | Representa las tablas de base de datos |

---

## ⚙️ Subsistemas Principales

### 👤 Gestión de Usuarios
Maneja la autenticación y la administración de cuentas.

| Componente | Ruta | Responsabilidades Clave |
|-------------|------|--------------------------|
| **Entidad** | `models/UsersModel.java` | Define campos: `idUser`, `firstName`, `lastName`, `email`, `password`, `estado` |
| **Servicio** | `servicies/UsersServicie.java` | CRUD: `saveUser()`, `getAllUsers()`, `getUsersById()`, `deleteUsersById()` |
| **Controlador** | `controllers/UserController.java` | Endpoints bajo `/api/users` |
| **Repositorio** | `repositories/UsersInterface.java` | Capa de acceso a datos con JPA |

---

### 👥 Gestión de Clientes
Administra perfiles de clientes vinculados a usuarios.

| Componente | Descripción |
|-------------|-------------|
| **Entidad** | `Clientes` con campos: `idCliente`, `nombre`, `direccion`, `telefono`, `idUser (FK)` |
| **Endpoint** | `/api/clientes` |
| **Relación** | Muchos a uno con `UsersModel` |

---

### 🏪 Gestión de Comerciantes
Maneja los perfiles comerciales o de vendedores.

| Componente | Descripción |
|-------------|-------------|
| **Entidad** | `Comerciantes` con campos: `idComerciante`, `nombre`, `negocio`, `idUser (FK)` |
| **Endpoint** | `/api/comerciantes` |
| **Relación** | Muchos a uno con `UsersModel` |

---

## 🔁 Flujo de Peticiones
Flujo típico de una solicitud REST:

```
@RequestBody (Controlador)
     ↓
Delegación al Servicio (Lógica de Negocio)
     ↓
Invocación del Repositorio (Acceso a Datos)
     ↓
ResponseEntity (Respuesta HTTP)
```

Usa:
- `Optional<T>` para seguridad ante nulos.  
- `ResponseEntity` para control del estado HTTP.

---

## 🧱 Pila Tecnológica

| Capa | Dependencia | Propósito |
|------|--------------|-----------|
| **Framework** | `spring-boot-starter-web` | API REST, servidor embebido Tomcat |
| **Acceso a Datos** | `spring-boot-starter-data-jpa` | ORM con Hibernate |
| **Base de Datos (Desarrollo)** | `h2` | Base de datos en memoria |
| **Base de Datos (Producción)** | `mysql-connector-j` | Conector MySQL (no activo aún) |
| **Herramientas de Desarrollo** | `spring-boot-devtools` | Recarga automática |
| **Pruebas** | `spring-boot-starter-test` | JUnit, Mockito, Spring Test |

**Compilación:** Maven 3.9.11  
**Versión de Java:** 25  
**Artifact:** `com.example:E-commerce:0.0.1-SNAPSHOT`

---

## 🧾 Configuración de la Aplicación

### Base de Datos (H2)
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### JPA / Hibernate
```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

- `ddl-auto=update`: Actualiza el esquema según las entidades.  
- `show-sql=true`: Muestra las consultas SQL en consola.  

**Consola H2:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

## 📁 Estructura de Archivos
```
src/
 └── main/
     ├── java/com/example/E_commerce/
     │   ├── controllers/
     │   ├── services/
     │   ├── models/
     │   └── repositories/
     └── resources/
         ├── application.properties
         └── static / templates
```

---

## 🧠 Patrones de Diseño Clave

### Inyección de Dependencias
```java
private final UsersServicie usersServicie;

public UserController(UsersServicie usersServicie) {
    this.usersServicie = usersServicie;
}
```

### Patrón Repositorio
```java
public interface UsersInterface extends JpaRepository<UsersModel, Long> {}
```

### Nomenclatura RESTful
| Operación | Endpoint | Descripción |
|------------|-----------|-------------|
| `GET` | `/api/users` | Listar usuarios |
| `GET` | `/api/users/{id}` | Obtener usuario |
| `POST` | `/api/users` | Crear usuario |
| `PUT` | `/api/users/{id}` | Actualizar usuario |
| `DELETE` | `/api/users/{id}` | Eliminar usuario |

---

## 🚧 Estado Actual del Proyecto

| Aspecto | Estado | Nota |
|----------|---------|------|
| Base de Datos | H2 (memoria) | Los datos se reinician al reiniciar |
| Esquema | `ddl-auto=update` | Se actualiza automáticamente |
| Logs SQL | `show-sql=true` | Consultas visibles en consola |
| Consola H2 | Activa | `/h2-console` |
| DevTools | Activo | Reinicio automático |

### ⚠️ Pendiente para Producción
- Capa de seguridad y autenticación  
- Encriptación de contraseñas  
- Validación de entradas  
- Manejo centralizado de errores  
- Configuración MySQL en producción  
- Documentación Swagger/OpenAPI  
- Configuración de logs y perfiles de entorno

---

## 🌐 Endpoints Disponibles

| Endpoint | Métodos | Descripción |
|-----------|----------|-------------|
| `/api/users` | GET, POST, PUT, PATCH, DELETE | Gestión de usuarios |
| `/api/clientes` | GET, POST, PUT, PATCH, DELETE | Gestión de clientes |
| `/api/comerciantes` | GET, POST, PUT, PATCH, DELETE | Gestión de comerciantes |
| `/h2-console` | Web UI | Consola de administración de BD |

---

## 🚀 Ejecución del Proyecto

### Requisitos Previos
- Java 25+  
- Maven 3.9+  
- IDE (IntelliJ, VSCode o NetBeans)

### Ejecución
```bash
mvn spring-boot:run
```
Abrir en navegador: [http://localhost:8080](http://localhost:8080)

### Compilación
```bash
mvn clean package
```

---

## 📚 Documentación Adicional
- Sistema de Gestión de Usuarios  
- Sistema de Gestión de Clientes  
- Sistema de Gestión de Comerciantes  
- Arquitectura del Sistema  
- Referencia de API  

---

## 🧑‍💻 Autor y Licencia
**Autor:** Equipo de Desarrollo – Proyecto E-commerce  
**Framework:** Spring Boot 3.5.6  
**Licencia:** MIT  

---

> _“Diseña con claridad, construye con estructura y despliega con confianza.”_
