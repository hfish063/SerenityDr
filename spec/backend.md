## Backend Configuration

### Setup Docker Container

In order to build and run the backend ensure you have the following installed.
-   [Docker](https://www.docker.com/products/docker-desktop)

The backend requires a MySQL database to be up and running.  The easiest way to accomplish this is to run the following commands.

```bash
docker pull mysql:latest
docker run --name serenitydr-db -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=serenitydr -p 3306:3306 -d mysql:latest
```

This will launch a docker container with the default username of 'root', password of 'password', and map port 3306 of your machine to port 3306 of the docker container to establish connections.

### Springboot

If we want to have a dedicated backend that can communicate and authorize requests from our Android application, the Springboot framework is a great option.  It is extensively documented and is compatible with both Java and Kotlin code.  The backend would be lightweight, as it only needs to interface with our Database.  Additional documentation can be found here.

-   [Springboot](https://docs.spring.io/spring-boot/index.html)

The general structure for creating backend services in Springboot is as follows.

```mermaid
flowchart LR
	Controller --> Service
	Service --> Repository
```

Where the **Controller** layer is where we expose our REST API endpoints, the **Service** layer handles any business logic needed when retrieving or saving data.  All database interactions take place in the **Repository**.  This design pattern allows for a simple abstraction, decoupling our database and application centric logic.

This is a more in-depth diagram that shows the overall architecture of our application.

```mermaid 
graph LR;

    subgraph Frontend
        UI[User Interface]
    end

    subgraph Backend
        Controller --> Service --> Repository --> Database[(Database)]
    end

    UI -->|Request| Controller
    Controller -->|Response| UI

```

Any kind of authorization that is required from a potential REST API could be handled through the Firebase API in Springboot, which provides methods for validating JWT tokens from incoming requests.  The Spring Security libraries allow us to intercept requests, and require certain authentication rules to be valid before acceptign them.

### Running the Application for Development

In order to enable full functionality for the application it will be necessary to run both the frontend and backend on our machines.  Usually the frontend runs locally, and the backend/database is hosted on the cloud or in another location, but this is not necessary for our project.  The only input necessary other than running the program is ensuring that the **database credentials** used for the Docker container match those provided to our backend.  We need to be able to access the database, it would look something like this.

```
spring.application.name=MyDatabaseApp
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/serenitydr
spring.datasource.username=root
spring.datasource.password=password

```

This can be found in the application.properties file of the backend, and the first line is auto generated for you.  The second is telling hibernate (ORM that handles database interaction) to automatically update our schema on changes to entity classes.  Lines 3-5 **must match** the credentials of the MySQL instance you are running in a Docker container.
