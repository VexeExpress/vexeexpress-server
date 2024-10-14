package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Office;

import lombok.Data;

@Data
public class OfficeDTO_v4 {
    private Long id;
    private String name;
    private String phone;
    private String code;
    private String address;
    private String note;
    private Long companyId;
}
