package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.AgentDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.AgentService;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.CompanyService;
import com.vexeexpress.vexeexpressserver.entity.BmsAgent;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bms/agent")
@CrossOrigin(origins = "http://localhost:5173")
public class AgentController {
    @Autowired
    AgentService agentService;
    @Autowired
    CompanyService companyService;

    // Tạo đại lý mới
    @PostMapping("/create-agent")
    public ResponseEntity<BmsAgent> createAgent(@RequestBody AgentDTO agentDTO){
        try{
            System.out.println("Dữ liệu đại lý mới: " +  agentDTO);
            BmsBusCompany company = companyService.getCompanyById(agentDTO.getCompanyId());
            if(company == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 if company not found
            }
            BmsAgent bmsAgent = new BmsAgent();
            bmsAgent.setId(agentDTO.getId());
            bmsAgent.setName(agentDTO.getName());
            bmsAgent.setPhone(agentDTO.getPhone());
            bmsAgent.setEmail(agentDTO.getEmail());
            bmsAgent.setAddress(agentDTO.getAddress());
            bmsAgent.setDiscount(agentDTO.getDiscount());
            bmsAgent.setNote(agentDTO.getNote());
            bmsAgent.setCreatedAt(agentDTO.getCreatedAt());
            bmsAgent.setCompany(company);

            BmsAgent agent = agentService.createAgent(bmsAgent);
            return ResponseEntity.status(HttpStatus.CREATED).body(agent);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
        }
    }
    // Hiển thị danh sách đại lý
    @GetMapping("/agent-list/{companyId}")
    public ResponseEntity<List<BmsAgent>> getAgentsByCompanyId(@PathVariable Long companyId) {
        if (companyId == null) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        try {
            List<BmsAgent> agents = agentService.getAgentsByCompanyId(companyId);
            if(agents.isEmpty()){
                return ResponseEntity.noContent().build();// 204 No Content
            }
            return ResponseEntity.ok(agents); // 200 OK
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }
    // Xoá đại lý
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable Long id){
        System.out.println(id);
        try {
            agentService.deleteAgent(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    // Cập nhật thông tin đại lý
    @PutMapping("/update-agent/{id}")
    public ResponseEntity<BmsAgent> updateAgent(@PathVariable Long id, @RequestBody AgentDTO agentDTO) {
        try {
            System.out.println("Updating agent: " + agentDTO);
            BmsAgent existingAgent = agentService.getAgentById(id);
            if(existingAgent == null) {
                System.out.println("Vehicle not found: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            BmsBusCompany company = companyService.getCompanyById(agentDTO.getCompanyId());
            if (company == null) {
                System.out.println("Company not found:: " + agentDTO.getCompanyId());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found if company doesn't exist
            }
            existingAgent.setCompany(company);
            existingAgent.setNote(agentDTO.getNote());
            existingAgent.setAddress(agentDTO.getAddress());
            existingAgent.setDiscount(agentDTO.getDiscount());
            existingAgent.setPhone(agentDTO.getPhone());
            existingAgent.setEmail(agentDTO.getEmail());
            existingAgent.setName(agentDTO.getName());

            BmsAgent agent = agentService.updateAgent(existingAgent);
            return ResponseEntity.ok(agent);
        }  catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
