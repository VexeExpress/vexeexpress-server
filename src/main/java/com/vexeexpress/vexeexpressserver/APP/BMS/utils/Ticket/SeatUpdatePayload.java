package com.vexeexpress.vexeexpressserver.APP.BMS.utils.Ticket;

import lombok.Data;

@Data
public class SeatUpdatePayload {
    private Long id;
    private String phone;
    private String pickupPoint;
    private String dropoffPoint;
    private Double ticketPrice;
    private Integer paymentMethod;
    private String note;
    private String departureDate;
    private String departureTime;
    private String fullName;
    private String ticketCode;
    private Integer agency;
    private Long userId;
    private String officeName;
}
