package com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Ticket;

import lombok.Data;

import java.util.List;
@Data
public class TicketDTO {
    private Long id;
    private TicketDTO_v2 seat;
}
