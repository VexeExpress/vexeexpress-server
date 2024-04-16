package com.vexeexpress.vexeexpressserver.APP.ADMIN.controller;

import com.vexeexpress.vexeexpressserver.APP.ADMIN.service.AdminBusCompanyService;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/bus-company")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminBusCompanyController {
    @Autowired
    AdminBusCompanyService adminBusCompanyService;
    @PostMapping("/add-bus-company")
    public String addBusCompany(@RequestBody BmsBusCompany bmsBusCompany){
        System.out.println(bmsBusCompany);
        return adminBusCompanyService.addBusCompany(bmsBusCompany);
    }
    @GetMapping("/get-processed-bus-companies")
    public List<BmsBusCompany> getProcessedBusCompanies(){
        return adminBusCompanyService.getProcessedBusCompanies();
    }
    @GetMapping("/get-company-info/{id}")
    public ResponseEntity<?> getCompanyInfoById(@PathVariable String id){
        try{
            System.out.println(id);
            BmsBusCompany company = adminBusCompanyService.getCompanyInfoById(id);
            if (company != null) {
                return ResponseEntity.ok(company);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy công ty với ID: " + id); // Trả về thông điệp lỗi 404
            }
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống: " + ex.getMessage());
        }
    }
}
