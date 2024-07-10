package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.entity.BmsTrip;
import com.vexeexpress.vexeexpressserver.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        System.out.println("Found trips: " + listTrip);

        return null;
    }

    public List<BmsTrip> getAllTrips() {
        return tripRepository.findAll();
    }
}
