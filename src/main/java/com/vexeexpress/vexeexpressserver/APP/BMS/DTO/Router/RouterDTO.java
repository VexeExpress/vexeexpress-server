package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Router;

import lombok.Data;

@Data
public class RouterDTO {
    private Long id;
    private String routeName;
    private String routeNameShort;
    private Double displayPrice;
    private Boolean status;
    private String note;
    private Long companyId;
}
