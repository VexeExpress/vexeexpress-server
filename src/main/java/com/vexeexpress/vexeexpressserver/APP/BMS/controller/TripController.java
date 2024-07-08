package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.TripService;
import com.vexeexpress.vexeexpressserver.entity.BmsTrip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<BmsTrip>> searchTrips() {
        try {
//            List<BmsTrip> trips = tripService.findTripsByDateAndRoute(date, route);
//            return new ResponseEntity<>(trips, HttpStatus.OK);
            return null;
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
