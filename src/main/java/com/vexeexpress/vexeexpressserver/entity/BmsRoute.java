package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bms_route")
@Data
public class BmsRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "route_name")
    private String routeName;

    @Column(name = "route_name_short")
    private String routeNameShort;

    @Column(name = "display_price")
    private Double displayPrice;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private BmsBusCompany company;
}
