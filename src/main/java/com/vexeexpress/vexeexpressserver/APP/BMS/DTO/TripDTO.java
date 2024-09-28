package com.vexeexpress.vexeexpressserver.APP.BMS.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Data
public class TripDTO {
    private Long id;
    private Long valueRouter;
    private String valueTimeDeparture;
    private Long valueVehicle;
    private String valueChairDiagram;
    private String valueDayDeparture;
    private String valueNote;
    private List<Integer> valueDriver;
    private Long companyId;
}
