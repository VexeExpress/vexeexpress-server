package com.vexeexpress.vexeexpressserver.VE.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Entity // Đánh dấu class này là một entity trong cơ sở dữ liệu
@Data // Sử dụng Lombok để tự động tạo các getter, setter, toString, equals, và hashCode
@Table(name = "ve_user")
public class VeUser {
    @Id // Đánh dấu trường id là primary key của entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Đánh dấu trường id sẽ tự động tăng (auto-increment) trong cơ sở dữ liệu
    private Long id;

    @Column(nullable = false)
    private String email;


    private String password;

    @Column(nullable = false)
    private String name;


    private String phone;

    private boolean verified;

    private boolean status = true;
}
