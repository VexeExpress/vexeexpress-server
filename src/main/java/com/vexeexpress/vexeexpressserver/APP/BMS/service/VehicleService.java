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
     // Cập nhật thông tin phương tiện theo id
     public BmsVehicle updateVehicle(Long id, BmsVehicle updatedVehicle) {
        BmsVehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with id: " + id));

        vehicle.setLicensePlate(updatedVehicle.getLicensePlate());
        vehicle.setPhone(updatedVehicle.getPhone());
        vehicle.setBrand(updatedVehicle.getBrand());
        vehicle.setCategory(updatedVehicle.getCategory());
        vehicle.setColor(updatedVehicle.getColor());
        vehicle.setNote(updatedVehicle.getNote());

        return vehicleRepository.save(vehicle);
    }

    // Xóa phương tiện
    public String deleteVehicle(Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return "Vehicle with id " + id + " has been deleted.";
        } else {
            throw new IllegalArgumentException("Vehicle not found with id: " + id);
        }
    }
}
