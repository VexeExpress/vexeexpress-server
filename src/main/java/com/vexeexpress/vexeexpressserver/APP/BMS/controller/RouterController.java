package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.LevelAgency.LevelAgencyDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Router.RouterDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Router.RouterDTO_v2;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.CompanyService;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.RouterService;

import com.vexeexpress.vexeexpressserver.entity.BmsLevelAgency;
import com.vexeexpress.vexeexpressserver.entity.BmsRoute;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/bms/router")
@CrossOrigin(origins = "http://localhost:3000")
public class RouterController {
    @Autowired
    RouterService routerService;
    @Autowired
    CompanyService companyService;

    @GetMapping("/list-router/{companyId}")
    public ResponseEntity<?> getListRouteDetailByCompanyId(@PathVariable Long companyId) {
        try {
            List<RouterDTO> router = routerService.getListRouteDetailByCompanyId(companyId);

            if (router == null || router.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
            }
            return ResponseEntity.ok(router); // 200 OK
        } catch (IllegalArgumentException e) {
            // Xử lý khi companyId không hợp lệ
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request
        } catch (EntityNotFoundException e) {
            // CompanyId null
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> createRoute_2(@RequestBody RouterDTO dto) {
        try {
            BmsRoute route = routerService.createRoute_2(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(route);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRoute_v2(@PathVariable Long id, @RequestBody RouterDTO dto) {
        try {
            BmsRoute route = routerService.updateRoute_v2(id, dto);
            if (route != null) {
                return ResponseEntity.ok(route);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        System.out.println(id);
        try {
            routerService.deleteRoute(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/get-router-active/{companyId}")
//    public ResponseEntity<List<RouterDTO_v2>> getActiveRoutersByCompanyId(@PathVariable Long companyId) {
//        try {
//            System.out.println("CompanyId: " + companyId);
//            List<RouterDTO_v2> routers = routerService.getActiveRoutersByCompanyId(companyId);
//            System.out.println(routers);
//            return ResponseEntity.ok(routers);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Company not found or no active routers
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  // General error
//        }
//
//    }



}
