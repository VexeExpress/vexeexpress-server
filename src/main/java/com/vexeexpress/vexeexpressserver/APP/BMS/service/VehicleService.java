package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Vehicle.VehicleDTO_v3;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.VehicleDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.VehicleDTO_v2;
import com.vexeexpress.vexeexpressserver.entity.BmsVehicle;
import com.vexeexpress.vexeexpressserver.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;
    public BmsVehicle createVehicle(BmsVehicle bmsVehicle) {
        return vehicleRepository.save(bmsVehicle);
    }

    public List<VehicleDTO_v2> getVehiclesByCompanyId(Long companyId) {
        return vehicleRepository.findByCompanyId(companyId).stream().map(vehicle -> {
            VehicleDTO_v2 dto = new VehicleDTO_v2();
            dto.setId(vehicle.getId());
            dto.setLicensePlate(vehicle.getLicensePlate());
            dto.setCompanyId(vehicle.getCompany().getId());
            return dto;
        }).collect(Collectors.toList());
    }


    // Cập nhật thông tin phương tiện theo id
    public BmsVehicle updateVehicle(BmsVehicle bmsVehicle) {
        return vehicleRepository.save(bmsVehicle);
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

    public BmsVehicle getVehicleById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId).orElse(null);
    }

    public List<VehicleDTO_v3> getVehicleNameByCompanyId(Long companyId) {
        List<BmsVehicle> vehicles = vehicleRepository.findByCompanyId(companyId);
        return vehicles.stream().map(entity -> {
            VehicleDTO_v3 dto = new VehicleDTO_v3();
            dto.setId(entity.getId());
            dto.setLicensePlate(entity.getLicensePlate());
            return dto;
        }).collect(Collectors.toList());
    }
}
