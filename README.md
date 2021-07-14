# Customer Services REST API.

REST API that provides basic CRUD functionality for customer's information. It uses an H2 database
that can be configured to run in a different container.

## Technology stack
- Java 8+
- Spring Boot 2.4
- OpenAPI 3
- H2 Database
- Docker


OpenAPI services definition can be found at: src/main/resources/services.yaml

## BUILD

Building the application generates the API definition and the model objects from the OpenAPI YAML definition. 
Build script uses Gradle. Build command example:

$ gradle clean build

## RUNNING THE APP WITHOUT DOCKER
The H2 database URL needs to point to the in-memory database, for instance:
_spring.datasource.url=jdbc:h2:mem:customers_ <br/>

This can be changed in _src/main/resources/application.properties_.

H2 can also be run on its on process and accessed via TCP.

## DOCKER & DOCKER COMPOSE
### Building docker images

#### H2
docker build --file=Dockerfile-H2 --tag=h2-server:latest .

#### Spring Boot Customer API
Before building the Docker image, first update the URL of the H2 database in src/main/resources/application.properties.

It needs to point the IP address of the machine where H2 is running. **It cannot be localhost**, and your dev machines
might get a new IP address everytime it connect to the internet (Home Wi-Fi for instance).

Then build the app using _gradle build_. This will generate the app's JAR file.

To build the docker image you can use the following command:

docker build --file=Dockerfile --tag=customer-services:latest .

### Running Docker containers
You can now run you database container and then run your app container. Sample commands below:

#### H2
docker run -d -p1521:1521 -p81:81 -e H2_OPTIONS='-ifNotExists' h2-server:latest

#### Spring Boot API
docker run -p8080:8080 customer-services:latest

### Docker Compose
To run the app using Compose you need the Docker images from 'Building docker images' above.

docker-compose up

If you want to scale the Spring boot App you can use something like:

docker-compose up --scale customer-services=2

If you want more than 5 Spring Boot containers, the Docker Compose file needs to be updated to allow for a larger
port range.

## Improvements
- Caching is not distributed, so Customer objects can get stale. Change current caching impl for a distributed one, like Hazelcast.
- Add a load balancer to the Docker Compose configuration.
- Use Kubernetes









