package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Vehicle;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VehicleDTO {
    private Long id;
    private String licensePlate;
    private String phone;
    private String brand;
    private String color;
    private Integer category;
    private String chassisNumber;
    private String machineNumber;
    private LocalDate registrationDeadline;
    private LocalDate insuranceTerm;
    private Long companyId;
}
