package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vexeexpress.vexeexpressserver.entity.BmsTicket;
import com.vexeexpress.vexeexpressserver.repository.BmsTicketRepository;

@Service
public class TicketService {

    @Autowired
    private BmsTicketRepository ticketRepository;

    public BmsTicket createTicket(BmsTicket ticket) {
        return ticketRepository.save(ticket);
    }

    public void updateTicket(BmsTicket ticket) {
        ticketRepository.save(ticket); // Save sẽ cập nhật nếu vé đã tồn tại
    }
    public boolean cancelTicket(String seatNumber, String tripId) {
        BmsTicket ticket = ticketRepository.findBySeatNumberAndTripId(seatNumber, tripId);
        if (ticket != null) {
            ticketRepository.delete(ticket);
            return true;
        }
        return false;
    }
    
    public BmsTicket findTicketBySeatNumberAndTripId(String seatNumber, String tripId) {
        return ticketRepository.findBySeatNumberAndTripId(seatNumber, tripId);
    }

    public List<BmsTicket> findTicketsByTripId(String tripId) {
        return ticketRepository.findByTripId(tripId);
    }
}