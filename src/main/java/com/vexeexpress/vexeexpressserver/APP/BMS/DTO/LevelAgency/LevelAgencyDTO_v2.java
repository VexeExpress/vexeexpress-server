package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.LevelAgency;

import lombok.Data;

@Data
public class LevelAgencyDTO_v2 {
    private Long id;
    private String levelName;
    private Double quota;

    // Constructor
    public LevelAgencyDTO_v2(Long id, String levelName, Double quota) {
        this.id = id;
        this.levelName = levelName;
        this.quota = quota;
    }


}
