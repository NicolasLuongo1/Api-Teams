# Football Teams API

This project is an application that allows users to manage football teams through various operations.

## Features
- **Add a Team**: Create a new football team by providing necessary details such as name, league, and country.
- **Edit a Team**: Update the information of an existing team.
- **Retrieve a Team**: Fetch details of a specific football team by its unique identifier.
- **Retrieve All Teams**: Get a list of all football teams available in the system.
- **Delete a Team**: Remove a football team from the system.
- **Search by Name**: Find teams based on their name.

## Exception Handling
The API utilizes **Aspect-Oriented Programming (AOP)** for exception handling. This approach allows for centralized error handling logic, enhancing the readability and maintainability of the code.


This API provides a comprehensive solution for managing football teams effectively and efficiently.


### Database

This project uses an **H2 in-memory database**. The initial data is persisted for testing purposes.

To access the data, log in at the following link with the credentials shown in the image:

[H2 Database Console](http://localhost:8080/h2-console)

![img_1.png](img_1.png)

### System Security

The system uses **Spring Security** to protect the endpoints and manage authentication and authorization. Authentication is handled through **JSON Web Tokens (JWT)**.

#### Security features implemented:
- **Authentication**: Through JWT, users receive a token upon successful authentication with valid credentials.
- **Authorization**: Tokens are verified on every request, restricting access to resources based on user roles and permissions.
- **Endpoint protection**: Endpoints are secured according to the user roles. Unauthorized users will receive a `403 Forbidden` response when attempting to access protected resources.

#### How to generate and use the JWT token:
1. Authenticate using the login endpoint by providing valid credentials.
2. Receive the JWT token in the response.
3. Include the token in the Authorization header (`Authorization: Bearer <token>`) to access the other protected endpoints.

## Documentation

The project uses Swagger for API documentation, providing an interactive interface to explore and test the available endpoints.

[Swagger](http://localhost:8080/swagger-ui/index.html)


![img.png](img.png)



# Run Spring Boot application with Docker

Compile application:

`mvn clean install`

Create Docker image:

`docker build --tag=teams:latest .`

Run Docker container:

`docker run -p 8080:8080 teams:latest`


# REST Request examples

### Get Team by id

**Curl:**

`curl -X 'GET' \
'http://localhost:8080/team/9' \
-H 'accept: */*' \
-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNzI3NzQxMzU1LCJpYXQiOjE3Mjc3MDUzNTV9.9JziKhhZ9WzLr0BtmANJCvfXPkVLYpqvKpdKerQAX-I'`

**Request URL:**

`http://localhost:8080/team/9`

### Get all Teams

**Curl:**

`curl -X 'GET' \
'http://localhost:8080/team' \
-H 'accept: */*' \
-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNzI3NzQxMzU1LCJpYXQiOjE3Mjc3MDUzNTV9.9JziKhhZ9WzLr0BtmANJCvfXPkVLYpqvKpdKerQAX-I'`

**Request URL:**

http://localhost:8080/team

### Get Team by name

**Curl:**

`curl -X 'GET' \
'http://localhost:8080/team/search?name=FC' \
-H 'accept: */*' \
-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNzI3NzQxMzU1LCJpYXQiOjE3Mjc3MDUzNTV9.9JziKhhZ9WzLr0BtmANJCvfXPkVLYpqvKpdKerQAX-I'`

**Request URL:**

http://localhost:8080/team/search?name=FC

### Create Team

**Curl:**

`curl -X 'POST' \
'http://localhost:8080/team' \
-H 'accept: */*' \
-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNzI3NzQxMzU1LCJpYXQiOjE3Mjc3MDUzNTV9.9JziKhhZ9WzLr0BtmANJCvfXPkVLYpqvKpdKerQAX-I' \
-H 'Content-Type: application/json' \
-d '{
"name": "Name Test",
"league": "League Test",
"country": "Country Test"
}'`

**Request URL:**

`http://localhost:8080/team`

### Update Team

**Curl:**

`curl -X 'PUT' \
'http://localhost:8080/team?id=2' \
-H 'accept: */*' \
-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNzI3NzQxMzU1LCJpYXQiOjE3Mjc3MDUzNTV9.9JziKhhZ9WzLr0BtmANJCvfXPkVLYpqvKpdKerQAX-I' \
-H 'Content-Type: application/json' \
-d '{
"id": 2,
"name": "FC Barcelona",
"league": "La Liga",
"country": "España"
}'`

**Request URL:**

`http://localhost:8080/team?id=2`

### Delete Team

**Curl:**

`curl -X 'DELETE' \
'http://localhost:8080/team/25' \
-H 'accept: */*' \
-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNzI3NzQxMzU1LCJpYXQiOjE3Mjc3MDUzNTV9.9JziKhhZ9WzLr0BtmANJCvfXPkVLYpqvKpdKerQAX-I'`

**Request URL:**

`http://localhost:8080/team/25`

### Login

**Curl:**

`curl -X 'POST' \
'http://localhost:8080/auth/login' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"username": "test",
"password": "12345"
}'`

**Request URL:**

http://localhost:8080/auth/login