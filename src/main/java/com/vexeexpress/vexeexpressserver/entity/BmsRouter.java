package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bms_router")
@Data
public class BmsRouter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "short_name", length = 100, nullable = false)
    private String shortName;

    @Column(name = "price", length = 100, nullable = false)
    private Double price;

    @Column(name = "note")
    private String note;

    @Column(name = "company_id", nullable = false)
    private String companyId;

}
