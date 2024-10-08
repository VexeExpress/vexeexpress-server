package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.LevelAgency.LevelAgencyDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.LevelAgency.LevelAgencyDTO_v2;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.LevelAgencyService;
import com.vexeexpress.vexeexpressserver.entity.BmsLevelAgency;
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

    @PostMapping("/create")
    public ResponseEntity<?> createLevelAgency(@RequestBody LevelAgencyDTO levelAgencyDTO) {
        try {
            levelAgencyService.createLevelAgency(levelAgencyDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/get-list-level-agency/{companyId}")
    public ResponseEntity<List<LevelAgencyDTO_v2>> getLevelAgenciesByCompanyId(@PathVariable Long companyId) {
        try {
            List<LevelAgencyDTO_v2> levelAgencies = levelAgencyService.getLevelAgenciesByCompanyId(companyId);
            System.out.println(levelAgencies);
            return ResponseEntity.ok(levelAgencies);
        } catch (Exception e) {
            System.err.println("Error fetching level agencies: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<LevelAgencyDTO> updateLevelAgency(@PathVariable Long id, @RequestBody LevelAgencyDTO levelAgencyDTO) {
        levelAgencyDTO.setId(id);
        try {
            LevelAgencyDTO updatedLevelAgency = levelAgencyService.updateLevelAgency(levelAgencyDTO);
            return ResponseEntity.ok(updatedLevelAgency);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Không tìm thấy
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Lỗi server
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLevelAgency(@PathVariable Long id) {
        try {
            levelAgencyService.deleteLevelAgency(id);
            return ResponseEntity.noContent().build(); // Trả về mã 204 No Content
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Trả về 404
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Lỗi server
        }
    }
}
