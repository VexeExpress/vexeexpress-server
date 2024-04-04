package com.vexeexpress.vexeexpressserver.ADMIN.service;

import com.vexeexpress.vexeexpressserver.ADMIN.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.ADMIN.entity.BmsUser;
import com.vexeexpress.vexeexpressserver.ADMIN.repository.BmsBusCompanyRepository;
import com.vexeexpress.vexeexpressserver.ADMIN.repository.BmsUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BmsUserService {
    @Autowired
    private BmsUserRepository bmsUserRepository;
    @Autowired
    private BmsBusCompanyRepository bmsBusCompanyRepository;
    public String addUser(BmsUser bmsUser){
        try {
            // Kiểm tra xem người dùng đã tồn tại hay chưa
            BmsUser existingUser = bmsUserRepository.findByUsername(bmsUser.getUsername());
            if(existingUser != null){
                System.out.println("Tài khoản đã tồn tại!");
                return "Tài khoản đã tồn tại!";
            }
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
