package com.vexeexpress.vexeexpressserver.APP.BMS.utils.Ticket;

import lombok.Data;

import java.util.List;
@Data
public class TicketRequest_v2 {
    private Long tripId;
    private List<String> selectedRooms;
    private Long userId;
    private String officeName;

}
