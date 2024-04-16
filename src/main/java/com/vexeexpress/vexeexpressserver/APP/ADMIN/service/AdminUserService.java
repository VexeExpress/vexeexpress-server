package com.vexeexpress.vexeexpressserver.APP.ADMIN.service;

import com.vexeexpress.vexeexpressserver.APP.ADMIN.repository.Admin_UserRepository;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminUserService {
    @Autowired
    Admin_UserRepository adminUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String addUser(BmsUser bmsUser){
        String username = bmsUser.getUsername();
        Optional<BmsUser> existingUserName = Optional.ofNullable(adminUserRepository.findByUsername(username));
        if(existingUserName.isPresent()){
            System.out.println("Tài khoản đã tồn tại: " + username);
            return "0";
        } else {
            String encryptedPassword = passwordEncoder.encode(bmsUser.getPassword());
            bmsUser.setPassword(encryptedPassword);
            adminUserRepository.save(bmsUser);
            return "1";
        }
    }


    public List<BmsUser> getListUserByCompanyId(String companyId) {
        return adminUserRepository.findByCompanyId(companyId);
    }
}
