package com.vexeexpress.vexeexpressserver.repository;

import com.vexeexpress.vexeexpressserver.entity.BmsRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteRepository extends JpaRepository<BmsRoute, Long> {
    List<BmsRoute> findByCompanyId(Long companyId);

    boolean existsByRouteNameAndCompany_Id(String routeName, Long companyId);

    boolean existsByRouteNameAndCompany_IdAndIdNot(String routeName, Long companyId, Long id);

    List<BmsRoute> findAllActiveRoutersByCompanyId(Long companyId);

//    boolean existsByRouteNameAndCompany_IdAndIdNot(String routeName, Long companyId, Long id);
//
//    boolean existsByRouterNameAndCompany_Id(String routeName, Long companyId);

//    @Query("SELECT r FROM BmsRouter r WHERE r.company.id = :companyId AND r.status = true")
//    List<BmsRouter> findAllActiveRoutersByCompanyId(@Param("companyId")
}
