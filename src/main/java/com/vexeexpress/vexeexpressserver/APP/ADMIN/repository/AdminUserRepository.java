package com.vexeexpress.vexeexpressserver.APP.ADMIN.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vexeexpress.vexeexpressserver.entity.BmsUser;

@Repository
public interface AdminUserRepository extends JpaRepository<BmsUser, Long>{
    BmsUser findByUsername(String username);
    List<BmsUser> findByCompanyId(Long companyId);
}
