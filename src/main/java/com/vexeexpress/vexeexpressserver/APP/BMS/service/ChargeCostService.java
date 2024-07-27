package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.entity.BmsChargeCost;
import com.vexeexpress.vexeexpressserver.repository.ChargeCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChargeCostService {
    @Autowired
    ChargeCostRepository repository;

    public BmsChargeCost add(BmsChargeCost input) {
        return repository.save(input);
    }

    public BmsChargeCost update(BmsChargeCost input) {
        BmsChargeCost existingInput = repository.findByTripId(input.getTripId());

        // Cập nhật các thuộc tính từ input không null
        if (input.getId() != null) {
            existingInput.setId(input.getId());
        }
        if (input.getStation() != null) {
            existingInput.setStation(input.getStation());
        }
        if (input.getStationMeal() != null) {
            existingInput.setStationMeal(input.getStationMeal());
        }
        if (input.getDailyEat() != null) {
            existingInput.setDailyEat(input.getStationMeal());
        }
        if (input.getWashing() != null) {
            existingInput.setWashing(input.getWashing());
        }
        if (input.getRepair() != null) {
            existingInput.setRepair(input.getRepair());
        }
        if (input.getDriverSalary() != null) {
            existingInput.setDriverSalary(input.getDriverSalary());
        }
        if (input.getAssistantSalary() != null) {
            existingInput.setAssistantSalary(input.getAssistantSalary());
        }
        if (input.getFreightCollected() != null) {
            existingInput.setFreightCollected(input.getFreightCollected());
        }
        if (input.getGas() != null) {
            existingInput.setGas(input.getGas());
        }
        if (input.getBusStation() != null) {
            existingInput.setBusStation(input.getBusStation());
        }

        return repository.save(existingInput);
    }

    public BmsChargeCost findByTripId(Long id) {
        return repository.findByTripId(id);
    }
}
