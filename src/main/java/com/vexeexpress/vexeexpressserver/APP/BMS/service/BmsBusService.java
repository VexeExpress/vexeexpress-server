package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vexeexpress.vexeexpressserver.entity.BmsBus;
import com.vexeexpress.vexeexpressserver.repository.BmsBusRepository;
import java.util.Optional;

@Service
public class BmsBusService {

    @Autowired
    BmsBusRepository bmsBusRepository;

    @Transactional
    public BmsBus createBus(String busNumber, String licensePlate, int capacity, String status, Long companyId) {
        Optional<BmsBus> existingBusByNumber = bmsBusRepository.findByBusNumber(busNumber);
        Optional<BmsBus> existingBusByPlate = bmsBusRepository.findByLicensePlate(licensePlate);

        if (existingBusByNumber.isPresent() && existingBusByPlate.isPresent()) {
            throw new RuntimeException(
                    "Both bus number and license plate already exist. Please enter different values.");
        } else if (existingBusByNumber.isPresent()) {
            throw new RuntimeException("Bus number already exists. Please enter a different bus number.");
        } else if (existingBusByPlate.isPresent()) {
            throw new RuntimeException("License plate already exists. Please enter a different license plate.");
        }

        BmsBus bus = new BmsBus();
        bus.setBusNumber(busNumber);
        bus.setLicensePlate(licensePlate);
        bus.setCapacity(capacity);
        bus.setStatus(status);
        bus.setCompanyId(companyId);
        return bmsBusRepository.save(bus);
    }

    public List<BmsBus> getAllBuses() {
        return bmsBusRepository.findAll();
    }

    public List<BmsBus> getBusesBySimilarNumber(String busNumber) {
        return bmsBusRepository.findByBusNumberContaining(busNumber);
    }

    @Transactional
    public void deleteBusById(Long id) {
        if (bmsBusRepository.existsById(id)) {
            bmsBusRepository.deleteById(id);
        } else {
            throw new RuntimeException("Bus with id " + id + " does not exist");
        }
    }

    @Transactional
    public void deleteAllBuses() {
        bmsBusRepository.deleteAll();
    }
}
