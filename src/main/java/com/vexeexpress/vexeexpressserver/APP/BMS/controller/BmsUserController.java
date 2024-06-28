package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.BmsUserService;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;

@RestController
@RequestMapping("/bms/user")
@CrossOrigin(origins = "http://localhost:5173")
public class BmsUserController {
    @Autowired
    BmsUserService bmsUserService;

    @GetMapping("/get-name-user/{id}")
    public ResponseEntity<?> getNameUser(@PathVariable Long id) {
        try {
            String name = bmsUserService.getNameUserById(id);
            if (name != null) {
                return ResponseEntity.ok(name);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi truy vấn thông tin người dùng");
        }
    }
    @PostMapping("/create-user")
    public BmsUser createUser(@RequestBody BmsUser bmsUser) {
        System.out.println(bmsUser);
        return bmsUserService.createUser(bmsUser);
    }

}
