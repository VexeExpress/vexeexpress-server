package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.User.UserDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Vehicle.VehicleDTO_v3;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Vehicle.VehicleDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.VehicleDTO_v2;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.CompanyService;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.VehicleService;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import com.vexeexpress.vexeexpressserver.entity.BmsVehicle;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bms/vehicle")
@CrossOrigin(origins = "http://localhost:3000")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;
    @Autowired
    CompanyService companyService;


    @GetMapping("/vehicles/{companyId}")
    public ResponseEntity<?> getListVehicleDetailByCompanyId(@PathVariable Long companyId) {
        try {
            List<VehicleDTO> vehicles = vehicleService.getListVehicleDetailByCompanyId(companyId);

            if (vehicles == null || vehicles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
            }
            return ResponseEntity.ok(vehicles); // 200 OK
        } catch (IllegalArgumentException e) {
            // Xử lý khi companyId không hợp lệ
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request
        } catch (EntityNotFoundException e) {
            // CompanyId null
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }
    @GetMapping("/list-vehicle-name/{companyId}")
    public ResponseEntity<?> getListVehicleNameByCompanyId(@PathVariable Long companyId) {
        try {
            List<VehicleDTO_v3> vehicles = vehicleService.getListVehicleNameByCompanyId(companyId);

            if (vehicles == null || vehicles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
            }
            return ResponseEntity.ok(vehicles); // 200 OK
        } catch (IllegalArgumentException e) {
            // Xử lý khi companyId không hợp lệ
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request
        } catch (EntityNotFoundException e) {
            // CompanyId null
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> createVehicle_2(@RequestBody VehicleDTO dto) {
        try {
            BmsVehicle createdVehicle = vehicleService.createVehicle_v2(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicle);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVehicle_v2(@PathVariable Long id, @RequestBody VehicleDTO dto) {
        try {
            BmsVehicle updatedVehicle = vehicleService.updateVehicle_v2(id, dto);
            if (updatedVehicle != null) {
                return ResponseEntity.ok(updatedVehicle);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    // Tạo phương tiện mới
//    @PostMapping("/create-vehicle")
//    public ResponseEntity<BmsVehicle> createVehicle(@RequestBody VehicleDTO vehicleDTO){
//        try {
//            System.out.println("Dữ liệu phương tiện mới: " +  vehicleDTO);
//            BmsBusCompany company = companyService.getCompanyById(vehicleDTO.getCompanyId());
//            if(company == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 if company not found
//            }
//            BmsVehicle bmsVehicle = new BmsVehicle();
//            bmsVehicle.setLicensePlate(vehicleDTO.getLicensePlate());
//            bmsVehicle.setNote(vehicleDTO.getNote());
//            bmsVehicle.setColor(vehicleDTO.getColor());
//            bmsVehicle.setBrand(vehicleDTO.getBrand());
//            bmsVehicle.setCategory(vehicleDTO.getCategory());
//            bmsVehicle.setPhone(vehicleDTO.getPhone());
//            bmsVehicle.setCompany(company);
//
//            BmsVehicle vehicle = vehicleService.createVehicle(bmsVehicle);
//            return ResponseEntity.status(HttpStatus.CREATED).body(vehicle); // Return 201 Created
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
//        }
//    }
    // Hiện thị danh sách phương tiện
    @GetMapping("/get-vehicle-by-company-id/{companyId}")
    public ResponseEntity<?> getVehiclesByCompanyId(@PathVariable Long companyId) {
        try {
            // Gọi service để lấy danh sách phương tiện theo companyId
            List<VehicleDTO_v2> vehicles = vehicleService.getVehiclesByCompanyId(companyId);

            // Kiểm tra nếu danh sách trống
            if (vehicles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy phương tiện nào cho công ty có ID: " + companyId);
            }

            // Trả về danh sách phương tiện với mã trạng thái 200 OK
            return ResponseEntity.ok(vehicles);
        } catch (Exception e) {
            // Xử lý lỗi và trả về mã trạng thái 500 Internal Server Error cùng với thông báo lỗi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi lấy phương tiện: " + e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVehicle_v2(@PathVariable Long id) {
        System.out.println(id);
        try {
            vehicleService.deleteVehicle_v2(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update vehicle by ID
//    @PutMapping("/update-vehicle/{vehicleId}")
//    public ResponseEntity<BmsVehicle> updateVehicle(@PathVariable Long vehicleId, @RequestBody VehicleDTO vehicleDTO) {
//        try {
//            System.out.println("Updating vehicle: " + vehicleDTO);
//            BmsVehicle existingVehicle = vehicleService.getVehicleById(vehicleId);
//            if (existingVehicle == null) {
//                System.out.println("Vehicle not found: " + vehicleId);
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found if vehicle doesn't exist
//            }
//            BmsBusCompany company = companyService.getCompanyById(vehicleDTO.getCompanyId());
//            if (company == null) {
//                System.out.println("Company not found: " + vehicleDTO.getCompanyId());
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found if company doesn't exist
//            }
//
//            existingVehicle.setLicensePlate(vehicleDTO.getLicensePlate());
//            existingVehicle.setNote(vehicleDTO.getNote());
//            existingVehicle.setBrand(vehicleDTO.getBrand());
//            existingVehicle.setColor(vehicleDTO.getColor());
//            existingVehicle.setPhone(vehicleDTO.getPhone());
//            existingVehicle.setCategory(vehicleDTO.getCategory());
//            existingVehicle.setCompany(company);
//
//            BmsVehicle vehicle = vehicleService.updateVehicle(existingVehicle);
//            return ResponseEntity.ok(vehicle);
//
//        } catch (Exception e) {
//            e.printStackTrace(); // Print stack trace to debug
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }




    @GetMapping("/vehicles-name/{companyId}")
    public ResponseEntity<List<VehicleDTO_v3>> getVehicleNameByCompanyId (@PathVariable Long companyId) {
        try {
            List<VehicleDTO_v3> seatMap = vehicleService.getVehicleNameByCompanyId(companyId);
            return ResponseEntity.ok(seatMap);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
