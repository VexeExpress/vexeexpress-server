package com.vexeexpress.vexeexpressserver.APP.BMS.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class CompanyDTO {
    private Long id;
    private String companyName;
    private String address;
    private String phoneNumber;
    private Boolean status;
    private LocalDate createdAt;
}
