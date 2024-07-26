package com.vexeexpress.vexeexpressserver.APP.BMS.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.TicketService;
import com.vexeexpress.vexeexpressserver.entity.BmsTicket;
import com.vexeexpress.vexeexpressserver.APP.BMS.utils.TicketRequest;

@RestController
@RequestMapping("/bms/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/create-ticket")
    public ResponseEntity<String> createTicket(@RequestBody TicketRequest ticketRequest) {
        BmsTicket ticket = new BmsTicket(
            ticketRequest.getSeatNumber(),
            ticketRequest.getPhone(),
            ticketRequest.getName(),
            ticketRequest.getBoardingPoint(),
            ticketRequest.getDropOffPoint(),
            ticketRequest.getNotes(),
            ticketRequest.getPrice(),
            ticketRequest.getTripId()
        );
        ticketService.createTicket(ticket);
        return ResponseEntity.ok("Ticket created successfully");
    }

    
}
