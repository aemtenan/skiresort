# Ski Resort Booking System API
 In this project, we implement the API for the booking system of a Ski Resort chain.
 
Functional Requirements:
* An admin can add a new ski resort to the system
* An admin can retrieve/update/delete the information of the ski resort
* An admin can add/update/retrieve/delete accommodation information for a  ski resort
* An admin can add/update/retrieve/delete accommodation type information for a particular ski resort
* A user can make a booking for one or more accomodations at a ski resort

Tools Used:
* Environment: OpenJDK 21
* Framework: Spring Boot 3.1.5
* Database: H2 in-memory db
* Technologies: RESTful API, JPA for Data Access Layer

Usage:
* Clone repository
* If you have Maven installed, use 'mvn spring-boot:run'

Resort API Guide:

| Action              | Method | Path                                   | Body                                      | HTTP response |
|---------------------|--------|----------------------------------------|-------------------------------------------|---------------|
| Add a ski resort    | POST   | http://localhost:8080/api/v2/resorts   | {"name": "Snowy Hills","town": "Wiarton"} | 201           |
| Get a ski resort    | GET    | http://localhost:8080/api/v2/resorts/1 |                                           | 200           |
| Get all ski resorts | GET    | http://localhost:8080/api/v2/resorts   |                                           | 200           |
| Update a ski resort | PUT    | http://localhost:8080/api/v2/resorts/1 | {"name": "Windy Hills","town": "Wiarton"} | 200           |
| Delete a ski resort | DELETE | http://localhost:8080/api/v2/resorts/1 |                                           | 204           |

Accomodation API Guide:

| Action                                         | Method | Path                                                  | Body                  | HTTP response |
|------------------------------------------------|--------|-------------------------------------------------------|-----------------------|---------------|
| Add an accommodation for a particular resort   | POST   | http://localhost:8080/api/v2/resorts/1/accommodations | {"occupied": "false"} | 201           |
| Get all accommodations for a particular resort | GET    | http://localhost:8080/api/v2/resorts/1/accommodations |                       | 200           |
| Get a particular accommodation                 | GET    | http://localhost:8080/api/v2/accommodations/1         |                       | 200           |
| Update a particular accommodation              | PUT    | http://localhost:8080/api/v2/accommodations/1         | {"occupied": "true"}  | 200           |
| Delete a particular accommodation              | DELETE | http://localhost:8080/api/v2/accommodations/1         |                       | 204           |
| Del all accommodations for a particular resort | DELETE | http://localhost:8080/api/v2/resorts/1/accommodations |                       | 204           |
