package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.entity.BmsVehicle;
import com.vexeexpress.vexeexpressserver.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;
    public BmsVehicle createVehicle(BmsVehicle bmsVehicle) {
        return vehicleRepository.save(bmsVehicle);
    }

    public List<BmsVehicle> getVehiclesByCompanyId(Long companyId) {
        return vehicleRepository.findByCompanyId(String.valueOf(companyId));
    }
}
