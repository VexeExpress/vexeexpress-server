package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.SeatMap;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Company.CompanyDTO;
import lombok.Data;

import java.util.List;

@Data
public class SeatMapDTO {
    private String seatMapName;
    private Integer floor;
    private Integer row;
    private Integer column;
    private List<SeatDTO> seats;
    private CompanyDTO company;
}
