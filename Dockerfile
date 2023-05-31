FROM openjdk:17

EXPOSE 8080

COPY ./target/evaluator-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "evaluator-0.0.1-SNAPSHOT.jar"]
