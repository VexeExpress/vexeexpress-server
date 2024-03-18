package com.vexeexpress.vexeexpressserver.VE.controller;

import com.vexeexpress.vexeexpressserver.VE.entity.VeUser;
import com.vexeexpress.vexeexpressserver.VE.service.VeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ve_user")
public class VeUserController {
    @Autowired
    private VeUserService veUserService;
    @GetMapping("/v1")
    public String getAll() {
        return "Danh sách";
    }
    @PostMapping("/register")
    public String register(@RequestBody VeUser veUser) {
        return veUserService.register(veUser);
    }
}
