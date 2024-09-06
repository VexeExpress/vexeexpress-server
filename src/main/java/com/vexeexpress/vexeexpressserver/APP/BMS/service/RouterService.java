package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.RouterDTO;
import com.vexeexpress.vexeexpressserver.entity.BmsRouter;
import com.vexeexpress.vexeexpressserver.repository.RouterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class RouterService {
    @Autowired
    RouterRepository routerRepository;
    public BmsRouter createRouter(BmsRouter bmsRouter) {
        return routerRepository.save(bmsRouter);
    }

    public List<RouterDTO> getRouterByCompanyId(Long companyId) {
        List<BmsRouter> routers = routerRepository.findByCompanyId(companyId);
        return routers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private RouterDTO convertToDTO(BmsRouter router) {
        RouterDTO dto = new RouterDTO();
        dto.setId(router.getId());
        dto.setName(router.getName());
        dto.setShortName(router.getShortName());
        dto.setPrice(router.getPrice());
        dto.setNote(router.getNote());
        dto.setCompanyId(router.getCompany() != null ? router.getCompany().getId() : null); // Extract companyId
        return dto;
    }

    public void deleteRouteById(Long routeId) {
        if (!routerRepository.existsById(routeId)) {
            throw new NoSuchElementException("Route not found");
        }
        routerRepository.deleteById(routeId);
    }

    public BmsRouter updateRouter(BmsRouter bmsRouter) {
        return routerRepository.save(bmsRouter);
    }

    public BmsRouter getRouterById(Long routeId) {
        return routerRepository.findById(routeId).orElse(null);
    }
}
