package com.vexeexpress.vexeexpressserver.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsOffice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BmsOfficeRepository extends JpaRepository<BmsOffice, String> {
     List<BmsOffice> findByCompanyId(String companyId);
}
