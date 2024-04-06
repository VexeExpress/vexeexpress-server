package com.vexeexpress.vexeexpressserver.APP.ADMIN.service;

import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.APP.BMS.repository.BmsBusCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BmsBusCompanyService {
    @Autowired
    private BmsBusCompanyRepository bmsBusCompanyRepository;
    public String addBusCompany(BmsBusCompany bmsBusCompany){
        try {
            // Kiểm tra xem nhà xe đã tồn tại hay chưa
            BmsBusCompany existingBusCompany = bmsBusCompanyRepository.findByCompanyName(bmsBusCompany.getCompanyName());
            if(existingBusCompany != null){
                System.out.println("Nhà xe đã tồn tại!");
                return "Nhà xe đã tồn tại!";
            }
            // Lưu thông tin nhà xe vào cơ sở dữ liệu
            BmsBusCompany savedCompany = bmsBusCompanyRepository.save(bmsBusCompany);
            if (savedCompany != null) {
                return "Tạo thành công";
            } else {
                return "Tạo thất bại";
            }
        } catch (Exception ex) {
            System.out.println("Đã xảy ra lỗi khi thêm nhà xe: " + ex.getMessage());
            return "Lỗi hệ thống";
        }
    }
}
