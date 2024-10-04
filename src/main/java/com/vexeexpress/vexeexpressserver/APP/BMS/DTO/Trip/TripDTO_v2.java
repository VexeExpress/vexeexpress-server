package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Trip;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TripDTO_v2 {
    private Long companyId;
    private LocalTime date;
    private Long routerId;


}
