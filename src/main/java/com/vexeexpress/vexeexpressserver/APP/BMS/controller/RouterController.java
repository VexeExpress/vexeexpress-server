package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.RouterDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.CompanyService;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.RouterService;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsRouter;
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

    // Tạo tuyến đường mới
    @PostMapping("/create-router")
    public ResponseEntity<BmsRouter> createRouter(@RequestBody BmsRouter bmsRouter) {
        try {
            System.out.println("Dữ liệu tuyến mới: " + bmsRouter);
            // Fetch the company by companyId
            BmsBusCompany company = companyService.getCompanyById(bmsRouter.getCompany().getId());
            if (company == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 if company not found
            }
            // Save the router
            BmsRouter createdRouter = routerService.createRouter(bmsRouter);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdRouter); // Return 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
        }
    }
    @GetMapping("/get-router/{companyId}")
    public ResponseEntity<?> getRouterByCompanyId(@PathVariable Long companyId) {
        try {
            List<RouterDTO> routers = routerService.getRouterByCompanyId(companyId);
            if (routers.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Không tìm thấy tuyến nào cho công ty ID: " + companyId);
            }
            return ResponseEntity.ok(routers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Đã xảy ra lỗi trong quá trình lấy danh sách tuyến. Vui lòng thử lại sau.");
        }
    }

    // Xóa 1 tuyến đường
    @DeleteMapping("/delete-router/{routeId}")
    public ResponseEntity<?> deleteRoute(@PathVariable Long routeId) {
        try {
            boolean deleted = routerService.deleteRouteById(routeId);
            if (!deleted) {
                // Nếu không tìm thấy tuyến đường để xóa
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tuyến đường không tồn tại.");
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Xử lý lỗi khác nếu có
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống, vui lòng thử lại sau.");
        }
    }

    @PutMapping("/update-router/{routeId}")
    public ResponseEntity<?> updateRoute(@PathVariable Long routeId, @RequestBody RouterDTO routerDTO) {
        try {
            System.out.println("Data: " + routerDTO);
            // Fetch the existing route by routeId
            BmsRouter existingRouter = routerService.getRouterById(routeId);
            if (existingRouter == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tuyến không tồn tại."); // 404 Not Found
            }


            // Update the fields of the existing router
            existingRouter.setRouteName(routerDTO.getRouteName());
            existingRouter.setRouteNameShort(routerDTO.getRouteNameShort());
            existingRouter.setDisplayPrice(routerDTO.getDisplayPrice());
            existingRouter.setNote(routerDTO.getNote());
            existingRouter.setStatus(routerDTO.getStatus());

            // Save the updated route
            BmsRouter updatedRouter = routerService.updateRouter(existingRouter);
            return ResponseEntity.ok(updatedRouter); // Return 200 OK with the updated route

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống, vui lòng thử lại sau."); // 500 Internal Server Error
        }
    }

}
