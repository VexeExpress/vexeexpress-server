package com.vexeexpress.vexeexpressserver.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsRouter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouterRepository extends JpaRepository<BmsRouter, Long> {

    List<BmsRouter> findByCompanyId(Long companyId);

    @Query("SELECT r FROM BmsRouter r WHERE r.company.id = :companyId AND r.status = true")
    List<BmsRouter> findAllActiveRoutersByCompanyId(@Param("companyId") Long companyId);
}
