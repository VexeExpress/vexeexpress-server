package com.vexeexpress.vexeexpressserver.APP.BMS.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Data
public class TripDTO_v3 {
    private Long tripId; // BmsTrip
    private String valueChairDiagram; // BmsTrip
    private LocalTime valueTimeDeparture; // BmsTrip
    private LocalDate valueDayDeparture; //BmsTrip
    private String routerName; // BmsRouter
    private String licensePlate; // BmsVehicle
    private String vehiclePhone; // BmsVehicle
    private String valueNote; // BmsTrip
    private List<String> valueDriverNames;
}
