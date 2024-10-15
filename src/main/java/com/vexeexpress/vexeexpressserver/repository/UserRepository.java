package com.vexeexpress.vexeexpressserver.repository;

import io.micrometer.common.KeyValues;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<BmsUser, Long>{
    BmsUser findByUsername(String username);
    List<BmsUser> findByCompanyId(Long companyId);

//    boolean existsByUsername(String username);

    List<BmsUser> findAllByCompanyId(Long companyId);

    @Query("SELECT u FROM BmsUser u WHERE u.company.id = :companyId AND u.role IN (1, 2)")
    List<BmsUser> findEmployeesByCompanyId(@Param("companyId") Long companyId);

    @Query("SELECT u FROM BmsUser u WHERE u.company.id = :companyId AND u.role = 3")
    List<BmsUser> findDriversByCompanyId(@Param("companyId") Long companyId);

    List<BmsUser> findByIdIn(List<Long> ids);

    List<BmsUser> findByCompanyIdAndRole(Long companyId, int role);

    boolean existsByUsernameAndCompany_Id(String username, Long companyId);
}
