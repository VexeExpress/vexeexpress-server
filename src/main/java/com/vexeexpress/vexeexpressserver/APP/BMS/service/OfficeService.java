package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.CompanyDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.OfficeDTO;
import com.vexeexpress.vexeexpressserver.entity.BmsOffice;
import com.vexeexpress.vexeexpressserver.repository.BmsOfficeRepository;

import java.util.List;
import java.util.stream.Collectors;

import com.vexeexpress.vexeexpressserver.repository.OfficeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeService {

    @Autowired
    private BmsOfficeRepository bmsOfficeRepository;
    @Autowired
    private OfficeRepository officeRepository;

    public BmsOffice createOffice(BmsOffice bmsOffice) {
        return bmsOfficeRepository.save(bmsOffice);
    }

    public List<OfficeDTO> getOfficesByCompanyId(Long companyId) {
        List<BmsOffice> offices = officeRepository.findByCompanyId(companyId);

        if (offices == null || offices.isEmpty()) {
            throw new EntityNotFoundException("No offices found for companyId: " + companyId);
        }

        // Convert entities to DTOs
        return offices.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private OfficeDTO convertToDTO(BmsOffice office) {
        OfficeDTO dto = new OfficeDTO();
        dto.setId(office.getId());
        dto.setName(office.getName());
        dto.setPhone(office.getPhone());
        dto.setCode(office.getCode());
        dto.setAddress(office.getAddress());
        dto.setNote(office.getNote());
        if (office.getCompany() != null) {
            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setId(office.getCompany().getId());


            dto.setCompany(companyDTO);
        }

        return dto;
    }

    public boolean deleteOfficeById(Long id) {
        if (bmsOfficeRepository.existsById(id)) {
            bmsOfficeRepository.deleteById(id);
            return true;  // Xóa thành công
        }
        return false;  // Không tìm thấy mục để xóa
    }

    public boolean updateOffice(Long id, BmsOffice updatedOffice) {
        if (bmsOfficeRepository.existsById(id)) {
            updatedOffice.setId(id);
            bmsOfficeRepository.save(updatedOffice);
            return true;
        }
        return false;
    }


    public boolean doesOfficeExist(String name, Long companyId) {
        return officeRepository.existsByNameAndCompanyId(name, companyId);
    }


}
