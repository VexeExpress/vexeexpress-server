package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.SeatMap;

import lombok.Data;

@Data
public class SeatDTO_v3 {
    private Long id;
    private Integer floor;
    private Integer row;
    private Integer seatColumn;
    private String name;
    private Boolean status;
}
