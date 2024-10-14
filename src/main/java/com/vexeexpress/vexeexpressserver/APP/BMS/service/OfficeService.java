package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.CompanyDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Office.OfficeDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Office.OfficeDTO_v2;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Office.OfficeDTO_v3;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Office.OfficeDTO_v4;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsOffice;
import com.vexeexpress.vexeexpressserver.repository.BmsOfficeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vexeexpress.vexeexpressserver.repository.CompanyRepository;
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
    @Autowired
    private CompanyRepository companyRepository;

//    public BmsOffice createOffice(BmsOffice bmsOffice) {
//        return bmsOfficeRepository.save(bmsOffice);
//    }

//    public List<OfficeDTO> getOfficesByCompanyId(Long companyId) {
//        List<BmsOffice> offices = officeRepository.findByCompanyId(companyId);
//
//        if (offices == null || offices.isEmpty()) {
//            throw new EntityNotFoundException("No offices found for companyId: " + companyId);
//        }
//
//        // Convert entities to DTOs
//        return offices.stream().map(this::convertToDTO).collect(Collectors.toList());
//    }

//    private OfficeDTO convertToDTO(BmsOffice office) {
//        OfficeDTO dto = new OfficeDTO();
//        dto.setId(office.getId());
//        dto.setName(office.getName());
//        dto.setPhone(office.getPhone());
//        dto.setCode(office.getCode());
//        dto.setAddress(office.getAddress());
//        dto.setNote(office.getNote());
//        if (office.getCompany() != null) {
//            CompanyDTO companyDTO = new CompanyDTO();
//            companyDTO.setId(office.getCompany().getId());
//
//
//            dto.setCompany(companyDTO);
//        }
//
//        return dto;
//    }

    public boolean deleteOfficeById(Long id) {
        if (bmsOfficeRepository.existsById(id)) {
            bmsOfficeRepository.deleteById(id);
            return true;  // Xóa thành công
        }
        return false;  // Không tìm thấy mục để xóa
    }

//    public boolean updateOffice(Long id, BmsOffice updatedOffice) {
//        if (bmsOfficeRepository.existsById(id)) {
//            updatedOffice.setId(id);
//            bmsOfficeRepository.save(updatedOffice);
//            return true;
//        }
//        return false;
//    }
//
//
//    public boolean doesOfficeExist(String name, Long companyId) {
//        return officeRepository.existsByNameAndCompanyId(name, companyId);
//    }


    public List<OfficeDTO_v2> getOfficesByCompanyId(Long companyId) {
        // 404
        if (companyId == null) {
            throw new IllegalArgumentException("companyId must not be null");
        }
        List<BmsOffice> offices = officeRepository.findByCompanyId(companyId);
        //204
        if(offices == null || offices.isEmpty()) {
            return null;
        }
        return offices.stream().map(this::convertToDTO_v2).collect(Collectors.toList());
    }
    private OfficeDTO_v2 convertToDTO_v2(BmsOffice office) {
        OfficeDTO_v2 dto = new OfficeDTO_v2();
        dto.setId(office.getId());
        dto.setName(office.getName());
        return dto;
    }

    public List<OfficeDTO_v3> getListOfficeDetailByCompanyId(Long companyId) {
        List<BmsOffice> offices = officeRepository.findByCompanyId(companyId);
        if(offices == null || offices.isEmpty()) {
            return null;
        }
        return offices.stream().map(this::convertToDTO_v3).collect(Collectors.toList());
    }

    private OfficeDTO_v3 convertToDTO_v3(BmsOffice office) {
        OfficeDTO_v3 dto = new OfficeDTO_v3();
        dto.setId(office.getId());
        dto.setName(office.getName());
        dto.setPhone(office.getPhone());
        dto.setCode(office.getCode());
        dto.setAddress(office.getAddress());
        dto.setNote(office.getNote());
        return dto;
    }

    public BmsOffice createOffice_v2(OfficeDTO_v4 dto) {
        Optional<BmsBusCompany> companyOpt = companyRepository.findById(dto.getCompanyId());
        if (companyOpt.isEmpty()) {
            throw new IllegalArgumentException("Company ID không hợp lệ.");
        }
        if (officeRepository.existsByNameAndCompany_Id(dto.getName(), dto.getCompanyId())) {
            throw new IllegalArgumentException("Tên văn phòng đã tồn tại trong công ty này.");
        }
        BmsOffice office = new BmsOffice();
        office.setName(dto.getName());
        office.setAddress(dto.getAddress());
        office.setCode(dto.getCode());
        office.setNote(dto.getNote());
        office.setPhone(dto.getPhone());
        office.setCompany(companyOpt.get());

        return officeRepository.save(office);
    }

    public BmsOffice updateOffice_v2(Long id, OfficeDTO_v3 dto) {
        Optional<BmsOffice> optionalOffice = officeRepository.findById(id);
        if (optionalOffice.isPresent()) {
            BmsOffice office = optionalOffice.get();
            office.setName(dto.getName());
            office.setPhone(dto.getPhone());
            office.setCode(dto.getCode());
            office.setAddress(dto.getAddress());
            office.setNote(dto.getNote());

            return officeRepository.save(office);
        } else {
            return null;
        }
    }
}
