package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.User;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String address;
    private LocalDate birthDate;
    private String cccd;
    private String email;
    private LocalDate expirationDate;
    private Integer gender;
    private Integer licenseCategory;
    private String phone;
    private Integer role;
    private Boolean status;
    private Long companyId;
    private String password;
}
