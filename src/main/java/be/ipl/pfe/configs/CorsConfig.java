package be.ipl.pfe.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowedOrigins("http://localhost:4200",
                                "https://pfe-web-front-dev.herokuapp.com/",
                                "https://pfe-web-frontend-production.herokuapp.com",
                                "https://pfe-web-frontend-dev.herokuapp.com",
                                "https://pfe-web-front.herokuapp.com/",
                                "*"
                        );
            }
        };
    }
}
