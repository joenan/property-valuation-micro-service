package com.mcb.gateway.Gateway.config;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import reactor.netty.http.client.HttpClient;

@Configuration
@EnableWebFlux
public class CORSFilter implements WebFluxConfigurer {

   @Override
   public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedHeaders("*")
            .allowedMethods("*");
   }

   @Bean
   public CorsWebFilter corsWebFilter() {
      CorsConfiguration corsConfiguration = new CorsConfiguration();
      corsConfiguration.addAllowedHeader("*");
      corsConfiguration.addAllowedMethod("*");
      corsConfiguration.addAllowedOrigin("*");
      UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
      corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
      return new CorsWebFilter(corsConfigurationSource);
   }

   @Bean
   public HttpClient httpClient() {
      return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
   }
}