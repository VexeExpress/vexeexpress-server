package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.LevelAgency.LevelAgencyDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.LevelAgency.LevelAgencyDTO_v2;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsLevelAgency;
import com.vexeexpress.vexeexpressserver.entity.BmsVehicle;
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



    public List<LevelAgencyDTO> getListLevelAgencyDetailByCompanyId(Long companyId) {
        if (companyId == null) {
            throw new IllegalArgumentException("companyId must not be null");
        }
        List<BmsLevelAgency> levelAgencies = levelAgencyRepository.findByCompanyId(companyId);
        if(levelAgencies == null || levelAgencies.isEmpty()) {
            return null;
        }
        return levelAgencies.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private LevelAgencyDTO convertToDTO(BmsLevelAgency levelAgency) {
        LevelAgencyDTO dto = new LevelAgencyDTO();
        dto.setId(levelAgency.getId());
        dto.setLevelName(levelAgency.getLevelName());
        dto.setQuota(levelAgency.getQuota());
        return dto;
    }

    public BmsLevelAgency createLevelAgency_2(LevelAgencyDTO dto) {
        Optional<BmsBusCompany> companyOpt = companyRepository.findById(dto.getCompanyId());
        if (companyOpt.isEmpty()) {
            throw new IllegalArgumentException("Company ID không hợp lệ.");
        }
        if (levelAgencyRepository.existsByLevelNameAndCompany_Id(dto.getLevelName(), dto.getCompanyId())) {
            throw new IllegalArgumentException("Cấp đại lý đã tồn tại trong công ty này.");
        }
        BmsLevelAgency levelAgency = new BmsLevelAgency();
        levelAgency.setLevelName(dto.getLevelName());
        levelAgency.setQuota(dto.getQuota());
        levelAgency.setCompany(companyOpt.get());
        return levelAgencyRepository.save(levelAgency);
    }

    public BmsLevelAgency updateLevelAgency_v2(Long id, LevelAgencyDTO dto) {
        Optional<BmsLevelAgency> levelAgencyOptional = levelAgencyRepository.findById(id);
        if (levelAgencyOptional.isPresent()) {
            BmsLevelAgency currentLevelAgency = levelAgencyOptional.get();
            Optional<BmsBusCompany> companyOpt = companyRepository.findById(dto.getCompanyId());
            if (companyOpt.isEmpty()) {
                throw new IllegalArgumentException("Company ID không hợp lệ.");
            }
            if (!currentLevelAgency.getLevelName().equals(dto.getLevelName())) {
                boolean levelNameExists = levelAgencyRepository.existsByLevelNameAndCompany_IdAndIdNot(dto.getLevelName(), dto.getCompanyId(), currentLevelAgency.getId());
                if (levelNameExists) {
                    System.out.println("Cấp đại lý: " + dto.getLevelName() + " đã tồn tại trong công ty: " + dto.getCompanyId());
                    return null;
                }
            }
            currentLevelAgency.setLevelName(dto.getLevelName());
            currentLevelAgency.setQuota(dto.getQuota());

            return levelAgencyRepository.save(currentLevelAgency);
        } else {
            return null;
        }
    }

    public void deleteLevelAgency(Long id) throws Exception {
        if (levelAgencyRepository.existsById(id)) {
            levelAgencyRepository.deleteById(id);
        } else {
            throw new Exception("Level Agency not found");
        }
    }
}
