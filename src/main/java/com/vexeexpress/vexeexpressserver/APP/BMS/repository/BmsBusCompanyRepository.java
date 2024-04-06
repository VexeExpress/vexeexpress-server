package com.vexeexpress.vexeexpressserver.APP.BMS.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BmsBusCompanyRepository extends MongoRepository<BmsBusCompany, String> {
    BmsBusCompany findByCompanyName(String companyName);
    BmsBusCompany findByIdUser(String idUser);
}
