package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.RouterDTO;
import com.vexeexpress.vexeexpressserver.entity.BmsRouter;
import com.vexeexpress.vexeexpressserver.repository.RouterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouterService {
    @Autowired
    RouterRepository routerRepository;
    public BmsRouter createRouter(BmsRouter bmsRouter) {
        return routerRepository.save(bmsRouter);
    }


    public List<RouterDTO> getRouterByCompanyId(Long companyId) {
        // Lấy danh sách BmsRouter theo companyId
        List<BmsRouter> routers = routerRepository.findByCompanyId(companyId);

        // Chuyển đổi từ BmsRouter sang RouterDTO và trả về kết quả
        return routers.stream()
                .map(router -> {
                    RouterDTO dto = new RouterDTO();
                    dto.setId(router.getId());
                    dto.setRouteName(router.getRouteName());
                    dto.setRouteNameShort(router.getRouteNameShort());
                    dto.setDisplayPrice(router.getDisplayPrice());
                    dto.setStatus(router.getStatus());
                    dto.setNote(router.getNote());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public boolean deleteRouteById(Long routeId) {
        Optional<BmsRouter> router = routerRepository.findById(routeId);
        if (router.isPresent()) {
            routerRepository.delete(router.get());
            return true; // Xóa thành công
        }
        return false; // Không tìm thấy tuyến đường
    }


    public BmsRouter getRouterById(Long routeId) {
        Optional<BmsRouter> optionalRouter = routerRepository.findById(routeId);
        return optionalRouter.orElse(null);
    }

    public BmsRouter updateRouter(BmsRouter updatedRouter) {
        return routerRepository.save(updatedRouter);
    }
}
