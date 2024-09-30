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

    @Column(name = "route_name", length = 200, nullable = false)
    private String routeName;

    @Column(name = "route_name_short", length = 100, nullable = false)
    private String routeNameShort;

    @Column(name = "display_price", length = 100, nullable = false)
    private Double displayPrice;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private BmsBusCompany company;

}
