# Alura Forum — Spring Boot

This project was developed during a Spring Boot training course at Caelum, taught by the author of this repository and implemented alongside the students throughout the classes.

The application simulates a forum back end: authenticated users can create topics and post answers, while the API organizes content by course and category.

## Key features

- REST API for authentication, topics, answers, and the dashboard;
- stateless authentication with Spring Security and JWT;
- queries with filtering, pagination, and sorting;
- data validation and centralized error handling;
- asynchronous email notifications when topics receive answers;
- a scheduled task and an administrative report built with Thymeleaf;
- interactive API documentation with Swagger;
- initial demo data loaded at startup.

## Technologies

- Java 8 and Spring Boot 2.2.4;
- Spring MVC, Data JPA, Security, Validation, and Mail;
- MySQL and H2 for testing;
- Thymeleaf, Swagger 2, and JWT;
- Maven and JUnit.

## Running the project

You need Java 8 and a running MySQL instance. Before starting the application, review the database connection and the other settings in the `application*.properties` files.

```bash
./mvnw spring-boot:run
```

Once the application is available at `http://localhost:8080`, you can access the interactive documentation at:

```text
http://localhost:8080/swagger-ui.html
```

To run the tests:

```bash
./mvnw test
```

> This is an educational project. The default configuration uses `ddl-auto=create-drop`, so the tables and data are recreated on every run. It should not be used in production without the appropriate changes.
