package com.vexeexpress.vexeexpressserver.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsLevelAgency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LevelAgencyRepository extends JpaRepository<BmsLevelAgency, Long> {
    List<BmsLevelAgency> findByCompanyId(Long companyId);
}
