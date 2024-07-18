package com.vexeexpress.vexeexpressserver.repository;

import org.springframework.stereotype.Repository;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<BmsUser, Long>{
    BmsUser findByUsername(String username);
    Optional<BmsUser> findById(Long id);
    List<BmsUser> findByCompanyId(Long companyId);

}
