package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Office;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.CompanyDTO;
import lombok.Data;

@Data
public class OfficeDTO {
    private Long id;
    private String name;
    private String phone;
    private String code;
    private String address;
    private String note;
    private CompanyDTO company;
}
