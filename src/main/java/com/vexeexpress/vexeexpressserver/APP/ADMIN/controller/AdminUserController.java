package com.vexeexpress.vexeexpressserver.APP.ADMIN.controller;

import com.vexeexpress.vexeexpressserver.APP.ADMIN.service.AdminUserService;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;
    @PostMapping("/add-user")
    public String addUser(@RequestBody  BmsUser bmsUser){
        try {
            System.out.println(bmsUser);
            return adminUserService.addUser(bmsUser);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
    @GetMapping("/get-list-user/{companyId}")
    public List<BmsUser> getListUserByCompanyId(@PathVariable String companyId) {
        return adminUserService.getListUserByCompanyId(companyId);
    }

}
