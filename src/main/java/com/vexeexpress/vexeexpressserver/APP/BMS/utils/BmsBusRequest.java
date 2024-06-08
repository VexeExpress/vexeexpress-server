package com.vexeexpress.vexeexpressserver.APP.BMS.utils;

import lombok.Data;

@Data
public class BmsBusRequest {
    private String busNumber;
    private String licensePlate;
    private int capacity;
    private String status;
    private Long companyId;
}