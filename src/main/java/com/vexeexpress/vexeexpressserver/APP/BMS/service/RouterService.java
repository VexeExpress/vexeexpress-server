package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Router.RouterDTO;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsRoute;
import com.vexeexpress.vexeexpressserver.repository.CompanyRepository;
import com.vexeexpress.vexeexpressserver.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouterService {
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    CompanyRepository companyRepository;








//    public List<RouterDTO_v2> getActiveRoutersByCompanyId(Long companyId) {
//        List<BmsRouter> routers = routerRepository.findAllActiveRoutersByCompanyId(companyId);
//        if (routers.isEmpty()) {
//            throw new EntityNotFoundException("Công ty không tồn tại hoặc không có router nào hoạt động.");
//        }
//        System.out.println(routers);
//
//        return routers.stream()
//                .map(router -> new RouterDTO_v2(
//                        router.getId(),
//                        router.getRouteName(),
//                        router.getDisplayPrice()
//                ))
//                .collect(Collectors.toList());
//    }

    public List<RouterDTO> getListRouteDetailByCompanyId(Long companyId) {
        if (companyId == null) {
            throw new IllegalArgumentException("companyId must not be null");
        }
        List<BmsRoute> routers = routeRepository.findByCompanyId(companyId);
        if (routers == null || routers.isEmpty()) {
            return null;
        }
        return routers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private RouterDTO convertToDTO(BmsRoute router) {
        RouterDTO dto = new RouterDTO();
        dto.setId(router.getId());
        dto.setRouteName(router.getRouteName());
        dto.setRouteNameShort(router.getRouteNameShort());
        dto.setDisplayPrice(router.getDisplayPrice());
        dto.setStatus(router.getStatus());
        return dto;
    }

    public BmsRoute createRouter_2(RouterDTO dto) {
        Optional<BmsBusCompany> companyOpt = companyRepository.findById(dto.getCompanyId());
        if (companyOpt.isEmpty()) {
            throw new IllegalArgumentException("Company ID không hợp lệ.");
        }
//        if (routeRepository.existsByRouterNameAndCompany_Id(dto.getRouteName(), dto.getCompanyId())) {
//            throw new IllegalArgumentException("Phương tiện đã tồn tại trong công ty này.");
//        }
        BmsRoute router = new BmsRoute();
        router.setRouteName(dto.getRouteName());
        router.setRouteNameShort(dto.getRouteNameShort());
        router.setNote(dto.getNote());
        router.setStatus(dto.getStatus());
        router.setDisplayPrice(dto.getDisplayPrice());
        router.setCompany(companyOpt.get());
        return routeRepository.save(router);
    }

    public BmsRoute updateRouter_v2(Long id, RouterDTO dto) {
        Optional<BmsRoute> routerOptional = routeRepository.findById(id);
        if (routerOptional.isPresent()) {
            BmsRoute currentRouter = routerOptional.get();
            Optional<BmsBusCompany> companyOptional = companyRepository.findById(dto.getCompanyId());
            if(companyOptional.isEmpty()){
                throw new IllegalArgumentException("Company ID không hợp lệ.");
            }
//            if (!currentRouter.getRouteName().equals(dto.getRouteName())) {
//                boolean routerNameExists = routeRepository.existsByRouteNameAndCompany_IdAndIdNot(dto.getRouteName(), dto.getCompanyId(), currentRouter.getId());
//                if (routerNameExists) {
//                    System.out.println("Tuyến: " + dto.getRouteName() + " đã tồn tại trong công ty: " + dto.getCompanyId());
//                    return null;
//                }
//            }
            currentRouter.setRouteName(dto.getRouteName());
            currentRouter.setRouteNameShort(dto.getRouteNameShort());
            currentRouter.setStatus(dto.getStatus());
            currentRouter.setNote(dto.getNote());
            currentRouter.setDisplayPrice(dto.getDisplayPrice());
            return routeRepository.save(currentRouter);
        } else {
            return null;
        }
    }

    public void deleteRouter_v2(Long id) throws Exception {
        if (routeRepository.existsById(id)) {
            routeRepository.deleteById(id);
        } else {
            throw new Exception("Router not found");
        }
    }
}
