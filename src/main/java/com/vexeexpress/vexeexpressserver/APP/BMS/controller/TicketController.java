package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.TicketService;
import com.vexeexpress.vexeexpressserver.APP.BMS.utils.TicketRequest;
import com.vexeexpress.vexeexpressserver.entity.BmsTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bms/ticket")
@CrossOrigin(origins = "http://localhost:5173")
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
    @PutMapping("/update-ticket")
    public ResponseEntity<String> updateTicket(@RequestBody TicketRequest ticketRequest) {
        BmsTicket existingTicket = ticketService.findTicketBySeatNumberAndTripId(
                ticketRequest.getSeatNumber(), ticketRequest.getTripId()
        );
        if (existingTicket != null) {
            existingTicket.setPhone(ticketRequest.getPhone());
            existingTicket.setName(ticketRequest.getName());
            existingTicket.setBoardingPoint(ticketRequest.getBoardingPoint());
            existingTicket.setDropOffPoint(ticketRequest.getDropOffPoint());
            existingTicket.setNotes(ticketRequest.getNotes());
            existingTicket.setPrice(ticketRequest.getPrice());
            ticketService.updateTicket(existingTicket);
            return ResponseEntity.ok("Ticket updated successfully");
        } else {
            return ResponseEntity.status(404).body("Ticket not found");
        }
    }

    @DeleteMapping("/cancel-ticket")
    public ResponseEntity<String> cancelTicket(@RequestParam String seatNumber, @RequestParam String tripId) {
        boolean isCancelled = ticketService.cancelTicket(seatNumber, tripId);
        if (isCancelled) {
            return ResponseEntity.ok("Ticket canceled successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket not found");
        }
    }

    @GetMapping("/get-seats")
    public ResponseEntity<List<BmsTicket>> getSeats(@RequestParam String tripId) {
        List<BmsTicket> tickets = ticketService.findTicketsByTripId(tripId);
        return ResponseEntity.ok(tickets);
    }
}
