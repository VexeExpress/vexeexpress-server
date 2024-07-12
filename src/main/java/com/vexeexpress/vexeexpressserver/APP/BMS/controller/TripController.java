package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.TripService;
import com.vexeexpress.vexeexpressserver.entity.BmsTrip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bms/trip")
@CrossOrigin(origins = "http://localhost:5173")
public class TripController {
    @Autowired
    TripService tripService;

    @PostMapping("/create-trip")
    public BmsTrip createTrip(@RequestBody BmsTrip bmsTrip){
        System.out.println(bmsTrip);
        return tripService.createTrip(bmsTrip);
    }

    @GetMapping("/search")
    public List<BmsTrip> searchTrips(
            @RequestParam("valueRouter") String valueRouter,
            @RequestParam("valueDayDeparture") String valueDayDeparture,
            @RequestParam("companyId") String companyId
    ) {

        return tripService.searchTrips(valueRouter, valueDayDeparture, companyId);
    }

    @GetMapping("/get-all-trips")
    public List<BmsTrip> getAllTrips() {
        return tripService.getAllTrips();
    }

    @GetMapping("/get-info-trip/{id}")
    public Optional<BmsTrip> getInfoTip(@PathVariable Long id){
        return tripService.getInfoTrip(id);
    }
}
