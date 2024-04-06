package com.vexeexpress.vexeexpressserver.APP.ADMIN.repository;

import com.vexeexpress.vexeexpressserver.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
    Account findByUsername(String username);
    Account findByPassword(String password);
}
