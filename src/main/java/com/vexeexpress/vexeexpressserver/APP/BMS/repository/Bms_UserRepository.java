package com.vexeexpress.vexeexpressserver.APP.BMS.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Bms_UserRepository extends MongoRepository<BmsUser, String> {
    BmsUser findByUsername(String username);
}
