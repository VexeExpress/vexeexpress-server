package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.response;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.request.SeatDTO;
import lombok.Data;

import java.util.List;

@Data
public class SeatMapDTO_v2 {
    private Long id;
    private String seatMapName;
    private Integer floor;
    private Integer row;
    private Integer column;
    private List<SeatDTO_v2> seats;
}
