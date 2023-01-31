FROM eclipse-temurin:19-jre

WORKDIR /opt/app

ARG JAR_FILE=target/pbs-app.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]