package com.vexeexpress.vexeexpressserver.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<BmsVehicle, Long> {
    List<BmsVehicle> findByCompanyId(Long companyId);

    boolean existsByLicensePlateAndCompany_Id(String licensePlate, Long companyId);

    boolean existsByLicensePlateAndCompany_IdAndIdNot(String licensePlate, Long companyId, Long id);
}
