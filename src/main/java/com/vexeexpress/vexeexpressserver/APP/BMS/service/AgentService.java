package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.AgentDTO;
import com.vexeexpress.vexeexpressserver.entity.BmsAgent;
import com.vexeexpress.vexeexpressserver.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentService {
    @Autowired
    AgentRepository agentRepository;
    public BmsAgent createAgent(BmsAgent bmsAgent) {
        return agentRepository.save(bmsAgent);
    }

    public List<BmsAgent> getAgentsByCompanyId(Long companyId) {
        return agentRepository.findByCompanyId(companyId);
    }

    public void deleteAgent(Long id) throws Exception {
        if (agentRepository.existsById(id)) {
            agentRepository.deleteById(id);
        } else {
            throw new Exception("User not found");
        }
    }

    public boolean doesAgentExist(String name) {
        Optional<Object> agents = agentRepository.findByName(name);
        return agents.isPresent();
    }

    public BmsAgent updateAgent(BmsAgent bmsAgent) {
        return agentRepository.save(bmsAgent);
    }

    public BmsAgent getAgentById(Long id) {
        return agentRepository.findById(id).orElse(null);
    }
}
