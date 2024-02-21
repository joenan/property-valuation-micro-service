
FROM amazoncorretto:21.0.2-alpine3.19

#Working Directory for the API
WORKDIR /home/gateway

#move the Jar to target folder
COPY target/*.jar /home/gateway-service.jar

#Api Port
EXPOSE 8080

CMD ["java", "-jar", "/home/gateway-service.jar"]