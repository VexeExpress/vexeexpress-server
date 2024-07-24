package com.vexeexpress.vexeexpressserver.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsChargeCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargeCostRepository extends JpaRepository<BmsChargeCost, Long> {
    BmsChargeCost findByTripId(Long tripId);
}
