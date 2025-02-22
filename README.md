This repository is designed to showcase how a login system works, utilizing **Spring Boot** and **Maven** for project management, with a focus on understanding the core concepts of user authentication and session management. 

## 🗂 Project Structure

### 📂 **resources/templates/**
Contains the HTML files for the user interface:
- **`login.html`**: Login page where users can enter their credentials to access the system. 🔑
- **`register.html`**: Registration page for new users to sign up. 
- **`index.html`**: The user dashboard displayed after a successful login. 

### 📂 **appLogin/**
This is the main package containing the core business logic of the application.

#### **AppLoginApplication** 
The entry point of the Spring Boot application that boots up the project.

#### **DataConfiguration** 
This class configures the connection to a **MySQL** database using **Spring JPA (Hibernate)**. It defines the `DataSource` and JPA adapter to allow Spring to interact with the database and perform CRUD operations.

### 📂 **Controller Package** 
Manages the login and registration logic, handles user authentication, and manages the cookies. When a user logs in, cookies are set to store user information, such as `userId` and `userName`. If the login fails, an error message is shown.

### 📂 **Model Package** 
Contains the **`User`** entity, which represents the user data and provides methods to access and modify user attributes (like email, password, etc.).

### 📂 **Repository Package** 
Contains the **`UserRepository`** interface:
- Fetches users by `ID`.
- Handles authentication queries by verifying **email** and **password**.

### 📂 **Service Package** 
#### **CookieService** 
Handles HTTP cookies:
- **`setCookie`**: Sets a cookie in the response with an expiration time.
- **`getCookie`**: Retrieves and decodes the cookie from the request.

#### **Authenticator Package** 
##### **LoginInterceptor** 
Intercepts incoming requests to check for a `userId` cookie. If not found, redirects the user to the **login page**. Ensures only authenticated users can access protected routes.

##### **LoginInterceptorAppConfig** ⚙
Configures and registers the `LoginInterceptor` to enforce authentication across the application. Excludes routes like `/login`, `/register`, etc., allowing unauthenticated access.

---

## 💻 Technologies Used

- **Spring Boot**: Framework for building Java-based applications.
- **Maven**: Dependency management and project automation tool.
- **MySQL**: Relational database for storing user data.
- **JPA (Hibernate)**: Java Persistence API for object-relational mapping.
- **Spring Security**: Provides authentication and authorization mechanisms.
- **Cookies**: For managing user sessions (storing `userId` and `userName`).

---

## ⚡ How to Run

1. Clone the repository.
2. Set up a local MySQL database named `appLogin`.
3. Configure your database connection details in the `DataConfiguration` class if necessary.
4. Run the application using your preferred IDE or via the terminal with this command:
```bash
mvn spring-boot:run
```
5.Navigate to http://localhost:8080 to access the application and test the login functionality!
