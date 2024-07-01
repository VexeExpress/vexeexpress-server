package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.VehicleService;
import com.vexeexpress.vexeexpressserver.entity.BmsVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bms/vehicle")
@CrossOrigin(origins = "http://localhost:5173")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    // Tạo phương tiện mới
    @PostMapping("/create-vehicle")
    public BmsVehicle createVehicle(@RequestBody BmsVehicle bmsVehicle){
        System.out.println(bmsVehicle);
        return vehicleService.createVehicle(bmsVehicle);
    }
    // Hiện thị danh sách phương tiện
    @GetMapping("/get-vehicle/{companyId}")
    public List<BmsVehicle> getVehiclesByCompanyId(@PathVariable Long companyId){
        return vehicleService.getVehiclesByCompanyId(companyId);
    }
}
