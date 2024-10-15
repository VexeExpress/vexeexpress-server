package com.vexeexpress.vexeexpressserver.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepository extends JpaRepository<BmsOffice, Long> {
    List<BmsOffice> findByCompanyId(Long companyId);


    boolean existsByNameAndCompany_Id(String name, Long companyId);
}
