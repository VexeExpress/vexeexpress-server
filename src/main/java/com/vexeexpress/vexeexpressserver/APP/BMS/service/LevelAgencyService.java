package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.LevelAgency.LevelAgencyDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.LevelAgency.LevelAgencyDTO_v2;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsLevelAgency;
import com.vexeexpress.vexeexpressserver.repository.CompanyRepository;
import com.vexeexpress.vexeexpressserver.repository.LevelAgencyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LevelAgencyService {
    @Autowired
    LevelAgencyRepository levelAgencyRepository;
    @Autowired
    CompanyRepository companyRepository;


    public void createLevelAgency(LevelAgencyDTO levelAgencyDTO) {
        BmsBusCompany company = companyRepository.findById(levelAgencyDTO.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));

        BmsLevelAgency newLevelAgency = new BmsLevelAgency();
        newLevelAgency.setLevelName(levelAgencyDTO.getLevelName());
        newLevelAgency.setQuota(levelAgencyDTO.getQuota());
        newLevelAgency.setCompany(company);

        levelAgencyRepository.save(newLevelAgency);
    }

    public List<LevelAgencyDTO_v2> getLevelAgenciesByCompanyId(Long companyId) {
        List<BmsLevelAgency> levelAgencies = levelAgencyRepository.findByCompanyId(companyId);

        System.out.println("Level agencies found: " + levelAgencies);

        return levelAgencies.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    private LevelAgencyDTO_v2 convertToDTO(BmsLevelAgency levelAgency) {
        System.out.println("Converting level agency: " + levelAgency);
        return new LevelAgencyDTO_v2(
                levelAgency.getId(),
                levelAgency.getLevelName(),
                levelAgency.getQuota()
        );
    }


    public LevelAgencyDTO updateLevelAgency(LevelAgencyDTO levelAgencyDTO) {
        BmsLevelAgency levelAgency = levelAgencyRepository.findById(levelAgencyDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Level Agency not found"));

        levelAgency.setLevelName(levelAgencyDTO.getLevelName());
        levelAgency.setQuota(levelAgencyDTO.getQuota());


        BmsLevelAgency updatedLevelAgency = levelAgencyRepository.save(levelAgency);

        return convertToDTOUpdate(updatedLevelAgency);
    }
    private LevelAgencyDTO convertToDTOUpdate(BmsLevelAgency levelAgency) {
        LevelAgencyDTO dto = new LevelAgencyDTO();
        dto.setId(levelAgency.getId());
        dto.setLevelName(levelAgency.getLevelName());
        dto.setQuota(levelAgency.getQuota());
        return dto;
    }

    public void deleteLevelAgency(Long id) {
        BmsLevelAgency levelAgency = levelAgencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Level Agency not found"));
        levelAgencyRepository.delete(levelAgency);
    }
}
