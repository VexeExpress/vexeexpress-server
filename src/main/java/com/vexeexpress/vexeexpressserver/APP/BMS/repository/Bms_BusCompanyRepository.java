package com.vexeexpress.vexeexpressserver.APP.BMS.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface Bms_BusCompanyRepository extends MongoRepository<BmsBusCompany, String> {
    Optional<BmsBusCompany> findById(String id);
}
