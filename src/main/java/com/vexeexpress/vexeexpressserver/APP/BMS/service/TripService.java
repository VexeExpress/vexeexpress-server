package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.entity.BmsTrip;
import com.vexeexpress.vexeexpressserver.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripService {
    @Autowired
    TripRepository tripRepository;
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
}
