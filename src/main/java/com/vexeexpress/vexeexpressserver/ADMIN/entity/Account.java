package com.vexeexpress.vexeexpressserver.ADMIN.entity;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "admin_account")
public class Account {
    @Id
    private String id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
}
