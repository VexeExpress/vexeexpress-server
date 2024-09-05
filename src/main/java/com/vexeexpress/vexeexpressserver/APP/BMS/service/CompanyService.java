package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import com.vexeexpress.vexeexpressserver.repository.CompanyRepository;
import com.vexeexpress.vexeexpressserver.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CompanyService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;


    public BmsBusCompany getCompanyById(Long id) {
        // Tìm công ty theo ID trong cơ sở dữ liệu
        Optional<BmsBusCompany> companyOptional = companyRepository.findById(id);

        // Kiểm tra xem công ty có tồn tại không
        if (companyOptional.isPresent()) {
            return companyOptional.get();
        } else {
            throw new EntityNotFoundException("Công ty không tồn tại với ID: " + id);
        }
    }
}
