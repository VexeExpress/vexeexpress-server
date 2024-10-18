package com.vexeexpress.vexeexpressserver.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<BmsBusCompany, Long> {
    Optional<BmsBusCompany> findByCompanyName(String companyName);
}
