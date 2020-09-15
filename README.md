# quarkus-rest project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

### Api Calls
* Custom Health Check: curl http://localhost:8080/health
* Custom Health Check delayed: curl http://localhost:8080/health/async
* Get All Products: curl http://localhost:8080/product
* Product Creation: curl -X POST -H "Content-Type: application/json" http://localhost:8080/product -d '{"name": "Ovaltine", "description": "They should call it Roundtine", "price": "3.50"}'
* Event bus message: curl -X POST -H "Content-Type: application/json" http://localhost:8080/message/send -d '{"message": "They should call it Roundtine"}'

### Sockets

* Socket at ws://localhost:8080/broadcast/{user}
* See chat.html in resources/META-INF.resources for client socket integration

## OpenAPI docs

* curl http://localhost:8080/openapi
* Open in : http://localhost:8080/swagger-ui

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `quarkus-rest-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/quarkus-rest-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/quarkus-rest-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.
