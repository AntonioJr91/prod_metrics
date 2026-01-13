# Prod Metrics API
A RESTful API built with Java and Spring Boot for managing employees, products, and production data, including aggregated reports and pagination.
This project does not aim to be feature-complete and does not include full validations or extensive test coverage.
Its primary purpose is to demonstrate CI/CD pipelines with GitHub Actions, containerization with Docker, and deployment using Kubernetes.
The application serves as a practical example for cloud-native delivery workflows, focusing on infrastructure and automation rather than business complexity.

# Project Goal
Provide a backend service to manage industrial production data, enabling:

* Employee and product management
* Production records by period (monthly / yearly)
* Aggregated production reports
* Efficient pagination and sorting
* Containerized and cloud-ready deployment

# Key Concepts Demonstrated
* Layered architecture (Controller, Service, Repository)
* Domain-Driven Design (DDD – tactical level)
* Domain validations inside entities
* DTOs and Mappers to isolate API contracts
* Pagination with Spring Data
* Global exception handling
* Transaction management
* Cloud-ready infrastructure setup

# Tech Stack
* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* Jakarta Validation
* MySQL (production)
* H2 (tests)
* Maven
* Docker / Docker Compose
* Kubernetes (YAML manifests)

# Endpoints
### Employees
```
GET    /api/employees
GET    /api/employees/{id}
POST   /api/employees
DELETE /api/employees/{id}
```

### Products 
```
GET    /api/products
GET    /api/products/{id}
POST   /api/products
DELETE /api/products/{id}
```

### Productions
```
GET    /api/productions
GET    /api/productions/{id}
POST   /api/productions
DELETE /api/productions/{id}
```

### Reports
```
GET /api/productions/reports
```

Available filters:
* Year (required)
* Month (optional)
* Employee
* Product

# Pagination
The API leverages Pageable from Spring Data, providing full pagination and sorting support.

Example request:
```
GET /api/productions/reports?year=2024&page=0&size=20
```

Example response:
```json
{
  "year": 2024,
  "month": null,
  "total": 0,
  "items": {
    "content": [],
    "totalElements": 0,
    "totalPages": 0,
    "number": 0,
    "size": 20,
    "first": true,
    "last": true
  }
}
```

# Error Handling
Centralized error handling via @RestControllerAdvice.

| Status | Description                   |
| ------ | ----------------------------- |
| 400    | Domain validation error       |
| 404    | Resource not found            |
| 409    | Conflict / duplicate resource |
| 500    | Unexpected internal error     |

Standard error response format:
```json
{
  "status": 400,
  "message": "Error description",
  "path": "/api/endpoint",
  "timestamp": "2024-01-01T10:00:00"
}
```

# Testing
* Unit tests focused on the domain layer
* Validation of business rules and invariants
* In-memory database (H2) for test profile

# Deployment & Infrastructure
The application is ready for modern deployment environments:
* Dockerfile
* docker-compose.yml
* Kubernetes
  * Application Deployment and Service
  * MySQL Deployment and Service

# Project Highlights
* Clean and maintainable codebase
* Rich domain model with encapsulated validations
* Consistent REST API design
* Efficient reporting and pagination
* CI/CD and cloud-ready structure
