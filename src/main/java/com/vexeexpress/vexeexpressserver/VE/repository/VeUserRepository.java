package com.vexeexpress.vexeexpressserver.VE.repository;

import com.vexeexpress.vexeexpressserver.VE.entity.VeUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeUserRepository extends JpaRepository<VeUser, Long> {
    VeUser findByEmail(String email);
    VeUser findByName(String name);
    VeUser findByVerified(boolean verified);
    VeUser findByPassword(String password);
}
