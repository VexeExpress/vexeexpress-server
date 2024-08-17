package com.vexeexpress.vexeexpressserver.APP.BMS.utils;

import lombok.Data;

@Data
public class TicketRequest {
    private String seatNumber;
    private String phone;
    private String name;
    private String boardingPoint;
    private String dropOffPoint;
    private String notes;
    private String price;
    private String tripId;
}
