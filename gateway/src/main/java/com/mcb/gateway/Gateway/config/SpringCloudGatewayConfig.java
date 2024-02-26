package com.mcb.gateway.Gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudGatewayConfig {
//
//    @Bean
//    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("Settings-Service", r -> r.path("/settings/**")
//                        .filters(f -> f.rewritePath("/settings/(?<segment>.*)", "/${segment}"))
//                        .uri("http://localhost:9093"))
//
//                .route("Auth-Service", r -> r.path("/security/**")
//                        .filters(f -> f.rewritePath("/security/.*", "/${segment}"))
//                        .uri("http://localhost:9091"))
//
//                .route("Report-Service", r -> r.path("/reports/**")
//                        .filters(f -> f.rewritePath("/reports/(?<segment>.*)", "/${segment}"))
//                        .uri("http://localhost:9092"))
//
//                .route("Property-Service", r -> r.path("/app/**")
//                        .filters(f -> f.rewritePath("/app/(?<segment>.*)", "/${segment}"))
//                        .uri("http://localhost:9094"))
//
//                .route("FileUpload-Service", r -> r.path("/files/**")
//                        .filters(f -> f.rewritePath("/files/(?<segment>.*)", "/${segment}"))
//                        .uri("http://localhost:9095"))
//                .build();
//    }
}
