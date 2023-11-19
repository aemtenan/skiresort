# Ski Resort Booking System API

## Introduction 
In this project, we implement the API for the booking system of a Ski Resort chain.

### Functional Requirements:
* An admin can add a new ski resort to the system
* An admin can retrieve/update/delete the information of the ski resort
* An admin can add/update/retrieve/delete accommodation information for a  ski resort
* An admin can add/update/retrieve/delete accommodation type information for a particular ski resort
* A user can make a booking for accommodation at a ski resort
* A user can retrieve saved bookings

### Tools Used:
* Environment: OpenJDK 21
* Framework: Spring Boot 3.1.5
* Database: H2 in-memory db
* Technologies: RESTful API, JPA for Data Access Layer

### Instructions:
* Clone repository
* If you have Maven installed, use 'mvn spring-boot:run'

## Design

### Database Schema
Based on the requirements above, we identify five entities: resorts, accommodations, accommodationtypes, users and bookings.
Their attributes relationships are captured in the following Entity Relationship diagram.

![alt text](https://github.com/aemtenan/skiresort/blob/main/src/main/resources/static/ski-resort-er.png?raw=true)

* Resorts: Defines information about each ski resort.
* Accommodationtype: Defines the types of accommodations available at each ski resort.
* Accommodation: Represents individual accommodation units. Each unit is associated with a specific resort and accommodationtype.
* User: Represents a logged in user.
* Booking: Represents a booking made by a user. Each booking is associated with a user and an accommodation unit.

## Todo

* Add Admin and Non-admin roles to User.
* Add Unit tests.

## Enhancements for Production

* **PostgreSQL**: For use in production, we should use a production grade database such as PostgreSQL instead of an in-memory database.
* **Caching**: Since we are using an in-memory database, we do not need caching. However once we use PostgreSQL in production, we would need to implement caching using Redis for example to improve performance.
* **Security**: To keep the codebase simple, we did not implement authentication and authorization. For use in production, this needs to be added using a library such as Spring Security.
* **Retry**: Add support to automatically retry a failed operation using a library such as Spring Retry.
* **Kubernetes**: Containerize the application and set it up for deployment in a Kubernetes cluster.

## API Guide

### Resort API 

| Action              | Method | Path                                   | Body                                      | HTTP response |
|---------------------|--------|----------------------------------------|-------------------------------------------|---------------|
| Add a ski resort    | POST   | http://localhost:8080/api/v2/resorts   | {"name": "Snowy Hills","town": "Wiarton"} | 201           |
| Get a ski resort    | GET    | http://localhost:8080/api/v2/resorts/1 |                                           | 200           |
| Get all ski resorts | GET    | http://localhost:8080/api/v2/resorts   |                                           | 200           |
| Update a ski resort | PUT    | http://localhost:8080/api/v2/resorts/1 | {"name": "Windy Hills","town": "Wiarton"} | 200           |
| Delete a ski resort | DELETE | http://localhost:8080/api/v2/resorts/1 |                                           | 204           |

### AccommodationType API

| Action                       | Method | Path                                              | Body                                        | HTTP response |
|------------------------------|--------|---------------------------------------------------|---------------------------------------------|---------------|
| Add an accommodation type    | POST   | http://localhost:8080/api/v2/accommodationtypes   | {"name": "cabin","rate": 100, "capacity":5} | 201           |
| Get an accommodation type    | GET    | http://localhost:8080/api/v2/accommodationtypes/1 |                                             | 200           |
| Update an accommodation type | PUT    | http://localhost:8080/api/v2/accommodationtypes/1 | {"name": "room","rate": 85, "capacity":2}   | 200           |
| Delete an accommodation type | DELETE | http://localhost:8080/api/v2/accommodationtypes/1 |                                             | 204           |

### Accommodation API 

| Action                                                                           | Method | Path                                                  | Body                  | HTTP response |
|----------------------------------------------------------------------------------|--------|-------------------------------------------------------|-----------------------|---------------|
| Add an accommodation for a particular resort with a particular accommodation type| POST   | http://localhost:8080/api/v2/resorts/1/accommodationtypes/1/accommodations| {"occupied": "false"} | 201           |
| Get all accommodations for a particular resort                                   | GET    | http://localhost:8080/api/v2/resorts/1/accommodations |                       | 200           |
| Get a particular accommodation                                                   | GET    | http://localhost:8080/api/v2/accommodations/1         |                       | 200           |
| Update a particular accommodation                                                | PUT    | http://localhost:8080/api/v2/accommodations/1         | {"occupied": "true"}  | 200           |
| Delete a particular accommodation                                                | DELETE | http://localhost:8080/api/v2/accommodations/1         |                       | 204           |
| Del all accommodations for a particular resort                                   | DELETE | http://localhost:8080/api/v2/resorts/1/accommodations |                       | 204           |

### User API

| Action        | Method | Path                                 | Body                                               | HTTP response |
|---------------|--------|--------------------------------------|----------------------------------------------------|---------------|
| Add a user    | POST   | http://localhost:8080/api/v2/users   | {"userName": "user1","email": "user1@example.com"} | 201           |
| Get a user    | GET    | http://localhost:8080/api/v2/users/1 |                                                    | 200           |
| Update a user | PUT    | http://localhost:8080/api/v2/users/1 | {"userName": "user2","email": "user2@example.com"} | 200           |
| Delete a user | DELETE | http://localhost:8080/api/v2/users/1 |                                                    | 204           |

### Booking API

| Action                      | Method | Path                                                           | Body                                                                         | HTTP response |
|-----------------------------|--------|----------------------------------------------------------------|------------------------------------------------------------------------------|---------------|
| Add a booking               | POST   | http://localhost:8080/api/v2/users/1/accommodations/1/bookings | {"checkInDate": "2024-04-28T13:30:00","checkOutDate": "2024-04-29T11:00:00"} | 201           |
| Get all bookings for a user | GET    | http://localhost:8080/api/v2/users/1/bookings                  |                                                                              |               |
| Get a booking               | GET    | http://localhost:8080/api/v2/bookings/1                        |                                                                              | 200           |
| Update a booking            | PUT    | http://localhost:8080/api/v2/bookings/1                        | {"checkInDate": "2024-04-27T13:30:00","checkOutDate": "2024-04-28T11:00:00"} | 200           |
| Delete a booking            | DELETE | http://localhost:8080/api/v2/bookings/1                        |                                                                              | 204           |