package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.TemporalType;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@Document(collection = "bms_user")
public class BmsUser {
    @Id
    private String id;
    private String name;
    private String phone;
    private String gender;
    private String email;
    private Date birth;
    private String role;
    private String username;
    private String password;
    private String companyId;
    private Date dateInnitiated;
    private Boolean status;

}
