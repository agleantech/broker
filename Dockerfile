FROM openjdk:8-jre-alpine
COPY "target/broker-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]