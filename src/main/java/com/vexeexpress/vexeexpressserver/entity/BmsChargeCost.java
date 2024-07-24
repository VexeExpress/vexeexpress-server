package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bms_charge_cost")
@Data
public class BmsChargeCost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "trip_id", nullable = false)
    private Long tripId;

    @Column(name = "station")
    private Double station;

    @Column(name = "station_meal")
    private Double stationMeal;

    @Column(name = "daily_eat")
    private Double dailyEat;

    @Column(name = "washing")
    private Double washing;

    @Column(name = "repair")
    private Double repair;

    @Column(name = "driver_salary")
    private Double driverSalary;

    @Column(name = "assistant_salary")
    private Double assistantSalary;

    @Column(name = "freight_collected")
    private Double freightCollected;

    @Column(name = "gas")
    private Double gas;
}
