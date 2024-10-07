package com.vexeexpress.vexeexpressserver.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsTicket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<BmsTicket, Long> {

    List<BmsTicket> findByTripId(Long tripId);

    @Modifying
    @Transactional
    @Query("UPDATE BmsTicket t SET t.selected = :selected WHERE t.id = :id")
    void updateSeatSelectedStatus(Long id, Boolean selected);
}
