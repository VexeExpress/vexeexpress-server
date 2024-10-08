package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.LevelAgency;

import lombok.Data;

@Data
public class LevelAgencyDTO {
    private Long id;
    private String levelName;
    private Double quota;
    private Long companyId;

}
