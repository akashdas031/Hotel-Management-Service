# Hotel Management Microservices Application

## Overview

The *Hotel Management Microservices Application* is a comprehensive system designed to manage various aspects of hotel services, including users, hotels, menus, staff, items, and ratings. The architecture of the application follows microservice principles, leveraging *Spring Boot, **Spring Security (Okta OAuth2), **Netflix Eureka* for service registry, and *RestTemplate* and *FeignClient* for inter-service communication. The application also uses an *API Gateway* for centralized routing and other essential features.

### Key Features
- *Microservice Architecture*: Each module (User, Hotel, Menu, Staff, Items, Rating) operates as a separate service, ensuring scalability, independent deployment, and fault isolation.
- *Security: Implemented using **Spring Security* with *Okta OAuth2* for authentication and role-based authorization.
- *Service Discovery: Managed via **Netflix Eureka Server*, which helps services to discover and communicate with each other dynamically.
- *API Gateway*: Centralized routing and security layer for all the services.
- *Inter-service Communication: Achieved using **RestTemplate* and *FeignClient* to make HTTP calls between services.
- *Databases*: Different modules use different databases for optimized performance:
  - *MS SQL*: For the User module.
  - *PostgreSQL*: For the Hotel, Staff, and Rating modules.
  - *MongoDB*: For the Menu and Items modules.
- *Rating Service*: Allows users to provide ratings and reviews for hotels and their services.
- *Other Features*: Integration with external services, load balancing, distributed logging, and monitoring.

---

## Architecture Overview

### Modules

1. *User Service*
   - Manages user authentication, registration, and profile management.
   - Integrates with *MS SQL* for storing user data.
   - Provides role-based access control with *Spring Security* and *Okta OAuth2*.

2. *Hotel Service*
   - Handles hotel-related information such as details, rooms, availability, and ratings.
   - Uses *PostgreSQL* for storing hotel information.
   - Interacts with the *Rating Service* and other services (e.g., Menu, Staff) via *FeignClient*.

3. *Menu Service*
   - Provides the ability to manage hotel menus, dishes, and food categories.
   - Utilizes *Postgre* for handling menu and item data.
   - Supports CRUD operations for items.

4. *Staff Service*
   - Manages hotel staff information such as roles, shifts, and salaries.
   - Uses *PostgreSQL* for persistence.
   - Includes endpoints for managing staff roles and assignments.

5. *Items Service*
   - Handles inventory of items used in the hotel, such as amenities, supplies, and restaurant stock.
   - Stores item details in *Postgres* for flexible data storage.
   - Provides APIs to manage and track item usage and availability.

6. *Rating Service*
   - Enables users to provide ratings and reviews for hotels.
   - Interacts with the *Hotel Service* to aggregate ratings and display average scores.
   - Uses *Mongodb* for storing user ratings and reviews.
   - Allows users to fetch and manage their previous ratings.
   
   *Key Endpoints*:
   - POST /ratings: Submit a new rating for a hotel.
   - GET /ratings/{hotelId}: Fetch ratings and reviews for a specific hotel.
   - PUT /ratings/{id}: Update a user’s rating.
   - DELETE /ratings/{id}: Remove a rating.

### Cross-cutting Features

- *Spring Security & Okta OAuth2*
  - Centralized security using Okta OAuth2, providing authentication and authorization across all microservices.
  - Role-based access control implemented at both API Gateway and microservice levels.
  
- *Netflix Eureka*
  - Service registry for dynamic service discovery.
  - Ensures load balancing and failure recovery among microservices.
  
- *API Gateway*
  - Provides a single entry point for client requests.
  - Performs request routing, load balancing, and authentication via *Spring Security*.

- *RestTemplate and FeignClient*
  - *RestTemplate* for simple, synchronous communication between microservices.
  - *FeignClient* for declarative RESTful service calls, enhancing inter-service communication.

---

## Technologies Used

### Backend
- *Spring Boot*
- *Spring Security*
- *Okta OAuth2*
- *Netflix Eureka (Service Discovery)*
- *Spring Cloud API Gateway*
- *RestTemplate and FeignClient*
- *Microservice architecture*

### Databases
- *MS SQL* (for User Service)
- *PostgreSQL* (for Hotel, Staff, and Rating Services)
- *MongoDB* (for Menu and Items Services)

---

## Setup Instructions

### Prerequisites
- *JDK 17+*
- *Maven 3.6+
- *Okta Developer Account* (for OAuth2 authentication)
- *Eureka Server* (for service registry)

### Steps to Run the Application

1. *Clone the Repository*:
   bash
   git clone https://github.com/your-repo/hotel-management-microservices.git
   cd hotel-management-microservices
   

2. *Configure Okta OAuth2*:
   - Create an *Okta* developer account.
   - Set up an application in Okta and configure OAuth2 credentials in application.yml for each service.

3. *Run the Eureka Server*:
   - Navigate to the eureka-server directory and run:
     bash
     mvn spring-boot:run
     

4. *Run Individual Microservices*:
   - For each service (User, Hotel, Menu, Staff, Items, Rating), navigate to the corresponding directory and run:
     bash
     mvn spring-boot:run
     

5. *Access the API Gateway*:
   - Open your browser and go to:
     
     http://localhost:8080
     

---

## API Endpoints

### User Service
- POST /users/register: Register a new user.
- POST /users/login: User login.
- GET /users/profile: Get user profile details.

### Hotel Service
- GET /hotels: List all hotels.
- POST /hotels: Add a new hotel.
- PUT /hotels/{id}: Update hotel details.
  
### Menu Service
- GET /menus/{hotelId}: Get menu for a specific hotel.
- POST /menus/{hotelId}/items: Add a new item to the menu.

### Staff Service
- GET /staff: List all staff members.
- POST /staff: Add a new staff member.

### Items Service
- GET /items: Get all items in the inventory.
- POST /items: Add a new item to inventory.

### Rating Service
- POST /ratings: Submit a rating for a hotel.
- GET /ratings/{hotelId}: Get all ratings for a specific hotel.
- PUT /ratings/{id}: Update an existing rating.
- DELETE /ratings/{id}: Delete a rating.

---

## Future Enhancements

- *CI/CD Integration*: Set up Jenkins pipelines for automated builds and deployments.
- *Caching*: Introduce Redis for caching frequently accessed data.
- *Monitoring*: Integrate with Prometheus and Grafana for real-time service monitoring.
- *Kubernetes*: Deploy the microservices in a Kubernetes cluster for better scalability and resilience.

---

## Conclusion

The Hotel Management Microservices Application is a scalable and flexible system designed with modern architecture principles. With its use of various databases, security mechanisms, and microservice components, it can handle the complexities of managing a hotel system efficiently, with the added feature of user ratings to enhance user engagement and feedback.
