package com.vexeexpress.vexeexpressserver.APP.BMS.DTO;

import lombok.Data;

import java.time.LocalDate;
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String name;
    private String phone;
    private String address;
    private String email;
    private String cccd;
    private Integer gender;
    private Boolean status;
    private LocalDate birthDate;
    private Integer role;
    private Integer licenseCategory;
    private LocalDate expirationDate;
}
