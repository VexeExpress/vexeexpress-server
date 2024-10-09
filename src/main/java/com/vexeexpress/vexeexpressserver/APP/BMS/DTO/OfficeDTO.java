package com.vexeexpress.vexeexpressserver.APP.BMS.DTO;

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
