package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

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
    private Integer category;

    @Column(name = "chassis_number", length = 50)
    private String chassisNumber;

    @Column(name = "machine_number", length = 50)
    private String machineNumber;

    @Column(name = "registration_deadline", length = 200)
    private LocalDate registrationDeadline;

    @Column(name = "insurance_term", length = 200)
    private LocalDate insuranceTerm;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private BmsBusCompany company;
}
