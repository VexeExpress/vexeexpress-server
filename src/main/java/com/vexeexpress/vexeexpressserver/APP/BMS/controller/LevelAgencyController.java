package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.LevelAgency.LevelAgencyDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.LevelAgency.LevelAgencyDTO_v2;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Vehicle.VehicleDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.LevelAgencyService;
import com.vexeexpress.vexeexpressserver.entity.BmsLevelAgency;
import com.vexeexpress.vexeexpressserver.entity.BmsVehicle;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bms/level-agency")
@CrossOrigin(origins = "http://localhost:3000")
public class LevelAgencyController {
    @Autowired
    LevelAgencyService levelAgencyService;

    @GetMapping("/list-level-agency/{companyId}")
    public ResponseEntity<?> getListLevelAgencyDetailByCompanyId(@PathVariable Long companyId) {
        try {
            List<LevelAgencyDTO> levelAgency = levelAgencyService.getListLevelAgencyDetailByCompanyId(companyId);

            if (levelAgency == null || levelAgency.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
            }
            return ResponseEntity.ok(levelAgency); // 200 OK
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
    public ResponseEntity<?> createLevelAgency_2(@RequestBody LevelAgencyDTO dto) {
        try {
            BmsLevelAgency levelAgency = levelAgencyService.createLevelAgency_2(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(levelAgency);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLevelAgency_v2(@PathVariable Long id, @RequestBody LevelAgencyDTO dto) {
        try {
            BmsLevelAgency levelAgency = levelAgencyService.updateLevelAgency_v2(id, dto);
            if (levelAgency != null) {
                return ResponseEntity.ok(levelAgency);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLevelAgency(@PathVariable Long id) {
        System.out.println(id);
        try {
            levelAgencyService.deleteLevelAgency(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
