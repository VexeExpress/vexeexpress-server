package com.vexeexpress.vexeexpressserver.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<BmsAgent, Long> {
    List<BmsAgent> findByCompanyId(Long companyId);

    Optional<Object> findByName(String name);

    boolean existsByNameAndCompany_Id(String name, Long companyId);

    boolean existsByNameAndCompany_IdAndIdNot(String name, Long companyId, Long id);
}
