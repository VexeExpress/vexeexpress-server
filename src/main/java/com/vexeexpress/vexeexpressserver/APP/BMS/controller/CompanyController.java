package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.CompanyService;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bms/company")
@CrossOrigin(origins = "http://localhost:5173")
public class CompanyController {
    @Autowired
    CompanyService companyService;

//    @GetMapping("/getCompanyIdByUserId/{userId}")
//    public Optional<BmsUser> getCompanyIdByUserId(@PathVariable Long userId) {
//        return companyService.getInfoUserByCompany(userId);
//    }
}
