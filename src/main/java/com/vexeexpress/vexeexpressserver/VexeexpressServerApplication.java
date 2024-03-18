package com.vexeexpress.vexeexpressserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class VexeexpressServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(VexeexpressServerApplication.class, args);

    }
    @Bean
    public CommandLineRunner checkDatabaseConnection() {
        return args -> {
            String url = "jdbc:mysql://localhost:3306/vexeexpress";
            String username = "root";
            String password = "";

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                System.out.println("Kết nối CSDL thành công!");
            } catch (Exception e) {
                System.out.println("Kết nối CSDL thất bại!");
                e.printStackTrace();
            }
        };
    }

}
