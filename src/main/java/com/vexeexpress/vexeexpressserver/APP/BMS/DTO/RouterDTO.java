package com.vexeexpress.vexeexpressserver.APP.BMS.DTO;

import lombok.Data;

@Data
public class RouterDTO {
    private Long id;
    private String routeName;
    private String routeNameShort;
    private Double displayPrice;
    private String note;
    private Boolean status;


}
