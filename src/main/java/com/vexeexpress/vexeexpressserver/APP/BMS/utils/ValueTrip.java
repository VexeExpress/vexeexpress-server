package com.vexeexpress.vexeexpressserver.APP.BMS.utils;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ValueTrip {
    private Long id;
    private Long companyId;
    private String valueChairDiagram;
    private LocalDate valueDayDeparture;
    private LocalTime valueTimeDeparture;
    private String valueVehicle;
    private String valueRouter;
    private String valueNote;
    private String phoneVehicle;
    private List<String> valueDriver;
}
