package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Trip;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Company.CompanyDTO;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Data
public class TripDTO {
    private Long id;
    private Long routerId;
    private LocalDate dateTrip;
    private Long vehicleId;
    private List<Integer> userId;
    private Long seatMapId;
    private LocalTime time;
    private String note;
    private Long companyId;
}
