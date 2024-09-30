package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.response;

import lombok.Data;

@Data
public class SeatDTO_v2 {
    private Long id;
    private Integer floor;
    private Integer row;
    private Integer column;
    private String name;
    private Boolean status;
}
