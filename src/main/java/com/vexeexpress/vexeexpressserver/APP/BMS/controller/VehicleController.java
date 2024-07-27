package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.VehicleService;
import com.vexeexpress.vexeexpressserver.entity.BmsVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    // Update vehicle by ID
    @PutMapping("/update-vehicle/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody BmsVehicle updatedVehicle) {
        try {
            BmsVehicle vehicle = vehicleService.updateVehicle(id, updatedVehicle);
            return ResponseEntity.ok(vehicle);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating vehicle: " + e.getMessage());
        }
    }

    // Delete vehicle by ID
    @DeleteMapping("/delete-vehicle/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        try {
            String result = vehicleService.deleteVehicle(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting vehicle: " + e.getMessage());
        }
    }
}
