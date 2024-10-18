package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Agency.AgentDTO;
import com.vexeexpress.vexeexpressserver.entity.BmsAgent;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsLevelAgency;
import com.vexeexpress.vexeexpressserver.repository.AgentRepository;
import com.vexeexpress.vexeexpressserver.repository.CompanyRepository;
import com.vexeexpress.vexeexpressserver.repository.LevelAgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgentService {
    @Autowired
    AgentRepository agentRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    LevelAgencyRepository levelAgencyRepository;


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
        dto.setLevelAgencyName(agent.getLevelAgency().getLevelName());
        dto.setPhone(agent.getPhone());
        return dto;
    }

    public BmsAgent createAgency_2(AgentDTO dto) {
        Optional<BmsBusCompany> companyOpt = companyRepository.findById(dto.getCompanyId());
        if (companyOpt.isEmpty()) {
            throw new IllegalArgumentException("Company ID không hợp lệ.");
        }
        Optional<BmsLevelAgency> levelAgencyOpt = levelAgencyRepository.findById(dto.getLevelAgencyId());
        if (levelAgencyOpt.isEmpty()) {
            throw new IllegalArgumentException("LevelAgency ID không hợp lệ.");
        }
        if (agentRepository.existsByNameAndCompany_Id(dto.getName(), dto.getCompanyId())) {
            throw new IllegalArgumentException("Đại lý đã tồn tại trong công ty này.");
        }
        BmsAgent agent = new BmsAgent();
        agent.setName(dto.getName());
        agent.setEmail(dto.getEmail());
        agent.setNote(dto.getNote());
        agent.setAddress(dto.getAddress());
        agent.setPhone(dto.getPhone());
        agent.setCompany(companyOpt.get());
        agent.setLevelAgency(levelAgencyOpt.get());
        return agentRepository.save(agent);
    }

    public BmsAgent updateAgency_v2(Long id, AgentDTO dto) {
        Optional<BmsAgent> agentOptional = agentRepository.findById(id);
        if (agentOptional.isPresent()) {
            BmsAgent currentAgency = agentOptional.get();
            Optional<BmsBusCompany> companyOpt = companyRepository.findById(dto.getCompanyId());
            if (companyOpt.isEmpty()) {
                throw new IllegalArgumentException("Company ID không hợp lệ.");
            }
            if (!currentAgency.getName().equals(dto.getName())) {
                boolean nameExists = agentRepository.existsByNameAndCompany_IdAndIdNot(dto.getName(), dto.getCompanyId(), currentAgency.getId());
                if (nameExists) {
                    System.out.println("Cấp đại lý: " + dto.getName() + " đã tồn tại trong công ty: " + dto.getCompanyId());
                    return null;
                }
            }
            Optional<BmsLevelAgency> levelAgencyOpt = levelAgencyRepository.findById(dto.getLevelAgencyId());
            if (levelAgencyOpt.isEmpty()) {
                throw new IllegalArgumentException("Level Agency ID không hợp lệ.");
            }

            currentAgency.setName(dto.getName());
            currentAgency.setPhone(dto.getPhone());
            currentAgency.setNote(dto.getNote());
            currentAgency.setEmail(dto.getEmail());
            currentAgency.setAddress(dto.getAddress());
            currentAgency.setLevelAgency(levelAgencyOpt.get());

            return agentRepository.save(currentAgency);
        } else {
            return null;
        }
    }

    public void deleteAgency(Long id) throws Exception {
        if (agentRepository.existsById(id)) {
            agentRepository.deleteById(id);
        } else {
            throw new Exception("Agency not found");
        }
    }
}
