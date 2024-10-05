package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.SeatMap;

import com.vexeexpress.vexeexpressserver.entity.BmsSeat;
import lombok.Data;

import java.util.List;

@Data
public class SeatMapDTO_v4 {
    private Long id;
    private String seatMapName;
    private Integer floor;
    private Integer row;
    private Integer column;
    private List<SeatDTO_v3> seats;
}
