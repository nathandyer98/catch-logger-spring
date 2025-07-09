# 🎣 Catch Logger - Spring Boot Backend

This repository contains the backend REST API for the **Catch Logger** application, a platform for anglers to log and manage their fishing catches. Built with **Spring Boot**, it provides a robust, scalable, and production-ready foundation for the application's data management.

This project was developed to serve as a modern Java backend, replacing a previous Flask implementation and focusing on best practices like database migrations, containerization, and clean API design using DTOs.

---

## ✨ Key Features

* **RESTful API:** A complete set of CRUD (Create, Read, Update, Delete) endpoints for managing fishing catches.
* **Database Migrations:** Uses **Flyway** for version-controlled, reliable database schema management. No more risky `ddl-auto` in production.
* **DTO Pattern:** Utilizes Data Transfer Objects for API requests, ensuring a secure and stable API contract separate from the internal database models.
* **Validation:** Employs `jakarta.validation` annotations for robust, declarative validation of incoming data.
* **Global Error Handling:** A centralized `@RestControllerAdvice` provides clean, consistent JSON error responses for consumers.
* **Containerized:** Fully containerized with **Docker** and orchestrated with **Docker Compose** for consistent development and production environments.

---

## 🔧 Technologies Used

* **Framework:** Spring Boot 3
* **Language:** Java 21
* **Database:** PostgreSQL
* **Data Access:** Spring Data JPA / Hibernate
* **Database Migration:** Flyway
* **Build Tool:** Apache Maven
* **Containerization:** Docker & Docker Compose
* **Web Server (for Frontend):** Nginx
* **Utilities:** Lombok

---

## 🚀 Getting Started

Follow these instructions to get the application running locally for development or testing.

### Prerequisites

* **Git:** To clone the repository.
* **JDK 21:** Ensure you have a Java Development Kit version 21 installed and your `JAVA_HOME` environment variable is set correctly.
* **Docker & Docker Compose:** Required to run the application stack (backend, database, and web server).

### Installation & Setup

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/nathandyer98/catch-logger-spring.git](https://github.com/nathandyer98/catch-logger-spring.git)
    cd catch-logger-spring
    ```

2.  **Create the Environment File:**
    The application uses an `.env` file to manage secrets and database configuration for Docker Compose. Create a file named `.env` in the root of the project and add the following content. You can change the password to something more secure.

    ```dotenv
    # PostgreSQL Secrets
    POSTGRES_USER=postgres
    POSTGRES_PASSWORD=your_secure_password
    POSTGRES_DB=catch_logger_db

    # Spring Boot Datasource URL for use within the Docker network
    # The hostname 'db' matches the service name in docker-compose.yml
    SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/catch_logger_db
    SPRING_DATASOURCE_USERNAME=${POSTGRES_USER}
    SPRING_DATASOURCE_PASSWORD=${POSTGRES_PASSWORD}
    ```

3.  **Build and Run with Docker Compose:**
    This single command will build the frontend and backend Docker images and start all three services (database, backend, frontend).

    ```bash
    docker-compose up --build
    ```

    * The application's frontend will be available at `http://localhost`.
    * The backend API (routed through Nginx) will be available at `http://localhost/api`.

---

## 📖 API Endpoints

The API is exposed under the `/api` path prefix.

| Method | Endpoint | Description | Example Body |
| :--- | :--- | :--- | :--- |
| `GET` | `/catches` | Retrieves a list of all catches. | (None) |
| `POST` | `/catches` | Creates a new catch. | `{ "name": "My Catch", "species": "PIKE", "weight": 10.5 }` |
| `GET` | `/catches/{id}` | Retrieves a single catch by its ID. | (None) |
| `PUT` | `/catches/{id}` | Updates an existing catch. | `{ "location": "The point", "notes": "Updated notes" }` |
| `DELETE` | `/catches/{id}` | Deletes a catch by its ID. | (None) |

### Example: Creating a new catch with `curl`

```bash
curl -X POST http://localhost/api/catches \
-H "Content-Type: application/json" \
-d '{
      "name": "Big Pike",
      "species": "PIKE",
      "weight": 15.2,
      "location": "Main lake, near the reeds"
    }'
