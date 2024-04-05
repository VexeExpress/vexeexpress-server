package com.vexeexpress.vexeexpressserver.ADMIN.entity;

import jakarta.persistence.TemporalType;
import lombok.Data;
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

    private String ownerName;
    private String phoneNumber;
    private String username;
    private String password;
    private String address;
    private Boolean activateAccount;
    private Date createdDate;
}
