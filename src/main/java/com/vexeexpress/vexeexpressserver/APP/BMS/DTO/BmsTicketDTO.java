package com.vexeexpress.vexeexpressserver.APP.BMS.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BmsTicketDTO {
    private Long id;
    private Long tripId;
    private String roomCode;
    private String phone;
    private String pickupPoint;
    private String dropoffPoint;
    private Double ticketPrice;
    private Integer paymentMethod;
    private String note;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private String fullName;
    private String ticketCode;
    private Integer agency;
    private Long userId;
    private String userName;
    private String officeName;

    public BmsTicketDTO(Long id, Long tripId, String roomCode, String phone, String pickupPoint, String dropoffPoint,
                        Double ticketPrice, Integer paymentMethod, String note, LocalDate departureDate,
                        LocalTime departureTime, String fullName, String ticketCode, Integer agency, Long userId,
                        String userName, String officeName) {
        this.id = id;
        this.tripId = tripId;
        this.roomCode = roomCode;
        this.phone = phone;
        this.pickupPoint = pickupPoint;
        this.dropoffPoint = dropoffPoint;
        this.ticketPrice = ticketPrice;
        this.paymentMethod = paymentMethod;
        this.note = note;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.fullName = fullName;
        this.ticketCode = ticketCode;
        this.agency = agency;
        this.userId = userId;
        this.userName = userName;
        this.officeName = officeName;
    }

    // Getters and setters
    // ...
}


