# gRPC with Spring boot

## Requirements

Java 22.


## Build the whole project

In the root folder (gRPCSpring): 
```
./gradlew build
```
See the generated files for the service: https://github.com/charroux/gRPCSpring/tree/main/myServiceInterface/src/generated/main

## Launch the server side
In the server folder (myServiceServer):
```
java -jar build/libs/myServiceServer-0.0.1-SNAPSHOT.jar
```

## Launch the client side
In the client folder (myServiceClient):
```
java -jar build/libs/myServiceClient-0.0.1-SNAPSHOT.jar
```
Service remote access: http://localhost:8080


## Test

Then you can use all the commands from the lab one with differents car's plate (do /cars to get them all)
