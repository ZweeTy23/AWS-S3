package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Aplica CORS solo a rutas del backend
                .allowedOrigins("http://localhost:3000") // Frontend local
                .allowedMethods("*") // Permite GET, POST, DELETE, etc.
                .allowedHeaders("*") // Permite todos los headers (como Content-Type)
                .allowCredentials(true); // Por si se usan cookies o auth más adelante
    }
}
