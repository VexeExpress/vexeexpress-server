package com.vexeexpress.vexeexpressserver.APP.ADMIN.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vexeexpress.vexeexpressserver.APP.ADMIN.service.AdminUserService;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    
    
}
