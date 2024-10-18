package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Router;

import lombok.Data;

@Data
public class RouterDTO_v2 {
    private Long id;
    private String routeName;


    public RouterDTO_v2(Long id, String routeName) {
        this.id = id;
        this.routeName = routeName;
    }
}
