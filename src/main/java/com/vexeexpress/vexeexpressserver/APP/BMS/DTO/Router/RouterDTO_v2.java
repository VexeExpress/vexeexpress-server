package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Router;

import lombok.Data;

@Data
public class RouterDTO_v2 {
    private Long id;
    private String routeName;
    private Double displayPrice;


    public RouterDTO_v2(Long id, String routeName, Double displayPrice) {
        this.id = id;
        this.routeName = routeName;
        this.displayPrice = displayPrice;
    }
}
