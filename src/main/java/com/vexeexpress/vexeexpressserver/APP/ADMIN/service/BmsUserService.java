package com.vexeexpress.vexeexpressserver.APP.ADMIN.service;

import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import com.vexeexpress.vexeexpressserver.APP.BMS.repository.BmsBusCompanyRepository;
import com.vexeexpress.vexeexpressserver.APP.BMS.repository.BmsUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BmsUserService {
    @Autowired
    private BmsUserRepository bmsUserRepository;
    @Autowired
    private BmsBusCompanyRepository bmsBusCompanyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String addUser(BmsUser bmsUser){
        try {
            // Kiểm tra xem người dùng đã tồn tại hay chưa
            BmsUser existingUser = bmsUserRepository.findByUsername(bmsUser.getUsername());
            if(existingUser != null){
                System.out.println("Tài khoản đã tồn tại!");
                return "Tài khoản đã tồn tại!";
            }
            // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
            String encodedPassword = passwordEncoder.encode(bmsUser.getPassword());
            bmsUser.setPassword(encodedPassword);
            // Gán Ngày tạo
            bmsUser.setCreatedDate(new Date());
            // Lưu người dùng vào cơ sở dữ liệu
            bmsUserRepository.save(bmsUser);
            System.out.println("Tạo tài khoản thành công!");
            // Trả về ID của người dùng mới được tạo
            return bmsUser.getId();
        } catch (Exception ex) {
            System.out.println("Đã xảy ra lỗi khi thêm tài khoản: " + ex.getMessage());
            return "Lỗi hệ thống";
        }
    }
    public List<Map<String, String>> getListUser(BmsUser bmsUser){
        List<BmsUser> users = bmsUserRepository.findAll();
        List<Map<String, String>> userList = new ArrayList<>();
        for (BmsUser user : users) {
            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("id", user.getId());
            userInfo.put("name", user.getOwnerName());
            userInfo.put("phone", user.getPhoneNumber());
            userInfo.put("address",user.getAddress());
            userInfo.put("username", user.getUsername());
            userInfo.put("status", String.valueOf(user.getActivateAccount()));
            BmsBusCompany busCompany = bmsBusCompanyRepository.findByIdUser(user.getId());
            if(busCompany != null){
                userInfo.put("companyName", busCompany.getCompanyName());
            }
            userList.add(userInfo);
        }
        return userList;
    }
}
