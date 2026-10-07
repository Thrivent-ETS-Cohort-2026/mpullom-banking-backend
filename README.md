# mpullom-banking-backend

## Running with Docker Compose

Make sure Docker Desktop is running before starting the container stack.

1. **Build and start PostgreSQL database:**
   ```bash
   cd src/main/java/com/example/banking_backend/db && docker compose up --build
    ```

2. **Build and start the application:**
- Open a new terminal window and run the following command
   ```bash
   docker compose up --build
    ```

3. **Open with Swagger to view endpoints**
- In your browser navigate to http://localhost:8080/swagger-ui.html