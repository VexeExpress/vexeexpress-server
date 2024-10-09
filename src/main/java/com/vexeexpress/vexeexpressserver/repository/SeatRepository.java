package com.vexeexpress.vexeexpressserver.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsSeat;
import com.vexeexpress.vexeexpressserver.entity.BmsSeatMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<BmsSeat, Long> {
    List<BmsSeat> findByBmsSeatMap_Id(Long seatMapId);
}
