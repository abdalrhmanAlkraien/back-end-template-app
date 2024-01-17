# SpringBoot Project README

This README provides essential information about the SpringBoot project, outlining key details regarding the technology stack, authentication mechanism, and database setup.

## Project Details

- **Java Version:** 17
- **Maven Version:** 3.9.4
- **SpringBoot Version:** 3.1.0

## Authentication Mechanism

The project incorporates Spring Security to manage user authentication. This ensures a secure login mechanism for users.

## Database Setup

- **Development Database:** H2 Database
- **Production Database:** MySQL Database

## Data Access

The project uses Spring Data JPA in conjunction with Hibernate to facilitate communication with the databases. This allows for efficient data access and management within the application.

## Build and Run

To build and run the project, ensure you have Java 17 and Maven 3.9.4 installed. Use the following commands:

1. **Build:**

    ```
    mvn clean install
    ```

2. **Run:**

    ```
    mvn spring-boot:run
    ```

3. **build and push to dockerhub**

    ```
    mvn clean package -Dimage.version= image version
    ```   

   **Note** to skip build and push you need to use this command
   ```
   mvn clean install -Djib.skip=true
    ```


## Configuration

### Database Configuration

- For development, the application uses H2 Database. Configuration details can be found in `src/main/resources/application.yaml`.
- For production, configure the MySQL database by modifying `src/main/resources/application-prod.yaml`.

### Spring Security Configuration

- Spring Security configuration can be found in `src/main/java/com/marinabits/energietechs/infra/security/SecurityConfig.java`.

## Contributions

Contributions are welcome! If you'd like to contribute to this project, please create a pull request.

## Issue Reporting

If you encounter any issues or have suggestions for improvements, please [create an issue](https://github.com/your-project-repository/issues) on our GitHub repository.

## License

This project is licensed under the [MIT License](LICENSE). Feel free to use and modify this code for your own projects.

## Contact

For any inquiries or additional information, please contact:
- Omar Ismail
- omar.pal.95@gmail.com
- Abdalrhman Alkraien
- abdalrhmanalkraien@gmail.com

Thank you for using and contributing to this project! Happy coding!
