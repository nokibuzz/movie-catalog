# Movie Catalog Application
Full stack application that simulates streaming movies, adding, editing, and manipulating movies. The application supports multiple user roles that define user permissions.

## Features
***Movie Browsing***: Users can browse, search, and view a catalog of available movies.

***User Authentication***: Secure login and registration system.

***Role-Based Access Control***: The application has two distinct user roles with different permissions:

**Watcher**: Can browse the movie catalog, search for movies, and view movie details.

**Admin**: Has full CRUD (Create, Read, Update, Delete) permissions over the movie catalog. Admins can add new movies, edit existing movie details, and remove movies from the catalog.

***Movie Management***: Admins can easily manage all movie listings through a dedicated interface.

## Tech Stack
The application is built with a modern full-stack architecture:

**Frontend**: React

**Backend**: Java with Spring Boot

**Database**: (Please specify your database, e.g., PostgreSQL, MySQL, MongoDB)

## Project Structure
The repository is organized into two main directories:

**/frontend**: Contains the React.js client application.

**/backend/dipl**: Contains the Java Spring Boot server application.

## Getting Started
To get a local copy up and running, follow these simple steps.

### Prerequisites
Node.js and npm (for frontend)

JDK (e.g., JDK 17) and Maven (for backend)

A running instance of (your database, e.g., PostgreSQL)

#### Installation
**1. Clone the repository:**

```bash
git clone https://github.com/nokibuzz/movie-catalog.git
cd movie-catalog
```

**2. Set up the Backend (Spring Boot):**

```bash
# Navigate to the backend directory
cd backend/dipl
```

##### Configure your database
- Open 'src/main/resources/application.properties'

- Update the 'spring.datasource.url', 'spring.datasource.username', and 'spring.datasource.password'fields to match your local database credentials.

#### Build and run the application
```bash
mvn spring-boot:run
```
The backend server will start on http://localhost:8080.

**3. Set up the Frontend (React):**

```bash
# Open a new terminal and navigate to the frontend directory
cd frontend
```

#### Install NPM packages
```bash
npm install
```

#### Start the development server
```bash
npm start
```
The React application will open and run on http://localhost:3000.
