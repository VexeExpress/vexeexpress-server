package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.entity.BmsChargeCost;
import com.vexeexpress.vexeexpressserver.repository.ChargeCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChargeCostService {
    @Autowired
    ChargeCostRepository chargeCostRepository;

    public BmsChargeCost add(BmsChargeCost bmsChargeCost) {
        return chargeCostRepository.save(bmsChargeCost);
    }

    public BmsChargeCost update(BmsChargeCost bmsChargeCost) {
        BmsChargeCost existingCost = chargeCostRepository.findByTripId(bmsChargeCost.getTripId());

        // Cập nhật các thuộc tính từ bmsChargeCost không null
        if (bmsChargeCost.getId() != null) {
            existingCost.setId(bmsChargeCost.getId());
        }
        if (bmsChargeCost.getStation() != null) {
            existingCost.setStation(bmsChargeCost.getStation());
        }
        if (bmsChargeCost.getStationMeal() != null) {
            existingCost.setStationMeal(bmsChargeCost.getStationMeal());
        }
        if (bmsChargeCost.getDailyEat() != null) {
            existingCost.setDailyEat(bmsChargeCost.getStationMeal());
        }
        if (bmsChargeCost.getWashing() != null) {
            existingCost.setWashing(bmsChargeCost.getWashing());
        }
        if (bmsChargeCost.getRepair() != null) {
            existingCost.setRepair(bmsChargeCost.getRepair());
        }
        if (bmsChargeCost.getDriverSalary() != null) {
            existingCost.setDriverSalary(bmsChargeCost.getDriverSalary());
        }
        if (bmsChargeCost.getAssistantSalary() != null) {
            existingCost.setAssistantSalary(bmsChargeCost.getAssistantSalary());
        }
        if (bmsChargeCost.getFreightCollected() != null) {
            existingCost.setFreightCollected(bmsChargeCost.getFreightCollected());
        }
        if (bmsChargeCost.getGas() != null) {
            existingCost.setGas(bmsChargeCost.getGas());
        }

        return chargeCostRepository.save(existingCost);
    }

    public BmsChargeCost findByTripId(Long id) {
        return chargeCostRepository.findByTripId(id);
    }
}
