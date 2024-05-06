# Clear Solution Test Task


`The following technologies are used to build the Application:`
- ☕ **Java 17**
- 🌱 **Spring Boot**
- 🌱🛢️ **Spring Data JPA**
- 🗎 **Swagger**
- 🐬 **MySQL**
- 🐋  **Docker**
- 🌶️ **Lombok**
- ↔️ **MapStruct**

### ❓ How to use
`Before running, ensure you have the following installed:`
- ☕ Java
- 🐋 Docker

`Follow the steps below to install:`
1. Clone the repository from GitHub and navigate to the project directory.
2. Create a `.env` file with the necessary environment variables. (See `.env-sample` for a sample.)
3. Run the following command to build and start the Docker containers:
   `docker-compose up --build`.
4. The application should now be running at `http://localhost:8081`. 

### Auth Endpoints(all users)

| **HTTP method** | **Endpoint** | **Function**                                 |
|:----------------|:-------------|:---------------------------------------------|
| GET             | /user        | Get a list of users between given date range |
| DELETE          | /user/{id}   | Delete user by specific id                   |
| POST            | /user        | Create new user                              |
| PUT             | /user/{id}   | Update all info about specific user          |
| PATCH           | /user/{id}   | Update some info about user                  |
