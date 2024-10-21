package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Vehicle.VehicleDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Vehicle.VehicleDTO_v3;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.VehicleDTO_v2;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import com.vexeexpress.vexeexpressserver.entity.BmsVehicle;
import com.vexeexpress.vexeexpressserver.repository.CompanyRepository;
import com.vexeexpress.vexeexpressserver.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    CompanyRepository companyRepository;
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



    public List<VehicleDTO_v3> getVehicleNameByCompanyId(Long companyId) {
        List<BmsVehicle> vehicles = vehicleRepository.findByCompanyId(companyId);
        return vehicles.stream().map(entity -> {
            VehicleDTO_v3 dto = new VehicleDTO_v3();
            dto.setId(entity.getId());
            dto.setLicensePlate(entity.getLicensePlate());
            return dto;
        }).collect(Collectors.toList());
    }
    public List<VehicleDTO_v3> getListVehicleNameByCompanyId(Long companyId) {
        if (companyId == null) {
            throw new IllegalArgumentException("companyId must not be null");
        }
        List<BmsVehicle> vehicles = vehicleRepository.findByCompanyId(companyId);
        if(vehicles == null || vehicles.isEmpty()) {
            return null;
        }
        return vehicles.stream().map(this::convertToDTO_v3).collect(Collectors.toList());
    }

    private VehicleDTO_v3 convertToDTO_v3(BmsVehicle vehicle) {
        VehicleDTO_v3 dto = new VehicleDTO_v3();
        dto.setId(vehicle.getId());
        dto.setLicensePlate(vehicle.getLicensePlate());
        return dto;
    }

    public List<VehicleDTO> getListVehicleDetailByCompanyId(Long companyId) {
        if (companyId == null) {
            throw new IllegalArgumentException("companyId must not be null");
        }
        List<BmsVehicle> vehicles = vehicleRepository.findByCompanyId(companyId);
        if(vehicles == null || vehicles.isEmpty()) {
            return null;
        }
        return vehicles.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private VehicleDTO convertToDTO(BmsVehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setLicensePlate(vehicle.getLicensePlate());
        dto.setRegistrationDeadline(vehicle.getRegistrationDeadline());
        dto.setColor(vehicle.getColor());
        dto.setCategory(vehicle.getCategory());
        dto.setBrand(vehicle.getBrand());
        dto.setPhone(vehicle.getPhone());
        dto.setChassisNumber(vehicle.getChassisNumber());
        dto.setMachineNumber(vehicle.getMachineNumber());
        dto.setRegistrationDeadline(vehicle.getRegistrationDeadline());
        dto.setInsuranceTerm(vehicle.getInsuranceTerm());
        return dto;
    }

    public BmsVehicle createVehicle_v2(VehicleDTO dto) {
        System.out.println(dto);
        Optional<BmsBusCompany> companyOpt = companyRepository.findById(dto.getCompanyId());
        if (companyOpt.isEmpty()) {
            throw new IllegalArgumentException("Company ID không hợp lệ.");
        }
        if (vehicleRepository.existsByLicensePlateAndCompany_Id(dto.getLicensePlate(), dto.getCompanyId())) {
            throw new IllegalArgumentException("Phương tiện đã tồn tại trong công ty này.");
        }
        BmsVehicle vehicle = new BmsVehicle();
        vehicle.setLicensePlate(dto.getLicensePlate());
        vehicle.setPhone(dto.getPhone());
        vehicle.setBrand(dto.getBrand());
        vehicle.setColor(dto.getColor());
        vehicle.setCategory(dto.getCategory());
        vehicle.setChassisNumber(dto.getChassisNumber());
        vehicle.setMachineNumber(dto.getMachineNumber());
        vehicle.setRegistrationDeadline(dto.getRegistrationDeadline());
        vehicle.setInsuranceTerm(dto.getInsuranceTerm());
        vehicle.setCompany(companyOpt.get());

        return vehicleRepository.save(vehicle);
    }

    public BmsVehicle updateVehicle_v2(Long id, VehicleDTO dto) {
        Optional<BmsVehicle> vehicleOptinal = vehicleRepository.findById(id);
        if (vehicleOptinal.isPresent()) {
            BmsVehicle currentVehicle = vehicleOptinal.get();
            Optional<BmsBusCompany> companyOpt = companyRepository.findById(dto.getCompanyId());
            if (companyOpt.isEmpty()) {
                throw new IllegalArgumentException("Company ID không hợp lệ.");
            }
            if (!currentVehicle.getLicensePlate().equals(dto.getLicensePlate())) {
                boolean licensePlateExists = vehicleRepository.existsByLicensePlateAndCompany_IdAndIdNot(dto.getLicensePlate(), dto.getCompanyId(), currentVehicle.getId());
                if (licensePlateExists) {
                    System.out.println("Phương tiện: " + dto.getLicensePlate() + " đã tồn tại trong công ty: " + dto.getCompanyId());
                    return null;
                }
            }
            currentVehicle.setLicensePlate(dto.getLicensePlate());
            currentVehicle.setPhone(dto.getPhone());
            currentVehicle.setBrand(dto.getBrand());
            currentVehicle.setColor(dto.getColor());
            currentVehicle.setCategory(dto.getCategory());
            currentVehicle.setChassisNumber(dto.getChassisNumber());
            currentVehicle.setMachineNumber(dto.getMachineNumber());
            currentVehicle.setRegistrationDeadline(dto.getRegistrationDeadline());
            currentVehicle.setInsuranceTerm(dto.getInsuranceTerm());
            
            return vehicleRepository.save(currentVehicle);
        } else {
            return null;
        }
    }

    public void deleteVehicle_v2(Long id) throws Exception {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
        } else {
            throw new Exception("Vehicle not found");
        }
    }


}
