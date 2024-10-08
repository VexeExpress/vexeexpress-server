package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bms_level_agency")
@Data
public class BmsLevelAgency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private BmsBusCompany company;

    @Column(name = "level_name", nullable = false)
    private String levelName;

    @Column(name = "quota", nullable = false)
    private Double quota;
}
