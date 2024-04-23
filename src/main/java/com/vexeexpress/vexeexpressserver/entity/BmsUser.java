package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;


import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "bms_user")
@Data
public class BmsUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "phone", length = 13, nullable = false)
    private String phone;

    @Column(name = "status", length = 1, nullable = false)
    private String status;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "gender", length = 6, nullable = false)
    private String gender;

    @Column(name = "role",  length = 1, nullable = false)
    private String role;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    public BmsUser(){
        
    }
}
