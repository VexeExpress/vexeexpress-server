package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Ticket;

import lombok.Data;

@Data
public class TicketDTO_v2 {
    private Long id;
    private int floor;
    private int row;
    private int seatColumn;
    private String name;
    private boolean status;
    private TicketDTO_v3 seatMap;
}
