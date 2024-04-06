package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.ADMIN.service.BmsUserService;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.BmsAuthService;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bms")
@CrossOrigin(origins = "http://localhost:3000")
public class BmsAuthController {
    @Autowired
    BmsAuthService bmsAuthService;

    @PostMapping("/login-app")
    public String loginApp(@RequestBody BmsUser bmsUser) {
        return bmsAuthService.loginApp(bmsUser);
    }
}
