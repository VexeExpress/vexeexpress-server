package com.vexeexpress.vexeexpressserver.entity;

import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "bus_company")
@Data
public class BmsBusCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "company_name", length = 100, nullable = false)
    private String companyName;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @Column(name = "phone_number", length = 13, nullable = false)
    private String phoneNumber;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "date_expiration")
    private LocalDate dateExpiration;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    public BmsBusCompany(){
        
    }
}
