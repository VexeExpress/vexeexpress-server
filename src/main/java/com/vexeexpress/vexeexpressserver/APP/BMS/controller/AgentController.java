package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.AgentService;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.BmsAuthService;
import com.vexeexpress.vexeexpressserver.entity.BmsAgent;
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

    // Tạo đại lý mới
    @PostMapping("/create-agent")
    public ResponseEntity<BmsAgent> createAgent(@RequestBody BmsAgent bmsAgent){
        if(bmsAgent == null || bmsAgent.getName() == null || bmsAgent.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (agentService.doesAgentExist(bmsAgent.getName())){
            System.out.println("Đại lý đã tồn tại");
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        BmsAgent createdAgent = agentService.createAgent(bmsAgent);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAgent);
    }
    // Hiển thị danh sách đại lý
    @GetMapping("/agent-list/{companyId}")
    public ResponseEntity<List<BmsAgent>> getAgentsByCompanyId(@PathVariable String companyId) {
        if (companyId == null || companyId.trim().isEmpty()) {
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
    public ResponseEntity<BmsAgent> updateAgent(@PathVariable Long id, @RequestBody BmsAgent bmsAgent) {
        try {
            BmsAgent updateAgent = agentService.updateAgent(id, bmsAgent);
            return ResponseEntity.ok(updateAgent);
        }  catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
