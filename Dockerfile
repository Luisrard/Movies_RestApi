FROM openjdk:11-jre-slim
COPY "./target/movies_api-0.0.1-SNAPSHOT.jar" "movies-api.jar"
EXPOSE 9091
ENTRYPOINT ["java", "-jar", "movies-api.jar"]