package com.mcb.gateway.Gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudGatewayRouting {

    @Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("Settings-Service", r -> r.path("/settings/**")
                        .filters(f -> f.rewritePath("/settings/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:9093")) // dynamic routing with path rewriting

                .route("Auth-Service", r -> r.path("/security/**")
                        .filters(f -> f.rewritePath("/security/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:9091")) // dynamic routing with path rewriting

                .route("Report-Service", r -> r.path("/reports/**")
                        .filters(f -> f.rewritePath("/reports/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:9092")) // dynamic routing with path rewriting

                .route("Property-Service", r -> r.path("/valuation/**")
                        .filters(f -> f.rewritePath("/valuation/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:9094")) // dynamic routing with path rewriting

                .route("FileUpload-Service", r -> r.path("/files/**")
                        .filters(f -> f.rewritePath("/files/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:9095")) // dynamic routing with path rewriting
                .build();
    }
}
