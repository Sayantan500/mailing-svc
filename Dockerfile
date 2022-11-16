FROM amazoncorretto:11-alpine
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
ENTRYPOINT ["./mvnw", "spring-boot:run"]