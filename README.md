
# Ship and Cargo Backend Platform
## Overview
The Ship and Cargo Backend Platform is a backend solution designed to streamline and optimize the process of matching dry bulk cargo with available ships. Built on Java Spring Boot with MyBatis for data access, and MySQL as the relational database, the platform addresses the complexities and unique demands of dry bulk cargo management at ports, offering users efficient and intelligent cargo-to-ship matching.

## Features
Cargo and Ship Management: Allows users to add and manage both dry bulk cargo and ships, creating a centralized database of available transportation options.
Advanced Matching Algorithms: Presents multiple optimal transportation options based on cargo requirements and ship availability, enabling users to make the best logistical choices.
Microservices Architecture: Utilizes a microservices approach to invoke API calls, which enhances scalability, flexibility, and performance across various platform functions.
Industry Support: Developed in collaboration with and supported by Cosco, a leader in global shipping, ensuring the platform meets industry standards and expectations.

## Technologies Used
Java Spring Boot: Provides a robust framework for building production-ready applications with embedded server support and dependency injection.
MyBatis: Used for flexible, SQL-based data mapping and integration, allowing efficient database connectivity.
MySQL: Relational database management system, providing a reliable and scalable solution for data storage and retrieval.

# Getting Started
## Prerequisites
Java 11+
MySQL 8.0+
Maven (for dependency management)
Installation
Clone the repository:

`bash
Copy code
git clone https://github.com/your-username/ship-cargo-backend.git
cd ship-cargo-backend`

## Configure the Database:

Set up a MySQL database and update the database credentials in the application.properties file:
properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
Build and Run the Application:

Compile and run the application using Maven:
bash
Copy code
mvn spring-boot:run
Accessing the Platform:

The application will be available at http://localhost:8080.
API Endpoints
Add Cargo: /api/cargo/add - Endpoint to add new cargo information.
Add Ship: /api/ship/add - Endpoint to add new ship information.
Match Cargo and Ship: /api/match - Endpoint to receive optimal matching options for available cargo and ships.
Example Usage
After starting the application, you can test the API endpoints using tools like Postman or curl.

bash
Copy code
curl -X POST http://localhost:8080/api/cargo/add -d '{"cargoType": "Iron Ore", "quantity": 50000}'
curl -X POST http://localhost:8080/api/ship/add -d '{"shipName": "Ocean Carrier", "capacity": 100000}'
curl -X GET http://localhost:8080/api/match
Architecture
The platform is built with a microservices architecture approach, which modularizes different functionalities into separate services that can be independently scaled and managed. This architecture not only improves agility but also simplifies maintenance and future expansion.

Support and Contributions
This project is actively supported by Cosco. Contributions to improve matching algorithms, extend functionalities, or optimize performance are welcome!

Fork the repository.
Create a new feature branch.
Commit your changes and submit a pull request.
License
This project is licensed under the MIT License.
