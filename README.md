# Code Craftery Server Side
This project contains the server-side code for the CodeCraftery website, providing APIs and backend services for the client-side application.

## Configuration

1. Create a Properties File:
   Create a new file named `application.properties` in the `src/main/resources` directory of the project.

2. **Add Configuration Properties:**
   Open the `application.properties` file and add the necessary configuration properties. 

spring.jpa.hibernate.ddl-auto=update

spring.datasource.url=jdbc:mysql://localhost:3306/database-name

spring.datasource.username=user

spring.datasource.password=password

#spring.jpa.show-sql: true

- Replace 'database-name','user' and 'password' with your actual database name, username and password.

### Overview
The Code Craftery Server Side project is built using Java Spring Boot and MySQL database. It provides RESTful APIs for interacting with the client-side application, handling data storage, retrieval, and validation.

### Technologies Used
- Java Spring Boot - Framework for building Java-based applications
- Spring Data JPA - Provides support for working with relational databases using JPA
- Spring Web - Provides features for building web applications
- MySQL - Open-source relational database management system
- Lombok - Library to reduce boilerplate code in Java classes
- Spring Boot Starter Test - Starter for testing Spring Boot applications
- Spring Boot Starter Validation - Starter for validating data in Spring Boot applications
- Commons IO - Utility classes for working with IO in Java
- Spring Security Crypto - Provides functionalities for password encryption, hashing, and key generation, enhancing security in Spring applications.

### Development
This project is under active development. 
