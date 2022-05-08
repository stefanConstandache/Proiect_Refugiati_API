FROM openjdk:11.0.15-oracle

#EXPOSE 8080
EXPOSE 8888

WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw clean
RUN ./mvnw install -DskipTests

#RUN mkdir -p target/dependency
#RUN cd target/dependency

#ENTRYPOINT ["mvn", "spring-boot:run"]
ENTRYPOINT ["java", "-jar", "./target/proiect_refugiati_api-0.0.1-SNAPSHOT.jar", "--spring.config.name=application-compose"]

#CMD sleep 25 && java -jar ./target/proiect_refugiati_api-0.0.1-SNAPSHOT.jar --spring.config.name=application-compose
#CMD sleep 25 && java -jar ./target/proiect_refugiati_api-0.0.1-SNAPSHOT.jar --spring.config.location=./src/main/resource/application-compose.properties


