package com.vexeexpress.vexeexpressserver.APP.BMS.DTO;

import com.vexeexpress.vexeexpressserver.entity.BmsAgent;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
public class TicketDTO_v2 {
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
    private Long agencyId;
    private Long userId;
    private Long officeId;
}
