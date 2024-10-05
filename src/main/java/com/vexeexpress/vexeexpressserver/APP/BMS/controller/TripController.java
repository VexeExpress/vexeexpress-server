package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Trip.TripDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Trip.TripDTO_v2;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.CompanyService;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.TripService;
import com.vexeexpress.vexeexpressserver.entity.BmsTrip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/bms/trip")
@CrossOrigin(origins = "http://localhost:3000")
public class TripController {
    @Autowired
    TripService tripService;
    @Autowired
    CompanyService companyService;

    @PostMapping("/create-trip")
    public ResponseEntity<?> createTrip(@RequestBody TripDTO tripDTO) {
        try {

            BmsTrip createdTrip = tripService.createTrip(tripDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTrip);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data provided.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
    @GetMapping("/search")
    public ResponseEntity<List<TripDTO_v2>> searchTrips(
            @RequestParam Long companyId,
            @RequestParam LocalDate dateTrip,
            @RequestParam Long routerId) {
        try {
            List<TripDTO_v2> trips = tripService.searchTrips(companyId, dateTrip, routerId);
            return ResponseEntity.ok(trips);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(null); // Return 400 Bad Request
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Return 500 Internal Server Error
        }
    }

//    @GetMapping("/get-info-trip/{tripId}")
//    public ResponseEntity<TripDTO_v3> getTripDetails(@PathVariable Long tripId) {
//        try {
//            TripDTO_v3 tripDetails = tripService.getTripDetails(tripId);
//            if (tripDetails != null) {
//                return ResponseEntity.ok(tripDetails);
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }



//    @GetMapping("/search")
//    public ResponseEntity<List<TripDTO_v2>> searchTrips(
//            @RequestParam(value = "valueRouter") Long valueRouter,
//            @RequestParam(value = "valueDayDeparture") String valueDayDeparture,
//            @RequestParam(value = "companyId") Long companyId
//    ) {
//        try {
//            // Call the service to fetch trips and convert them to DTOs
//            List<TripDTO_v2> trips = tripService.searchTrips(valueRouter, valueDayDeparture, companyId);
//
//            // If no trips are found, return a 204 No Content response
//            if (trips.isEmpty()) {
//                return ResponseEntity.noContent().build();
//            }
//
//            // Return the list of trips in the response body
//            return ResponseEntity.ok(trips);
//        } catch (Exception e) {
//            // Return a 500 Internal Server Error for other exceptions
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

    @GetMapping("/get-all-trips")
    public List<BmsTrip> getAllTrips() {
        return tripService.getAllTrips();
    }

//    @GetMapping("/get-trip-by-company-id/{companyId}")
//    public ResponseEntity<List<TripDTO>> getInfoTrip(@PathVariable Long companyId) {
//        try {
//            System.out.println("CompanyID: " + companyId);
//            List<TripDTO> tripDTOList = tripService.getInfoTrip(companyId);
//
//            if (tripDTOList.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//            }
//
//            return ResponseEntity.ok(tripDTOList);
//        } catch (NoSuchElementException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(null);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
//        }
//    }
}
