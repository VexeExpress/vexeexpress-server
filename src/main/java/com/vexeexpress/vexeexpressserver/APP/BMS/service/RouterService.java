package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.entity.BmsRouter;
import com.vexeexpress.vexeexpressserver.repository.RouterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RouterService {
    @Autowired
    RouterRepository routerRepository;
    public BmsRouter createRouter(BmsRouter bmsRouter) {
        return routerRepository.save(bmsRouter);
    }

    public List<BmsRouter> getRouterByCompanyId(Long companyId) {
        return routerRepository.findByCompanyId(String.valueOf(companyId));
    }

    public void deleteRouteById(Long routeId) {
        if (!routerRepository.existsById(routeId)) {
            throw new NoSuchElementException("Route not found");
        }
        routerRepository.deleteById(routeId);
    }

    public BmsRouter updateRouter(Long routeId, BmsRouter bmsRouter) {
        BmsRouter existingRouter = routerRepository.findById(routeId).orElseThrow(NoSuchElementException::new);
        existingRouter.setName(bmsRouter.getName());
        existingRouter.setShortName(bmsRouter.getShortName());
        existingRouter.setPrice(bmsRouter.getPrice());
        existingRouter.setNote(bmsRouter.getNote());
        // Set other fields as necessary
        return routerRepository.save(existingRouter);
    }

}
