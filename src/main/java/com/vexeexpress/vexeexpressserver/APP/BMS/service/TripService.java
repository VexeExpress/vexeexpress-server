package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.entity.BmsTrip;
import com.vexeexpress.vexeexpressserver.entity.BmsVehicle;
import com.vexeexpress.vexeexpressserver.repository.TripRepository;
import com.vexeexpress.vexeexpressserver.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TripService {
    @Autowired
    TripRepository tripRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    public BmsTrip createTrip(BmsTrip bmsTrip) {
        return tripRepository.save(bmsTrip);
    }

    public List<BmsTrip> findTripsByDateAndRoute(String date, int route) {
        return null;

    }



    public List<BmsTrip> searchTrips(String valueRouter, String valueDayDeparture, String companyId) {
        System.out.println("Received request: valueRouter=" + valueRouter + ", valueDayDeparture=" + valueDayDeparture + ", companyId=" + companyId);
        List<BmsTrip> listTrip = tripRepository.findByCompanyId(companyId);
//        System.out.println("Found trips: " + listTrip);
        System.out.println("Value Day Departure: " + valueDayDeparture);

        List<BmsTrip> listValueRouterTrip = tripRepository.findByValueRouter(valueRouter);
        System.out.println("Found value router: " + listValueRouterTrip);
        LocalDate date = LocalDate.parse(valueDayDeparture, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.println("Date short: " + date);

        List<BmsTrip> filteredTrips = listValueRouterTrip.stream()
                .filter(trip -> trip.getValueDayDeparture().isEqual(date))
                .toList();
        System.out.println("filteredTrips: " + filteredTrips);
        return filteredTrips;
    }

    public List<BmsTrip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Optional<BmsTrip> getInfoTrip(Long id) {
        System.out.println("Fetching trip info for ID: " + id);
        // Fetch the trip
        Optional<BmsTrip> tripOpt  = tripRepository.findById(String.valueOf(id));
        if(tripOpt.isPresent()){
            BmsTrip trip = tripOpt.get();

            // Fetch the vehicle using valueVehicle
            Optional<BmsVehicle> vehicleOpt = vehicleRepository.findById(trip.getValueVehicle());
            if(vehicleOpt.isPresent()){
                BmsVehicle vehicle = vehicleOpt.get();
                // Enrich trip with vehicle details
                trip.setValueVehicle(vehicle.getLicensePlate());
            }
            System.out.println("Trip info: " + trip);
            return  Optional.of(trip);
        }
        System.out.println("Trip: " + tripOpt);

        return Optional.empty();
    }
}
