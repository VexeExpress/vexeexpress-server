package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.RouterService;
import com.vexeexpress.vexeexpressserver.entity.BmsRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bms/router")
@CrossOrigin(origins = "http://localhost:5173")
public class RouterController {
    @Autowired
    RouterService routerService;

    // Tạo tuyến đường mới
    @PostMapping("/create-router")
    public BmsRouter createRouter(@RequestBody BmsRouter bmsRouter){
        System.out.println(bmsRouter);
        return routerService.createRouter(bmsRouter);
    }
    // Hiện thị danh sách tuyến
    @GetMapping("/get-router/{companyId}")
    public List<BmsRouter> getRouterByCompanyId(@PathVariable Long companyId){
        return routerService.getRouterByCompanyId(companyId);
    }
}
