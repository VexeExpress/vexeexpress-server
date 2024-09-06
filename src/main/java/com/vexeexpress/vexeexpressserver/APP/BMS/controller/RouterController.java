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

import java.util.List;

@RestController
@RequestMapping("/bms/router")
@CrossOrigin(origins = "http://localhost:5173")
public class RouterController {
    @Autowired
    RouterService routerService;
    @Autowired
    CompanyService companyService;

    // Tạo tuyến đường mới
    @PostMapping("/create-router")
    public ResponseEntity<BmsRouter> createRouter(@RequestBody RouterDTO routerDTO) {
        try {
            System.out.println("Dữ liệu tuyến mới: " + routerDTO);
            // Fetch the company by companyId
            BmsBusCompany company = companyService.getCompanyById(routerDTO.getCompanyId());
            if (company == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 if company not found
            }

            // Create a new BmsRouter and set the values from DTO
            BmsRouter bmsRouter = new BmsRouter();
            bmsRouter.setName(routerDTO.getName());
            bmsRouter.setShortName(routerDTO.getShortName());
            bmsRouter.setPrice(routerDTO.getPrice());
            bmsRouter.setNote(routerDTO.getNote());
            bmsRouter.setCompany(company); // Set the fetched company

            // Save the router
            BmsRouter createdRouter = routerService.createRouter(bmsRouter);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdRouter); // Return 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
        }
    }
    // Hiện thị danh sách tuyến
    @GetMapping("/get-router/{companyId}")
    public List<RouterDTO> getRouterByCompanyId(@PathVariable Long companyId) {
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
    public ResponseEntity<BmsRouter> updateRoute(@PathVariable Long routeId, @RequestBody RouterDTO routerDTO) {
        try {
            // Fetch the existing route by routeId
            BmsRouter existingRouter = routerService.getRouterById(routeId);
            if (existingRouter == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found if route doesn't exist
            }

            // Fetch the company by companyId from the DTO
            BmsBusCompany company = companyService.getCompanyById(routerDTO.getCompanyId());
            if (company == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found if company doesn't exist
            }

            // Update the fields of the existing router
            existingRouter.setName(routerDTO.getName());
            existingRouter.setShortName(routerDTO.getShortName());
            existingRouter.setPrice(routerDTO.getPrice());
            existingRouter.setNote(routerDTO.getNote());
            existingRouter.setCompany(company); // Update the company

            // Save the updated route
            BmsRouter updatedRouter = routerService.updateRouter(existingRouter);
            return ResponseEntity.ok(updatedRouter); // Return 200 OK with the updated route

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error if an error occurs
        }
    }

}
