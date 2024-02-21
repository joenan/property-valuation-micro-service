# Property Valuation Microservices

This repository contains a set of Spring Boot microservices for Property Valuation.

## Microservices Overview

1. **Gateway Service:** Responsible for routing requests to the appropriate microservice.
2. **Auth Service:** Handles authentication and authorization.
3. **Settings Service:** Manages application settings such as currency, categories, etc.
4. **Reports Service:** Generates and manages property valuation reports.
5. **Valuation Service:** Core service for property valuation.

## Prerequisites

- [Docker](https://www.docker.com/get-started)

## Running the Microservices

1. Clone the repository:

    ```bash
    https://github.com/joenan/property-valuation-micro-service.git
    ```

2. Navigate to the project directory:

    ```bash
    cd property-valuation-microservices
    ```

3. Pull Docker images using Docker Compose:

    ```bash
    docker-compose up -d
    ```

4. Build the Docker images for each service:

    ```bash
    docker build -t gateway-service ./gateway
    docker build -t auth-service ./auth
    docker build -t settings-service ./settings
    docker build -t reports-service ./reports
    docker build -t valuation-service ./valuation
    ```

5. Run the microservices using Docker:

    ```bash
    docker run -it -p 9090:9090 gateway-service
    docker run -it -p 9091:9091 auth-service
    docker run -it -p 9093:9093 settings-service
    docker run -it -p 9092:9092 reports-service
    docker run -it -p 9094:9094 valuation-service
    ```

   These commands will pull the necessary Docker images, build the microservices, and run the containers.

6. Access the microservices:

   - Gateway: [http://localhost:9090](http://localhost:9090)
   - Auth Service: [http://localhost:9090/auth/**](http://localhost:9090/auth/**)
   - Settings Service: [http://localhost:9090/settings/**](http://localhost:9090/settings/**)
   - Reports Service: [http://localhost:9090/reports/**](http://localhost:9090/reports/**)
   - Valuation Service: [http://localhost:9090/valuation/**](http://localhost:9090/valuation/**)


7. Signup an account:

```bash
   http://localhost:9090/security/v1/auth/signup
```

```
{
"username": "joenan",
"email": "nandomgsn@gmail.com",
"name": "Nandom Gusen",
"businessUnit": "IT Department",
"contactNumber": "08060680061",
"password": "P@ssword"
}
```
Send the above payload to the url above to signup for a new account


6. SignIn and obtain auth token:

```
http://localhost:9090/security/v1/auth/signin
```

```
{
"username": "mcbuser",
"password": "P@ssword!$"
}
```

## Stopping the Microservices

To stop the running microservices, use the following command:

```bash
docker-compose down
```

7. The micro service has been deployed for testing and can be tested at:

```
   - Eureka Server: [http://63.250.53.24:8761](http://63.250.53.24:8761)
   - Gateway: [http://63.250.53.24:9090](http://63.250.53.24:9090)
   - Auth Service: [http://63.250.53.24:9090/auth/**](http://63.250.53.24:9090/auth/**)
   - Settings Service: [http://63.250.53.24:9090/settings/**](http://63.250.53.24:9090/settings/**)
   - Reports Service: [http://63.250.53.24:9090/reports/**](http://63.250.53.24:9090/reports/**)
   - Valuation Service: [http://63.250.53.24:9090/valuation/**](http://63.250.53.24:9090/valuation/**)
```
8. The swagger config for every deployed service can be accessed through

   ```
   - Auth Service: [http://63.250.53.24:9091/swagger-ui/index.html](http://63.250.53.24:9091/swagger-ui/index.html)
   - Settings Service: [http://63.250.53.24:9093/swagger-ui/index.html](http://63.250.53.24:9093/swagger-ui/index.html)
   - Reports Service: [http://63.250.53.24:9092/swagger-ui/index.html](http://63.250.53.24:9092/swagger-ui/index.html)
   - Valuation Service: [http://63.250.53.24:9094/swagger-ui/index.html](http://63.250.53.24:9094/swagger-ui/index.html)
```


![Local Image](./eurekaserver.png)

![Local Image](./swaggerdoc.png)