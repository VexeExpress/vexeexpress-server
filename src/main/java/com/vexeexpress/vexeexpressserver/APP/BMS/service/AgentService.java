package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Agency.AgentDTO;
import com.vexeexpress.vexeexpressserver.entity.BmsAgent;
import com.vexeexpress.vexeexpressserver.entity.BmsLevelAgency;
import com.vexeexpress.vexeexpressserver.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<AgentDTO> getListAgencyDetailByCompanyId(Long companyId) {
        if (companyId == null) {
            throw new IllegalArgumentException("companyId must not be null");
        }
        List<BmsAgent> agents = agentRepository.findByCompanyId(companyId);
        if(agents == null || agents.isEmpty()) {
            return null;
        }
        return agents.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AgentDTO convertToDTO(BmsAgent agent) {
        AgentDTO dto = new AgentDTO();
        dto.setId(agent.getId());
        dto.setName(agent.getName());
        dto.setAddress(agent.getAddress());
        dto.setEmail(agent.getEmail());
        dto.setNote(agent.getNote());
        dto.setCreatedAt(agent.getCreatedAt());
        dto.setLevelAgencyId(agent.getLevelAgency().getId());
        dto.setPhone(agent.getPhone());
        return dto;
    }
}
