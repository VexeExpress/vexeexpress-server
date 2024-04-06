package com.vexeexpress.vexeexpressserver;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
@EnableMongoRepositories
public class VexeexpressServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(VexeexpressServerApplication.class, args);

    }

    @Bean
    public CommandLineRunner checkDatabaseConnection() {
        return args -> {
            String connectionString = "mongodb+srv://root:CVbn12345@vexeexpress.g7k9lqm.mongodb.net/VexeExpress?retryWrites=true&w=majority";

            try {
                MongoClient mongoClient = MongoClients.create(connectionString);
                MongoDatabase database = mongoClient.getDatabase("VexeExpress");
                System.out.println("Kết nối CSDL MongoDB thành công!");
                mongoClient.close();
            } catch (MongoException e) {
                System.err.println("Kết nối CSDL MongoDB thất bại: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/api/**").allowedOrigins("http://localhost:3000").allowedMethods("GET", "POST", "PUT",
                    "DELETE");
        }
    }

}
