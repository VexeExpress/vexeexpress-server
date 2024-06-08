package com.vexeexpress.vexeexpressserver.repository;

import org.springframework.stereotype.Repository;
import com.vexeexpress.vexeexpressserver.entity.BmsBus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BmsBusRepository extends JpaRepository<BmsBus, Long> {
    Optional<BmsBus> findByBusNumber(String busNumber);

    Optional<BmsBus> findByLicensePlate(String licensePlate);

    List<BmsBus> findByBusNumberContaining(String busNumber);
}
