package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.Bms_AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth/bms")
@CrossOrigin(origins = "http://localhost:3000")
public class Bms_AuthController {
    @Autowired
    Bms_AuthService bmsAuthService;
    @PostMapping("/login-app")
    public String loginApp(@RequestBody Map<String, String> loginData){
        String username = loginData.get("username");
        String password = loginData.get("password");
        try {
            return bmsAuthService.loginApp(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
}
