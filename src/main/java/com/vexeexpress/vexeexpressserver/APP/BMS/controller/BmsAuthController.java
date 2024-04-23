package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.BmsAuthService;
import com.vexeexpress.vexeexpressserver.APP.BMS.utils.LoginForm;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/bms/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class BmsAuthController {
    @Autowired
    BmsAuthService bmsAuthService;
    @PostMapping("/login")
    public String login(@RequestBody LoginForm loginForm) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        
        return bmsAuthService.login(username, password);
    }
    
}
