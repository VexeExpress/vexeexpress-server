package com.vexeexpress.vexeexpressserver.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BmsOfficeRepository extends JpaRepository<BmsOffice, Long> {

    List<BmsOffice> findByCompanyId(String companyId);
}