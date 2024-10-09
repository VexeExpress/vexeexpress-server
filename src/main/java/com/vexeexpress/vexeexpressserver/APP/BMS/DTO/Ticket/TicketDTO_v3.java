package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Ticket;

import lombok.Data;

import java.util.List;
@Data
public class TicketDTO_v3 {
    private Long id;
    private String seatMapName;
    private int floor;
    private int row;
    private int seatColumn;
}
