package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Trip;

import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class TripDTO_v2 {
    private Long id;
    private LocalTime time;
    private String seatMapName;
    private String licensePlate;
    private List<String> user;
}
