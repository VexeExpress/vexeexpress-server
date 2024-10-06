package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Trip;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class TripDTO_v3 {
    private Long id;
    private LocalTime time;
    private String routerName;
    private LocalDate date;
    private String note;
    private List<String> user;
    private String seatMap;
    private String licensePlate;
    private String vehcilePhone;
}
