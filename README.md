# invoice-service

## Description
The Spring invoice-service project uses the following spring modules :
1. Data JPA
2. Web MVC
3. H2
4. Actuator
5. Swagger

## Installation

1. Clone the repository from GitHub : https://github.com/C0d3N1nJ4/invoice-service.git
2. Open the project in your IDE
3. Run the following command in the root directory of the project to build the project : `mvn clean install`
4. Run the project as a Spring Boot Application
5. The application will be available at the following URL : http://localhost:8080/invoices
6. The swagger documentation is available at the following URL : http://localhost:8080/swagger-ui.html
7. The actuator endpoints are available at the following URL : http://localhost:8080/actuator
8. The H2 console is available at the following URL : http://localhost:8080/h2-console
9. The database is available at the following URL : jdbc:h2:mem:testdb
10. The database credentials are : 
    - username : sa
    - password : password
