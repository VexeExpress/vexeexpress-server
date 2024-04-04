package com.vexeexpress.vexeexpressserver.ADMIN.repository;

import com.vexeexpress.vexeexpressserver.ADMIN.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
    Account findByUsername(String username);
    Account findByPassword(String password);
}
