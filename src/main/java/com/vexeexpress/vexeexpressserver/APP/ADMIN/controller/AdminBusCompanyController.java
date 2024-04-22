package com.vexeexpress.vexeexpressserver.APP.ADMIN.controller;

import org.springframework.web.bind.annotation.RestController;

import com.vexeexpress.vexeexpressserver.APP.ADMIN.service.AdminBusCompanyService;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/admin/bus-company")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminBusCompanyController {
    @Autowired
    AdminBusCompanyService adminBusCompanyService;

    @PostMapping("/add-bus-company")
    public String addBusCompany(@RequestBody BmsBusCompany busCompany) {
        try {
            return adminBusCompanyService.addBusCompany(busCompany);
        } catch(Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
    @GetMapping("/get-list-bus-company")
    public List<BmsBusCompany> getBusCompany() {
        try {
            return adminBusCompanyService.getAllBusCompanies();
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("/get-company-info/{id}")
    public ResponseEntity<?> getBusCompanyById(@PathVariable Long id) {
        try {
            BmsBusCompany busCompany = adminBusCompanyService.getBusCompanyById(id);
            if (busCompany != null) {
                return ResponseEntity.ok(busCompany);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống: " + e.getMessage());
        }
    }
    
}
