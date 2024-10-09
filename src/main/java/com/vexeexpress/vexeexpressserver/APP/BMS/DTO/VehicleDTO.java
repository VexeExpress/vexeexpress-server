package com.vexeexpress.vexeexpressserver.APP.BMS.DTO;

import lombok.Data;

@Data
public class VehicleDTO {
    private Long id;
    private String licensePlate;
    private String phone;
    private String brand;
    private String color;
    private String category;
    private String note;
    private Long companyId;
}
