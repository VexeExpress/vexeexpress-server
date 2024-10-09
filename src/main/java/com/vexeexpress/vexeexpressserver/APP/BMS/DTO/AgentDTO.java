package com.vexeexpress.vexeexpressserver.APP.BMS.DTO;

import lombok.Data;

import java.util.Date;
@Data
public class AgentDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Double discount;
    private String note;
    private Long companyId;
    private Date createdAt;
}
