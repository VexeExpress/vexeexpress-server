package com.vexeexpress.vexeexpressserver.ADMIN.controller;

import com.vexeexpress.vexeexpressserver.ADMIN.entity.Account;
import com.vexeexpress.vexeexpressserver.ADMIN.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/login-admin")
    public String login(@RequestBody Account account){
        System.out.println("Dữ liệu đầu vào từ client: " + account.toString());
        return authenticationService.login(account);
    }
}
