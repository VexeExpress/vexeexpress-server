package com.vexeexpress.vexeexpressserver.APP.ADMIN.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vexeexpress.vexeexpressserver.APP.ADMIN.repository.AdminBusCompanyRepository;
import com.vexeexpress.vexeexpressserver.APP.ADMIN.repository.AdminUserRepository;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;

import io.micrometer.common.util.StringUtils;

@Service
public class AdminUserService {
    @Autowired
    AdminUserRepository adminUserRepository;
    @Autowired
    AdminBusCompanyRepository adminBusCompanyRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    // Thêm tài khoản nhân viên
    public String addUser(BmsUser bmsUser) {
        // Kiểm tra nếu bmsUser là null
        if (bmsUser == null) {
            return "Thông tin tài khoản không hợp lệ";
        }

        // Kiểm tra các trường bắt buộc
        if (StringUtils.isEmpty(bmsUser.getName()) ||
                StringUtils.isEmpty(bmsUser.getEmail()) ||
                StringUtils.isEmpty(bmsUser.getPhone()) ||
                bmsUser.getGender() == null ||  // Gender should be checked for null, not empty
                bmsUser.getRole() == null ||
                StringUtils.isEmpty(bmsUser.getUsername()) ||
                StringUtils.isEmpty(bmsUser.getPassword()) ||
                bmsUser.getCompany() == null ||  // Check if the company is null first
                bmsUser.getCompany().getId() == null) {  // Then check the company ID
            return "Vui lòng nhập đầy đủ thông tin tài khoản";
        }

        // Kiểm tra ràng buộc của mật khẩu
        if (bmsUser.getPassword().length() < 8) {
            return "Mật khẩu phải có ít nhất 8 ký tự";
        }

        // Kiểm tra xem username đã tồn tại trong cơ sở dữ liệu chưa
        BmsUser existingUser = adminUserRepository.findByUsername(bmsUser.getUsername());
        if (existingUser != null) {
            return "Tài khoản đã tồn tại";
        }

        // Kiểm tra tính tồn tại của companyId
        Optional<BmsBusCompany> company = adminBusCompanyRepository.findById(bmsUser.getCompany().getId());
        if (company.isEmpty()) {
            return "ID công ty không tồn tại";
        }

        // Mã hoá mật khẩu trước khi lưu vào cơ sở dữ liệu
        String encodedPassword = passwordEncoder.encode(bmsUser.getPassword());
        bmsUser.setPassword(encodedPassword);

        // Lưu tài khoản vào cơ sở dữ liệu
        adminUserRepository.save(bmsUser);
        return "Thêm tài khoản thành công";
    }


    // Truy xuất danh sách tài khoản thuộc công ty (companyId)
    public List<BmsUser> getListUserByCompanyId(Long companyId) {
        return adminUserRepository.findByCompanyId(companyId);
    }

}
