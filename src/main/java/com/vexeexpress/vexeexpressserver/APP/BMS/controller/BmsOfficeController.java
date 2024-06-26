package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.entity.BmsOffice;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.BmsOfficeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bms/office")
@CrossOrigin(origins = "http://localhost:5173") 
public class BmsOfficeController {

    @Autowired
    private BmsOfficeService bmsOfficeService;

    @PostMapping("/create-office")
    public ResponseEntity<BmsOffice> createOffice(@RequestBody BmsOffice bmsOffice) {
        BmsOffice createdOffice = bmsOfficeService.createOffice(bmsOffice);
        return ResponseEntity.ok(createdOffice);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<BmsOffice>> getOfficesByCompanyId(@PathVariable Long companyId) {
        List<BmsOffice> offices = bmsOfficeService.getOfficesByCompanyId(companyId);
        return ResponseEntity.ok(offices);
    }
}
