package com.vexeexpress.vexeexpressserver.APP.BMS.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class TicketDTO_v1 {
    private Long tripId; // BmsTrip
    private Long userId; // BmsUser
    private String userName;
    private Long officeId; // BmsOffice
    private String officeName;
    private Long agencyId;
    private String agencyName;
    private List<String> selectedRooms;

    private Long id;
    private String phone;
    private String roomCode;
    private String pickupPoint;
    private String dropoffPoint;
    private Double ticketPrice;
    private Integer paymentMethod;
    private String note;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private String fullName;
    private String ticketCode;

}
