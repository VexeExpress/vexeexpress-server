package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bms_vehicle")
@Data
public class BmsVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "licensePlate", length = 20, nullable = false)
    private String licensePlate;

    @Column(name = "phone", length = 10)
    private String phone;

    @Column(name = "brand", length = 50)
    private String brand;

    @Column(name = "color", length = 50)
    private String color;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "note", length = 200)
    private String note;

    @Column(name = "company_id", nullable = false)
    private String companyId;
}
