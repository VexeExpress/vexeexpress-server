package com.vexeexpress.vexeexpressserver.VE.controller;

import com.vexeexpress.vexeexpressserver.VE.entity.VeUser;
import com.vexeexpress.vexeexpressserver.VE.service.VeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/ve_user")
@CrossOrigin(origins = "http://localhost:3000")
public class VeUserController {
    @Autowired
    private VeUserService veUserService;
    @PostMapping("/register")
    public String register(@RequestBody VeUser veUser) {
        System.out.println("Received request with data: " + veUser);
        return veUserService.register(veUser);
    }
    @PostMapping("/login")
    public String login(@RequestBody VeUser veUser){
        return veUserService.login(veUser);
    }
    @PostMapping("/login-google")
    public String loginGoogle(@RequestBody VeUser veUser){
        return veUserService.loginGoogle(veUser);
    }
    @PostMapping("/login-facebook")
    public String loginFacebook(@RequestBody VeUser veUser){
        return veUserService.loginFacebook(veUser);
    }
}
