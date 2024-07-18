package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.utils.ValueTrip;
import com.vexeexpress.vexeexpressserver.entity.*;
import com.vexeexpress.vexeexpressserver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TripService {
    @Autowired
    TripRepository tripRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    RouterRepository routerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BmsBusRepository busRepository;
    @Autowired
    CompanyRepository companyRepository;
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

    public Optional<ValueTrip> getInfoTrip(Long id) {
        System.out.println("Fetching trip info for ID: " + id);

        // Fetch the trip
        Optional<BmsTrip> tripOpt = tripRepository.findById(String.valueOf(id));
        if (tripOpt.isEmpty()) {
            System.out.println("Trip not found for ID: " + id);
            return Optional.empty();
        }

        BmsTrip trip = tripOpt.get();
        ValueTrip dto = new ValueTrip();
        dto.setId(trip.getId());
        dto.setValueTimeDeparture(trip.getValueTimeDeparture());

        // Fetch and set company information
        companyRepository.findById(Long.valueOf(trip.getCompanyId())).ifPresent(company -> {
            dto.setCompanyId(company.getId());
        });

        // Directly set values if they are not supposed to be fetched again
        dto.setValueDayDeparture(trip.getValueDayDeparture());
        dto.setValueNote(trip.getValueNote());
        dto.setValueChairDiagram(trip.getValueChairDiagram());

        // Fetch the vehicle using valueVehicle
        vehicleRepository.findById(trip.getValueVehicle()).ifPresent(vehicle -> {
            dto.setValueVehicle(vehicle.getLicensePlate());
            dto.setPhoneVehicle(vehicle.getPhone());
        });

        // Fetch the router using valueRouter
        routerRepository.findById(trip.getValueRouter()).ifPresent(router -> {
            dto.setValueRouter(router.getName());
        });

        // Fetch the drivers using valueDriver
        List<String> driverIds = trip.getValueDriver();
        if (driverIds != null && !driverIds.isEmpty()) {
            List<Long> driverIdsLong = driverIds.stream().map(Long::valueOf).collect(Collectors.toList());
            List<BmsUser> drivers = userRepository.findAllById(driverIdsLong);
            List<String> driverNames = drivers.stream().map(driver -> driver.getName() + " - " + driver.getPhone()).collect(Collectors.toList());
            dto.setValueDriver(driverNames);
        }

        System.out.println("Trip info: " + dto);
        return Optional.of(dto);
    }

}
