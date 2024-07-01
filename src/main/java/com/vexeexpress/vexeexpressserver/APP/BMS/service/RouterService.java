package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.entity.BmsRouter;
import com.vexeexpress.vexeexpressserver.repository.RouterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
