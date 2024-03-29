FROM openjdk:21

EXPOSE 8080

COPY ./build/libs/evaluator-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "evaluator-0.0.1-SNAPSHOT.jar"]
