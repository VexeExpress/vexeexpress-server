package com.vexeexpress.vexeexpressserver.APP.ADMIN.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Admin_UserRepository extends MongoRepository<BmsUser, String> {
    BmsUser findByUsername(String username);

    List<BmsUser> findByCompanyId(String companyId);
}
