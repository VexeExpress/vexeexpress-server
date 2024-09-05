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

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "phone", length = 13, nullable = false)
    private String phone;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "gender", nullable = false)
    private Integer gender;

    @Column(name = "role", nullable = false)
    private Integer role;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private BmsBusCompany company;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    public BmsUser(){
        
    }
}
