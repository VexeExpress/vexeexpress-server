package com.vexeexpress.vexeexpressserver.APP.ADMIN.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminBusCompanyRepository extends MongoRepository<BmsBusCompany, String> {
    BmsBusCompany findByCompanyName(String companyName);
}
