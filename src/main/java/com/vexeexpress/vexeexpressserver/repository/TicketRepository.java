package com.vexeexpress.vexeexpressserver.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsTicket;
import com.vexeexpress.vexeexpressserver.entity.BmsTicket_v2;
import com.vexeexpress.vexeexpressserver.entity.BmsTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<BmsTicket, Long> {
    List<BmsTicket> findByTripId(String tripId);
    BmsTicket findBySeatNumberAndTripId(String seatNumber, String tripId);



}
