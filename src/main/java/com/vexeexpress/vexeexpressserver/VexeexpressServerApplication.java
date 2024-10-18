package com.vexeexpress.vexeexpressserver;

import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import com.vexeexpress.vexeexpressserver.repository.CompanyRepository;
import com.vexeexpress.vexeexpressserver.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;


@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackages = "com.vexeexpress.vexeexpressserver.entity")
public class VexeexpressServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VexeexpressServerApplication.class, args);
    }
    @Bean
    CommandLineRunner initDatabase(CompanyRepository companyRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            // Check if "Phương Hồng Linh" company exists
            BmsBusCompany phuongHongLinh = companyRepository.findByCompanyName("Phương Hồng Linh")
                    .orElseGet(() -> {
                        BmsBusCompany company = new BmsBusCompany();
                        company.setCompanyName("Phương Hồng Linh");
                        company.setAddress("123 Main Street, Gia Nghĩa, Đắk Nông");
                        company.setPhoneNumber("0901234567");
                        company.setStatus(true);
                        return companyRepository.save(company);
                    });


            BmsBusCompany tienOanh = companyRepository.findByCompanyName("Tiến Oanh")
                    .orElseGet(() -> {
                        BmsBusCompany company = new BmsBusCompany();
                        company.setCompanyName("Tiến Oanh");
                        company.setAddress("456 City Avenue, TP.HCM");
                        company.setPhoneNumber("0987654321");
                        company.setStatus(true);
                        return companyRepository.save(company);
                    });
            if (userRepository.count() == 0) {
                BmsUser user1 = new BmsUser();
                user1.setUsername("root.vexeexpress");
                user1.setPassword(passwordEncoder.encode("12345678")); // make sure to encrypt passwords in real apps
                user1.setStatus(true);
                user1.setName("Nguyễn Văn A");
                user1.setPhone("0909876543");
                user1.setAddress("123 Main Street, Gia Nghĩa, Đắk Nông");
                user1.setEmail("nguyenvana@example.com");
                user1.setCccd("123456789");
                user1.setGender(1);
                user1.setBirthDate(LocalDate.of(1990, 1, 1));
                user1.setRole(3);
                user1.setLicenseCategory(3);
                user1.setExpirationDate(LocalDate.of(2025, 12, 31));
                user1.setCompany(phuongHongLinh);
                userRepository.save(user1);

                BmsUser user2 = new BmsUser();
                user2.setUsername("root2.vexeexpress");
                user2.setPassword(passwordEncoder.encode("12345678")); // make sure to encrypt passwords in real apps
                user2.setStatus(true);
                user2.setName("Lê Thị B");
                user2.setPhone("0909876544");
                user2.setAddress("456 City Avenue, TP.HCM");
                user2.setEmail("lethib@example.com");
                user2.setCccd("987654321");
                user2.setGender(2); // Female
                user2.setBirthDate(LocalDate.of(1985, 5, 15));
                user2.setRole(3); // Admin
                user2.setLicenseCategory(null); // Admins might not have licenses
                user2.setExpirationDate(null);
                user2.setCompany(tienOanh);
                userRepository.save(user2);

                System.out.println("Khởi tạo data cơ bản");
            }
        };
    }

}
