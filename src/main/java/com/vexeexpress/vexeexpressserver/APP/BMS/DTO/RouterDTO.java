package com.vexeexpress.vexeexpressserver.APP.BMS.DTO;

import lombok.Data;

@Data
public class RouterDTO {
    private Long id;
    private String name;
    private String shortName;
    private Double price;
    private String note;
    private Long companyId;
}
