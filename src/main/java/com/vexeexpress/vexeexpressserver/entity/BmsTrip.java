package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bms_trip")
@Data
public class BmsTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



    @Column(name = "date_trip", nullable = false)
    private LocalDate dateTrip;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "vehicle_id", nullable = true)
    private Long vehicleId;

    @Column(name = "router_id", nullable = false)
    private Long routerId;

    @Column(name = "seat_map_id", nullable = false)
    private Long seatMapId;

    @Column(name = "note")
    private String note;

    @Column(name = "user_id")
    private List<Integer> userId;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private BmsBusCompany company;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<BmsTicket> tickets;



}


