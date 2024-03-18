package com.vexeexpress.vexeexpressserver.VE.service;

import com.vexeexpress.vexeexpressserver.VE.entity.VeUser;
import com.vexeexpress.vexeexpressserver.VE.repository.VeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service
public class VeUserService {
    @Autowired
    private VeUserRepository veUserRepository;

    public String register(VeUser veUser) {
        try{
            VeUser existingUser = veUserRepository.findByEmail(veUser.getEmail());
            if(existingUser != null) {
                return "Email đã được sử dụng";
            }
            if (veUser.getEmail() == null || veUser.getEmail().isEmpty()) {
                return "Vui lòng nhập email";
            }
            if (veUser.getPassword() == null || veUser.getPassword().isEmpty()) {
                return "Vui lòng nhập mật khẩu";
            }
            if (veUser.getName() == null || veUser.getName().isEmpty()) {
                return "Vui lòng nhập tên";
            }
            String encodedPassword = new BCryptPasswordEncoder().encode(veUser.getPassword());
            veUser.setPassword(encodedPassword);
            
            veUserRepository.save(veUser);
            return "Đăng ký thành công";
        } catch (Exception e){
            return "Hệ thống xảy ra lỗi";
        }
    }
}
