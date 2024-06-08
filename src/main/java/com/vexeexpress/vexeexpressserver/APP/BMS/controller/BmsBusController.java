package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.BmsBusService;
import com.vexeexpress.vexeexpressserver.APP.BMS.utils.BmsBusRequest;
import com.vexeexpress.vexeexpressserver.entity.BmsBus;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/bms/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class BmsBusController {

    @Autowired
    BmsBusService bmsBusService;

    @PostMapping("/createBusInfo")
    public ResponseEntity<?> createBus(@RequestBody BmsBusRequest busRequest) {
        try {
            BmsBus bus = bmsBusService.createBus(
                    busRequest.getBusNumber(),
                    busRequest.getLicensePlate(),
                    busRequest.getCapacity(),
                    busRequest.getStatus(),
                    busRequest.getCompanyId());
            return ResponseEntity.ok(bus);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAllBusInfo")
    public ResponseEntity<List<BmsBus>> getAllBuses() {
        List<BmsBus> buses = bmsBusService.getAllBuses();
        return ResponseEntity.ok(buses);
    }

    @GetMapping("/getBusesBySimilarNumber/{busNumber}")
    public ResponseEntity<List<BmsBus>> getBusesBySimilarNumber(@PathVariable String busNumber) {
        List<BmsBus> buses = bmsBusService.getBusesBySimilarNumber(busNumber);
        return ResponseEntity.ok(buses);
    }

    @DeleteMapping("/deleteBus/{id}")
    public ResponseEntity<?> deleteBusById(@PathVariable Long id) {
        try {
            bmsBusService.deleteBusById(id);
            return ResponseEntity.ok("Bus deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteAllBuses")
    public ResponseEntity<?> deleteAllBuses() {
        bmsBusService.deleteAllBuses();
        return ResponseEntity.ok("All buses deleted successfully");
    }
}
