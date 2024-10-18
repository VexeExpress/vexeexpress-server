package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Agency.AgentDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.LevelAgency.LevelAgencyDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.AgentService;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.CompanyService;
import com.vexeexpress.vexeexpressserver.entity.BmsAgent;
import com.vexeexpress.vexeexpressserver.entity.BmsLevelAgency;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bms/agency")
@CrossOrigin(origins = "http://localhost:3000")
public class AgentController {
    @Autowired
    AgentService agentService;
    @Autowired
    CompanyService companyService;

    @GetMapping("/list-agency/{companyId}")
    public ResponseEntity<?> getListAgencyDetailByCompanyId(@PathVariable Long companyId) {
        try {
            List<AgentDTO> agent = agentService.getListAgencyDetailByCompanyId(companyId);

            if (agent == null || agent.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
            }
            return ResponseEntity.ok(agent); // 200 OK
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
    public ResponseEntity<?> createAgency_2(@RequestBody AgentDTO dto) {
        try {
            BmsAgent agent = agentService.createAgency_2(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(agent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAgency_v2(@PathVariable Long id, @RequestBody AgentDTO dto) {
        try {
            BmsAgent agent = agentService.updateAgency_v2(id, dto);
            if (agent != null) {
                return ResponseEntity.ok(agent);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAgency(@PathVariable Long id) {
        System.out.println(id);
        try {
            agentService.deleteAgency(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
