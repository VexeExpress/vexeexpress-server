package com.vexeexpress.vexeexpressserver.APP.BMS.utils.Ticket;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;

    public User(Long id, String name) {
    }
}
