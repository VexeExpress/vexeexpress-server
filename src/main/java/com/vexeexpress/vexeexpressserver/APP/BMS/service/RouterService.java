package com.vexeexpress.vexeexpressserver.APP.BMS.service;


import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Router.RouterDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Router.RouterDTO_v2;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsLevelAgency;
import com.vexeexpress.vexeexpressserver.entity.BmsRoute;
import com.vexeexpress.vexeexpressserver.repository.CompanyRepository;
import com.vexeexpress.vexeexpressserver.repository.RouteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouterService {
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    CompanyRepository companyRepository;

    public List<RouterDTO> getListRouteDetailByCompanyId(Long companyId) {
        if (companyId == null) {
            throw new IllegalArgumentException("companyId must not be null");
        }
        List<BmsRoute> routes = routeRepository.findByCompanyId(companyId);
        if(routes == null || routes.isEmpty()) {
            return null;
        }
        return routes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private RouterDTO convertToDTO(BmsRoute route) {
        RouterDTO dto = new RouterDTO();
        dto.setId(route.getId());
        dto.setRouteName(route.getRouteName());
        dto.setRouteNameShort(route.getRouteNameShort());
        dto.setNote(route.getNote());
        dto.setStatus(route.getStatus());
        dto.setDisplayPrice(route.getDisplayPrice());
        return dto;
    }

    public BmsRoute createRoute_2(RouterDTO dto) {
        Optional<BmsBusCompany> companyOpt = companyRepository.findById(dto.getCompanyId());
        if (companyOpt.isEmpty()) {
            throw new IllegalArgumentException("Company ID không hợp lệ.");
        }
        if (routeRepository.existsByRouteNameAndCompany_Id(dto.getRouteName(), dto.getCompanyId())) {
            throw new IllegalArgumentException("Cấp đại lý đã tồn tại trong công ty này.");
        }
        BmsRoute route = new BmsRoute();
        route.setRouteName(dto.getRouteName());
        route.setRouteNameShort(dto.getRouteNameShort());
        route.setNote(dto.getNote());
        route.setStatus(dto.getStatus());
        route.setDisplayPrice(dto.getDisplayPrice());
        route.setCompany(companyOpt.get());
        return routeRepository.save(route);
    }

    public BmsRoute updateRoute_v2(Long id, RouterDTO dto) {
        Optional<BmsRoute> routeOptional = routeRepository.findById(id);
        if (routeOptional.isPresent()) {
            BmsRoute currentRoute = routeOptional.get();
            Optional<BmsBusCompany> companyOpt = companyRepository.findById(dto.getCompanyId());
            if (companyOpt.isEmpty()) {
                throw new IllegalArgumentException("Company ID không hợp lệ.");
            }
            if (!currentRoute.getRouteName().equals(dto.getRouteName())) {
                boolean routeExists = routeRepository.existsByRouteNameAndCompany_IdAndIdNot(dto.getRouteName(), dto.getCompanyId(), currentRoute.getId());
                if (routeExists) {
                    System.out.println("Cấp đại lý: " + dto.getRouteName() + " đã tồn tại trong công ty: " + dto.getCompanyId());
                    return null;
                }
            }
            currentRoute.setRouteName(dto.getRouteName());
            currentRoute.setRouteNameShort(dto.getRouteNameShort());
            currentRoute.setNote(dto.getNote());
            currentRoute.setStatus(dto.getStatus());
            currentRoute.setDisplayPrice(dto.getDisplayPrice());

            return routeRepository.save(currentRoute);
        } else {
            return null;
        }
    }

    public void deleteRoute(Long id) throws Exception {
        if (routeRepository.existsById(id)) {
            routeRepository.deleteById(id);
        } else {
            throw new Exception("Route not found");
        }
    }




    public List<RouterDTO_v2> getActiveRoutersByCompanyId(Long companyId) {
        List<BmsRoute> routers = routeRepository.findAllActiveRoutersByCompanyId(companyId);
        if (routers.isEmpty()) {
            throw new EntityNotFoundException("Công ty không tồn tại hoặc không có router nào hoạt động.");
        }

        return routers.stream()
                .map(router -> new RouterDTO_v2(
                        router.getId(),
                        router.getRouteName()
                ))
                .collect(Collectors.toList());
    }
}
