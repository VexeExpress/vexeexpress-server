package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.*;
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

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "phone", length = 13, nullable = false)
    private String phone;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "cccd", length = 30)
    private String cccd;

    @Column(name = "gender", nullable = false)
    private Integer gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "role", nullable = false)
    private Integer role;

    @Column(name = "license_category")
    private Integer licenseCategory;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private BmsBusCompany company;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;
}
