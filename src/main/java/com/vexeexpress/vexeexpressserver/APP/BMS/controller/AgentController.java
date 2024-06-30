package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.AgentService;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.BmsAuthService;
import com.vexeexpress.vexeexpressserver.entity.BmsAgent;
import org.springframework.beans.factory.annotation.Autowired;
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
    public BmsAgent createAgent(@RequestBody BmsAgent bmsAgent){
        System.out.println(bmsAgent);
        return agentService.createAgent(bmsAgent);
    }
    // Hiển thị danh sách đại lý
    @GetMapping("/agent-list/{companyId}")
    public List<BmsAgent> getAgentsByCompanyId(@PathVariable String companyId) {
        return agentService.getAgentsByCompanyId(companyId);
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
}
