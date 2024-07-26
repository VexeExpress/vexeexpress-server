package com.vexeexpress.vexeexpressserver.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vexeexpress.vexeexpressserver.entity.BmsTicket;

@Repository
public interface BmsTicketRepository extends JpaRepository<BmsTicket, Long> {
}
