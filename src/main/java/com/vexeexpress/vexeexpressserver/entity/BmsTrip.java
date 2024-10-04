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

    @Column(name = "date_trip", nullable = false)
    private LocalDate dateTrip;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private BmsVehicle vehicleId;

    @ManyToOne
    @JoinColumn(name = "router_id", referencedColumnName = "id", nullable = false)
    private BmsRouter routerId;

    @ManyToOne
    @JoinColumn(name = "seat_map_id", referencedColumnName = "id", nullable = false)
    private BmsSeatMap seatMapId;

    @Column(name = "note")
    private String note;

    @Column(name = "user_id")
    private List<Integer> userId;
}
