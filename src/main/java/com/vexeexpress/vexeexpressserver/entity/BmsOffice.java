package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bus_office")
@Data
public class BmsOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "note")
    private String note;

    @Column(name = "company_id", nullable = false, length = 50)
    private String companyId;
}
