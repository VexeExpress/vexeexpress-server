package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.RouterService;
import com.vexeexpress.vexeexpressserver.entity.BmsRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/bms/router")
@CrossOrigin(origins = "http://localhost:5173")
public class RouterController {
    @Autowired
    RouterService routerService;

    // Tạo tuyến đường mới
    @PostMapping("/create-router")
    public BmsRouter createRouter(@RequestBody BmsRouter bmsRouter) {
        System.out.println(bmsRouter);
        return routerService.createRouter(bmsRouter);
    }

    // Hiện thị danh sách tuyến
    @GetMapping("/get-router/{companyId}")
    public List<BmsRouter> getRouterByCompanyId(@PathVariable Long companyId) {
        return routerService.getRouterByCompanyId(companyId);
    }
    
    // Xóa 1 tuyến đường
    @DeleteMapping("/delete-route/{routeId}")
    public ResponseEntity<?> deleteRoute(@PathVariable Long routeId) {
            routerService.deleteRouteById(routeId);
            return ResponseEntity.ok().build();
    }

    // Cập nhật tuyến đường
    @PutMapping("/update-route/{routeId}")
    public ResponseEntity<BmsRouter> updateRoute(@PathVariable Long routeId, @RequestBody BmsRouter bmsRouter) {
            BmsRouter updatedRouter = routerService.updateRouter(routeId, bmsRouter);
            return ResponseEntity.ok(updatedRouter);
    }
}
