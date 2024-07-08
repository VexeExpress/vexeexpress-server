package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bms_trip")
@Data
public class BmsTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "company_id", nullable = false)
    private String companyId;

    @Column(name = "valueChairDiagram", nullable = false)
    private String valueChairDiagram;

    @Column(name = "valueDayDeparture", nullable = false)
    private LocalDate valueDayDeparture;

    @Column(name = "valueTimeDeparture", nullable = false)
    private LocalTime valueTimeDeparture;

    @Column(name = "valueVehicle")
    private String valueVehicle;

    @Column(name = "valueRouter", nullable = false)
    private String valueRouter;

    @Column(name = "valueNote")
    private String valueNote;

    @Column(name = "valueDriver")
    private List<Integer> valueDriver;
}
