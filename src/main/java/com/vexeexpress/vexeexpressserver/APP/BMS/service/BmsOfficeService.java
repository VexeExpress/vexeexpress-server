package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.entity.BmsOffice;
import com.vexeexpress.vexeexpressserver.repository.BmsOfficeRepository;

import java.util.List;
import java.util.Optional;

import com.vexeexpress.vexeexpressserver.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BmsOfficeService {

    @Autowired
    private BmsOfficeRepository bmsOfficeRepository;
    @Autowired
    private OfficeRepository officeRepository;

    public BmsOffice createOffice(BmsOffice bmsOffice) {
        return bmsOfficeRepository.save(bmsOffice);
    }
    
    public List<BmsOffice> getOfficesByCompanyId(String companyId) {
        return bmsOfficeRepository.findByCompanyId(companyId);
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


    public boolean doesOfficeExist(String name) {
        Optional<Object> offices = bmsOfficeRepository.findByName(name);
        return offices.isPresent();
    }
}
