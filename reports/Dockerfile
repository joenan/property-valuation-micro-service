
FROM amazoncorretto:21.0.2-alpine3.19

#Working Directory for the API
WORKDIR /home/reports

#move the Jar to target folder
COPY target/*.jar /home/report-service.jar

#Api Port
EXPOSE 8080

CMD ["java", "-jar", "/home/report-service.jar"]