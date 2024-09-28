package com.vexeexpress.vexeexpressserver.APP.BMS.DTO;

import lombok.Data;

import java.time.LocalDate;
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Boolean status;
    private LocalDate birth;
    private Integer gender;
    private Integer role;
    private String username;
    private String password;
    private Long companyId;
}
