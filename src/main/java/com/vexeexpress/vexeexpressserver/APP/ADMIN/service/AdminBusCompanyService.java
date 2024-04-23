package com.vexeexpress.vexeexpressserver.APP.ADMIN.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.vexeexpress.vexeexpressserver.APP.ADMIN.repository.AdminBusCompanyRepository;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;

@Service
public class AdminBusCompanyService {
    @Autowired
    AdminBusCompanyRepository adminBusCompanyRepository;

    // Thêm công ty mới
    public String addBusCompany(BmsBusCompany busCompany) {
        System.out.println(busCompany);
        // Kiểm tra sự tồn tại companyName trong cơ sở dữ liệu
        Optional<BmsBusCompany> existingCompany = adminBusCompanyRepository
                .findByCompanyName(busCompany.getCompanyName());
        if (existingCompany.isPresent()) {
            // companyName đã tồn tại, không thể thêm mới
            return "2";
        } else {
            // companyName không tồn tại, thêm mới data vào cơ sở dữ liệu
            adminBusCompanyRepository.save(busCompany);
            return "1";
        }
    }

    // Truy xuất danh sách công ty
    public List<BmsBusCompany> getAllBusCompanies() {
        return adminBusCompanyRepository.findAll();
    }

    // Truy xuất thông tin công ty dựa theo id
    public BmsBusCompany getBusCompanyById(Long id) {
        Optional<BmsBusCompany> optional = adminBusCompanyRepository.findById(id);
        return optional.orElse(null);
    }

}
