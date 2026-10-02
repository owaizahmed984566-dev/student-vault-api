# 🎓 Student Vault API

### Production-Oriented Student Management REST API

A secure and production-oriented RESTful backend application built with **Java 25 and Spring Boot 4.1.1**. The project demonstrates practical backend engineering concepts including REST API design, Spring Data JPA, Hibernate, JWT authentication, role-based authorization, validation, exception handling, automated testing, Docker, MySQL, OpenAPI/Swagger, and cloud deployment.

<p align="center">

![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?style=for-the-badge&logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-59666C?style=for-the-badge&logo=hibernate)
![MySQL](https://img.shields.io/badge/MySQL-8.4-4479A1?style=for-the-badge&logo=mysql)
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?style=for-the-badge&logo=docker)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven)
![Railway](https://img.shields.io/badge/Railway-Deployed-000000?style=for-the-badge&logo=railway)

</p>

<p align="center">

<a href="https://student-vault-api-production.up.railway.app">
<img src="https://img.shields.io/badge/🌐%20Live%20API-Visit%20Application-success?style=for-the-badge">
</a>

<a href="https://student-vault-api-production.up.railway.app/swagger-ui/index.html">
<img src="https://img.shields.io/badge/📚%20Swagger-API%20Documentation-blue?style=for-the-badge">
</a>

<img src="https://img.shields.io/badge/Tests-33%2F33%20Passing-brightgreen?style=for-the-badge">

</p>

---

## 🚀 Live Application

**Production API:**  
https://student-vault-api-production.up.railway.app

**Swagger UI:**  
https://student-vault-api-production.up.railway.app/swagger-ui/index.html

The application is deployed on **Railway** and connected to a **MySQL production database**.

---

# 📌 Overview

Student Vault API is a secure backend system for managing student records through RESTful APIs.

The project was built to understand how a real backend application is structured beyond basic CRUD operations, covering the complete backend development lifecycle:

- RESTful API development
- Layered architecture
- Database persistence
- DTO-based request and response handling
- Input validation
- Global exception handling
- JWT authentication
- Role-based access control
- Automated testing
- Logging
- API documentation
- Containerization
- Environment-based configuration
- Cloud deployment

---

# ✨ Features

## 👨‍🎓 Student Management

- Create student records
- Retrieve all students
- Retrieve a student by ID
- Update student information
- Partially update student information
- Delete student records
- Check student existence

## 🔐 Authentication & Security

- User registration
- User login
- BCrypt password hashing
- JWT-based authentication
- Role-based authorization
- Protected API endpoints
- Proper `401 Unauthorized` handling
- Proper `403 Forbidden` handling

## ✅ Validation & Error Handling

- Request validation using Jakarta Bean Validation
- Global exception handling
- Custom exceptions
- Consistent API error responses
- Invalid request handling
- Invalid ID/type handling

## 📚 API Documentation

- OpenAPI 3 documentation
- Swagger UI
- JWT Bearer authentication support
- Interactive API testing

## 🧪 Testing

The project includes:

- Service layer unit tests
- Controller layer tests
- Security tests
- Integration tests

### Current Test Results

    StudentServiceTest          11/11
    StudentControllerTest       14/14
    Security Tests                4/4
    Integration Tests             3/3
    -----------------------------------
    Total                        33/33

## 🐳 Deployment & DevOps

- Dockerized application
- Docker Compose development environment
- MySQL container
- Environment-based configuration
- Production Spring profile
- Railway cloud deployment
- Production MySQL database

---

# 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java 25 | Programming language |
| Spring Boot 4.1.1 | Backend framework |
| Spring Web | REST API development |
| Spring Data JPA | Data access |
| Hibernate | ORM |
| MySQL 8.4 | Relational database |
| Spring Security | Authentication & authorization |
| JWT | Stateless authentication |
| BCrypt | Password hashing |
| Maven | Build & dependency management |
| JUnit | Testing |
| Mockito | Mock-based unit testing |
| OpenAPI / Swagger | API documentation |
| Docker | Containerization |
| Docker Compose | Multi-container development |
| Railway | Cloud deployment |
| Git & GitHub | Version control |

---

# 🏗️ Architecture

The application follows a layered architecture:

    Client
       |
       v
    Controller Layer
       |
       v
    Service Layer
       |
       v
    Repository Layer
       |
       v
    MySQL Database

### Security Flow

    Client
       |
       v
    JWT Authentication Filter
       |
       v
    Spring Security
       |
       v
    Controller
       |
       v
    Service
       |
       v
    Repository
       |
       v
    MySQL

---

# 📂 Project Structure

    src/main/java/com/owaizz/studentapi
    │
    ├── config
    │   └── OpenApiConfig.java
    │
    ├── controller
    │   ├── StudentController.java
    │   └── AuthController.java
    │
    ├── service
    │   ├── StudentService.java
    │   └── AuthService.java
    │
    ├── repository
    │   ├── StudentRepository.java
    │   └── UserRepository.java
    │
    ├── entity
    │   ├── Student.java
    │   └── User.java
    │
    ├── dto
    │   ├── StudentRequest.java
    │   ├── StudentResponse.java
    │   ├── StudentPatchRequest.java
    │   ├── MessageResponse.java
    │   ├── RegisterRequest.java
    │   └── LoginRequest.java
    │
    ├── exception
    │   ├── StudentNotFoundException.java
    │   ├── InvalidStudentException.java
    │   └── GlobalExceptionHandler.java
    │
    └── security
        ├── SecurityConfig.java
        ├── MyUserDetailsService.java
        ├── JwtService.java
        ├── JwtAuthenticationFilter.java
        └── Role.java

---

# 🔑 API Endpoints

## Authentication

### Register

    POST /auth/register

Example request:

    {
      "username": "owaiz",
      "password": "your-password"
    }

### Login

    POST /auth/login

Example request:

    {
      "username": "owaiz",
      "password": "your-password"
    }

A successful login returns a JWT token.

Use the returned token for protected endpoints:

    Authorization: Bearer <JWT_TOKEN>

---

# 👨‍🎓 Student Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/students` | Get all students |
| `GET` | `/students/{id}` | Get student by ID |
| `POST` | `/students` | Create a student |
| `PUT` | `/students/{id}` | Fully update a student |
| `PATCH` | `/students/{id}` | Partially update a student |
| `DELETE` | `/students/{id}` | Delete a student |

---

## Create Student

    POST /students

Example request:

    {
      "id": 1,
      "name": "Rahul",
      "subject": "Java",
      "marks": 85
    }

## Partially Update Student

    PATCH /students/{id}

Example request:

    {
      "marks": 92
    }

Only the supplied fields are updated.

---

# 🔐 Authentication Flow

The application uses JWT-based stateless authentication.

    1. User registers
            |
            v
    2. Password is securely hashed using BCrypt
            |
            v
    3. User logs in
            |
            v
    4. Server authenticates credentials
            |
            v
    5. Server generates JWT
            |
            v
    6. Client sends JWT with protected requests
            |
            v
    7. JWT Authentication Filter validates token
            |
            v
    8. Spring Security establishes authentication
            |
            v
    9. Request reaches protected endpoint

---

# 🌐 HTTP Status Handling

The API follows standard HTTP status semantics.

| Status | Meaning |
|---|---|
| `200 OK` | Request completed successfully |
| `201 Created` | Resource created successfully |
| `400 Bad Request` | Invalid request or validation failure |
| `401 Unauthorized` | Authentication is missing or invalid |
| `403 Forbidden` | User is authenticated but not authorized |
| `404 Not Found` | Requested resource does not exist |
| `500 Internal Server Error` | Unexpected server-side error |

---

# ⚙️ Configuration

The application uses Spring profiles for environment-specific configuration.

    application.properties
    application-dev.properties
    application-prod.properties

Development configuration uses a local MySQL database.

Production configuration uses environment variables for sensitive values.

Example:

    spring.datasource.url=${DB_URL}
    spring.datasource.username=${DB_USERNAME}
    spring.datasource.password=${DB_PASSWORD}

    jwt.secret=${JWT_SECRET}

Sensitive values are not stored directly in the repository.

---

# 💻 Running Locally

## Prerequisites

Install:

- Java 25
- Maven
- MySQL
- Git

Optional:

- Docker Desktop
- IntelliJ IDEA

## 1. Clone the Repository

    git clone https://github.com/owaizahmed984566-dev/student-vault-api.git

    cd student-vault-api

## 2. Create the Database

Create a MySQL database:

    CREATE DATABASE college;

## 3. Configure Development Properties

Update:

    src/main/resources/application-dev.properties

Example:

    spring.datasource.url=jdbc:mysql://localhost:3306/college
    spring.datasource.username=root
    spring.datasource.password=YOUR_MYSQL_PASSWORD

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true

    jwt.secret=YOUR_JWT_SECRET

Do not commit real passwords or secrets.

## 4. Run the Application

Using Maven:

    mvn spring-boot:run

Or run the main Spring Boot application from IntelliJ IDEA.

The application starts on:

    http://localhost:8080

---

# 🐳 Running With Docker

Build the Docker image:

    docker build -t student-api .

Run the application:

    docker run -d --name student-api-container -p 8080:8080 student-api

---

# 🐳 Running With Docker Compose

The project includes Docker Compose configuration for running the application together with MySQL.

Start the services:

    docker compose up --build

This starts:

    Spring Boot Application
            |
            v
         MySQL

To stop the containers:

    docker compose down

The MySQL database uses a Docker volume so that database data can persist across normal container shutdowns.

---

# 📖 Swagger / OpenAPI

### Local

    http://localhost:8080/swagger-ui/index.html

### Production

    https://student-vault-api-production.up.railway.app/swagger-ui/index.html

Swagger provides:

- Complete endpoint documentation
- Request/response schemas
- Interactive API testing
- JWT Bearer authentication

---

# 🧪 Testing

The project contains tests covering multiple application layers.

### Current Test Results

    StudentServiceTest          11/11
    StudentControllerTest       14/14
    Security Tests                4/4
    Integration Tests             3/3
    -----------------------------------
    Total                        33/33

The tests cover:

- Service behavior
- Controller behavior
- Authentication
- Authorization
- JWT security
- HTTP status handling
- Integration between application components

Run the complete test suite:

    mvn test

---

# 🐳 Docker Architecture

For containerized development, Docker Compose runs two services:

    ┌─────────────────────────────┐
    │        Spring Boot App      │
    │          Port 8080          │
    └──────────────┬──────────────┘
                   │
                   │ JDBC
                   v
    ┌─────────────────────────────┐
    │          MySQL 8.4          │
    │          Port 3306          │
    └─────────────────────────────┘

The application connects to MySQL using the Docker service name:

    mysql

instead of:

    localhost

---

# ☁️ Production Deployment

The application is deployed on Railway.

### Production Architecture

                    Internet
                       |
                       v
              ┌─────────────────┐
              │     Railway     │
              └────────┬────────┘
                       |
                       v
              ┌─────────────────┐
              │  Spring Boot    │
              │  Student API    │
              └────────┬────────┘
                       |
                       v
              ┌─────────────────┐
              │  Railway MySQL  │
              └─────────────────┘

The production environment uses:

- GitHub-based deployment
- Spring `prod` profile
- Environment variables
- Railway-hosted MySQL
- HTTPS
- Docker-based deployment

---

# 🛡️ Security Considerations

The project follows several backend security practices:

- Passwords are stored using BCrypt hashing.
- Authentication is handled using JWT.
- Protected endpoints require authentication.
- Role-based authorization is implemented.
- Database credentials are supplied through environment variables.
- JWT secrets are supplied through environment variables.
- Sensitive `.env` files are excluded from Git using `.gitignore`.

For further production hardening, the project can be extended with database migrations, secret rotation, dedicated database users, monitoring, and additional operational controls.

---

# 🧠 Key Backend Concepts Demonstrated

## Java

- Object-Oriented Programming
- Classes and interfaces
- Exception handling
- Collections
- Dependency management

## Spring

- IoC
- Dependency Injection
- Beans
- Component scanning
- Layered architecture

## Spring Boot

- Auto-configuration
- Spring Boot starters
- Embedded Tomcat
- Profiles
- Configuration management

## Spring Data JPA / Hibernate

- Entities
- Repositories
- ORM
- CRUD operations
- Persistence
- Database interaction

## Spring Security

- Authentication
- Authorization
- JWT
- Password encoding
- Security filters
- Role-based access control

## REST API

- HTTP methods
- HTTP status codes
- Request/response DTOs
- Validation
- Exception handling
- Partial updates

## DevOps / Deployment

- Git
- GitHub
- Docker
- Docker Compose
- Environment variables
- Cloud deployment
- Railway

---

# 🚀 Future Improvements

Possible future improvements include:

- Database migration management using Flyway or Liquibase
- Refresh token mechanism
- Pagination and sorting
- Advanced filtering and searching
- API rate limiting
- Improved API error response structure
- Production monitoring
- Health checks
- CI/CD pipeline
- Automated deployment
- More comprehensive integration tests
- Performance optimization
- API versioning

---

# 🎯 Project Goal

The primary goal of Student Vault API is to build a practical backend application while understanding how individual backend technologies work together in a real application.

Rather than focusing only on CRUD operations, the project explores the complete backend development lifecycle:

    Design
      ↓
    Implementation
      ↓
    Database Integration
      ↓
    Security
      ↓
    Validation
      ↓
    Testing
      ↓
    Documentation
      ↓
    Containerization
      ↓
    Deployment

---

# 👨‍💻 Author

**Owaiz Ahmed**

Computer Science Engineering

### Areas of Interest

- Java Backend Development
- Spring Boot
- REST APIs
- Database Systems
- Cloud & Backend Engineering

---

# 📄 License

This project is intended for educational and portfolio purposes.
