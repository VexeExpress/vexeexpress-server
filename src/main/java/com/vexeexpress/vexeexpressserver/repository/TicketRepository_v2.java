package com.vexeexpress.vexeexpressserver.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsTicket_v2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository_v2 extends JpaRepository<BmsTicket_v2, Long> {
    boolean existsByTripIdAndRoomCode(Long tripId, String roomCode);

//    @Query("SELECT t FROM BmsTicket_v2 t WHERE t.tripId = :tripId")
//    List<BmsTicket_v2> findBookedRoomsByTripId(@Param("tripId") Long tripId);

}
