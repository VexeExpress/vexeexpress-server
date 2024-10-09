package com.vexeexpress.vexeexpressserver.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsOffice;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BmsOfficeRepository extends JpaRepository<BmsOffice, Long> {
//     List<BmsOffice> findByCompanyId(String companyId);
//
//    Optional<Object> findByName(String name);
}
