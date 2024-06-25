package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.entity.BmsOffice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bms/declare")
@CrossOrigin(origins = "http://localhost:5173")
public class BmsOfficeController {
    @PostMapping("/create-office")
    public String createOffice(@RequestBody BmsOffice bmsOffice){
        System.out.println(bmsOffice);
        return "2";
    };
}
