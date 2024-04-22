package com.vexeexpress.vexeexpressserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableJpaRepositories
public class VexeexpressServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(VexeexpressServerApplication.class, args);

    }

    

    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @SuppressWarnings("null")
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/api/**").allowedOrigins("http://localhost:3000").allowedMethods("GET", "POST", "PUT",
                    "DELETE");
        }
    }

}
