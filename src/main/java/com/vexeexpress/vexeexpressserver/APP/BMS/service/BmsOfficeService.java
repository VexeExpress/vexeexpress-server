package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.entity.BmsOffice;
import com.vexeexpress.vexeexpressserver.repository.BmsOfficeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BmsOfficeService {

    @Autowired
    private BmsOfficeRepository bmsOfficeRepository;

    public BmsOffice createOffice(BmsOffice bmsOffice) {
        return bmsOfficeRepository.save(bmsOffice);
    }
    
    public List<BmsOffice> getOfficesByCompanyId(Long companyId) {
        return bmsOfficeRepository.findByCompanyId(companyId);
    }
    
}
