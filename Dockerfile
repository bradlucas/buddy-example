FROM openjdk:8-alpine

COPY target/uberjar/buddy-example.jar /buddy-example/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/buddy-example/app.jar"]
