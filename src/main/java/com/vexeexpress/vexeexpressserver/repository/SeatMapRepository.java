package com.vexeexpress.vexeexpressserver.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsSeatMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatMapRepository extends JpaRepository<BmsSeatMap, Long> {


    List<BmsSeatMap> findByCompanyId(Long companyId);

    boolean existsBySeatMapNameAndCompany_IdAndIdNot(String seatMapName, Long companyId, Long id);
}
