package com.vexeexpress.vexeexpressserver.APP.ADMIN.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
@Repository
public interface AdminBusCompanyRepository extends JpaRepository<BmsBusCompany, Long>{
    Optional<BmsBusCompany> findByCompanyName(String companyName);  

} 
