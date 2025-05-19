package com.Calculadora.Metodos.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Permite solicitudes desde cualquier origen
        config.addAllowedOrigin("*");


        // Permite los métodos HTTP específicos
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");

        // Permite todas las cabeceras
        config.addAllowedHeader("*");

        // Permite que las credenciales se incluyan en la solicitud
        config.setAllowCredentials(true);

        source.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(source);
    }
}