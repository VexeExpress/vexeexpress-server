package com.vexeexpress.vexeexpressserver.APP.ADMIN.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vexeexpress.vexeexpressserver.APP.ADMIN.service.AdminUserService;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/admin/user")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;

    @PostMapping("/add-user")
    public String addUser(@RequestBody BmsUser bmsUser) {
        try {
            return adminUserService.addUser(bmsUser);
        } catch (Exception e) {
            e.printStackTrace();
            return "Lỗi hệ thống";
        }
    }
    @GetMapping("/get-list-user/{companyId}")
    public ResponseEntity<List<BmsUser>> getListUserByCompanyId(@PathVariable Long companyId) {
        try{
            List<BmsUser> listUsers=  adminUserService.getListUserByCompanyId(companyId);
            return ResponseEntity.ok(listUsers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
    
}
