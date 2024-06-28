package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.BmsOfficeService;
import com.vexeexpress.vexeexpressserver.entity.BmsOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bms/office")
@CrossOrigin(origins = "http://localhost:5173")
public class BmsOfficeController {
    @Autowired
    BmsOfficeService bmsOfficeService;
    // Tạo văn phòng mới
    @PostMapping("/create-office")
    public BmsOffice createOffice(@RequestBody BmsOffice bmsOffice){
        System.out.println(bmsOffice);
        return bmsOfficeService.createOffice(bmsOffice);
    };
    // Hiện danh sách văn phòng dựa theo companyId
    @GetMapping("/offices/{companyId}")
    public List<BmsOffice> getOfficesByCompanyId(@PathVariable String companyId) {
        return bmsOfficeService.getOfficesByCompanyId(companyId);
    }


}