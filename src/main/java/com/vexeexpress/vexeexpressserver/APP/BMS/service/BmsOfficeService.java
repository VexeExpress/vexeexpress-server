package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.entity.BmsOffice;
import com.vexeexpress.vexeexpressserver.repository.BmsOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BmsOfficeService {
    @Autowired
    private BmsOfficeRepository bmsOfficeRepository;
    public BmsOffice createOffice(BmsOffice bmsOffice) {
        return bmsOfficeRepository.save(bmsOffice);
    }

    public List<BmsOffice> getOfficesByCompanyId(String companyId) {
        return bmsOfficeRepository.findByCompanyId(companyId);
    }
}
