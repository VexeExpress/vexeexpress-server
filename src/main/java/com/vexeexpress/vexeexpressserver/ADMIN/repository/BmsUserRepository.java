package com.vexeexpress.vexeexpressserver.ADMIN.repository;

import com.vexeexpress.vexeexpressserver.ADMIN.entity.BmsUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BmsUserRepository extends MongoRepository<BmsUser, String> {
    BmsUser findByUsername(String username);

}
