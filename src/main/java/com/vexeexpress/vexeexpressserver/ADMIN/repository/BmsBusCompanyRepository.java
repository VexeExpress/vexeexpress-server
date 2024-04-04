package com.vexeexpress.vexeexpressserver.ADMIN.repository;

import com.vexeexpress.vexeexpressserver.ADMIN.entity.BmsBusCompany;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BmsBusCompanyRepository extends MongoRepository<BmsBusCompany, String> {
    BmsBusCompany findByCompanyName(String companyName);
    BmsBusCompany findByIdUser(String idUser);
}
