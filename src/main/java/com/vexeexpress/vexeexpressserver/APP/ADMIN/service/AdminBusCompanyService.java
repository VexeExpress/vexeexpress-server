package com.vexeexpress.vexeexpressserver.APP.ADMIN.service;

import com.vexeexpress.vexeexpressserver.APP.ADMIN.repository.AdminBusCompanyRepository;
import com.vexeexpress.vexeexpressserver.config.SecurityConfig;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminBusCompanyService {
    @Autowired
    private AdminBusCompanyRepository adminBusCompanyRepository;

    public String addBusCompany(BmsBusCompany bmsBusCompany){
        try {
            String companyName = bmsBusCompany.getCompanyName();
            Optional<BmsBusCompany> optionalBmsBusCompany = Optional.ofNullable(adminBusCompanyRepository.findByCompanyName(companyName));
            if(optionalBmsBusCompany.isPresent()){
                System.out.println("Nhà xe đã tồn tại: " + companyName);
                return "0";
            } else {
                adminBusCompanyRepository.save(bmsBusCompany);
                System.out.println("Da them nha xe thanh cong");
                return "1";
            }
        } catch (Exception ex) {
            System.out.println("Đã xảy ra lỗi khi thêm nhà xe: " + ex.getMessage());
            return "Lỗi hệ thống";
        }
    }
    public List<BmsBusCompany> getProcessedBusCompanies(){
        try{
            List<BmsBusCompany> busCompanies = adminBusCompanyRepository.findAll();
            return busCompanies.stream().map(company -> {
                BmsBusCompany filteredCompany = new BmsBusCompany();
                filteredCompany.setId(company.getId());
                filteredCompany.setCompanyName(company.getCompanyName());
                filteredCompany.setPhone(company.getPhone());
                filteredCompany.setAddress(company.getAddress());
                filteredCompany.setActiveStatus(company.getActiveStatus());
                return filteredCompany;
            }).collect(Collectors.toList());
        } catch (Exception ex){
            System.out.println("Đã xảy ra lỗi khi lấy danh sách: " + ex.getMessage());
            return null;
        }
    }

    public BmsBusCompany getCompanyInfoById(String id) {
        return adminBusCompanyRepository.findById(id).orElse(null);
    }
}
