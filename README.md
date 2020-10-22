# quarkus-rest project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

### PostgreSQL Database
* Run a PostgreSQL database using Docker: docker container run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=db1234 postgres:alpine

### Api Calls
* Custom Health Check: curl http://localhost:8080/health
* Custom Health Check delayed: curl http://localhost:8080/health/async
* Get All Products: curl http://localhost:8080/product
* Product Creation: h
* curl http://localhost:8080/openapi
* Open in : http://localhost:8080/swagger-ui

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```