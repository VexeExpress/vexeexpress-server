package com.vexeexpress.vexeexpressserver.APP.BMS.service;

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
}