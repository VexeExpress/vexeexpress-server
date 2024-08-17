package com.vexeexpress.vexeexpressserver.APP.BMS.service;

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

    public List<BmsAgent> getAgentsByCompanyId(String companyId) {
        return agentRepository.findByCompanyId(companyId);
    }

    public void deleteAgent(Long id) throws Exception {
        if (agentRepository.existsById(String.valueOf(id))) {
            agentRepository.deleteById(String.valueOf(id));
        } else {
            throw new Exception("User not found");
        }
    }

    public boolean doesAgentExist(String name) {
        Optional<Object> agents = agentRepository.findByName(name);
        return agents.isPresent();
    }

    public BmsAgent updateAgent(Long id, BmsAgent bmsAgent) {
        return agentRepository.findById(String.valueOf(id)).map(agent -> {
            agent.setName(bmsAgent.getName());
            agent.setEmail(bmsAgent.getEmail());
            agent.setAddress(bmsAgent.getAddress());
            agent.setDiscount(bmsAgent.getDiscount());
            agent.setPhone(bmsAgent.getPhone());
            agent.setNote(bmsAgent.getNote());
            return agentRepository.save(agent);
        }).orElse(null);
    }
}
