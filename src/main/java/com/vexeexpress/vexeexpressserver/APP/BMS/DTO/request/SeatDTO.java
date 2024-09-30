package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.request;

import lombok.Data;

@Data
public class SeatDTO {
    private Integer floor;
    private Integer row;
    private Integer column;
    private String name;
    private Boolean status;
}
