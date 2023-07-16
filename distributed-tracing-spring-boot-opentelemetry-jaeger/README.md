# Distributed Tracing with OpenTelemetry and Jaeger

In this project I have figured out how we can integrate distributed tracing in a Spring Boot application. 
We are using OpenTelemetry to export traces to Jaeger.

You can read about this on my website [https://refactorfirst.com](https://refactorfirst.com)

Once you build the application using `mvn clean verify`, You can start the application as two service instances.

Service 1
```
java -jar build/libs/Distributed-Service-0.0.1-SNAPSHOT.jar --spring.application.name=Service-1 --server.port=8080
```

Service 2
```
java -jar build/libs/Distributed-Service-0.0.1-SNAPSHOT.jar --spring.application.name=Service-2 --server.port=8091
```

sudo tcpdump -i any "host localhost and (port 4318 or port 4317)"

https://opentelemetry.io/docs/instrumentation/java/manual/