package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "bms_trip")
@Data
public class BmsTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private BmsBusCompany company;

    @Column(name = "valueChairDiagram", nullable = false)
    private String valueChairDiagram;

    @Column(name = "valueDayDeparture", nullable = false)
    private LocalDate valueDayDeparture;

    @Column(name = "valueTimeDeparture", nullable = false)
    private LocalTime valueTimeDeparture;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private BmsVehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "router_id", referencedColumnName = "id", nullable = false)
    private BmsRouter router;

    @Column(name = "valueNote")
    private String valueNote;

    @Column(name = "valueDriver")
    private List<Integer> valueDriver;
}
