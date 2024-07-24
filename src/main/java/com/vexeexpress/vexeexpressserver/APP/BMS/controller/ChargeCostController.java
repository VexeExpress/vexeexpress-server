package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.ChargeCostService;
import com.vexeexpress.vexeexpressserver.entity.BmsChargeCost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bms/chargecost")
@CrossOrigin(origins = "http://localhost:5173")
public class ChargeCostController {
    @Autowired
    ChargeCostService chargeCostService;

    @PostMapping("/add-chargecost")
    public BmsChargeCost addChargeCost(@RequestBody BmsChargeCost bmsChargeCost) {
        return chargeCostService.add(bmsChargeCost);
    }

    @PatchMapping("/update-chargecost")
    public BmsChargeCost updateChargeCost(@RequestBody BmsChargeCost bmsChargeCost) {
        return chargeCostService.update(bmsChargeCost);
    }

    @GetMapping("/get-chargecost/{tripId}")
    public BmsChargeCost findByTripId(@PathVariable Long tripId) {
        return chargeCostService.findByTripId(tripId);
    }
}
