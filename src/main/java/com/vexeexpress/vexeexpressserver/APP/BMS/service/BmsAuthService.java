package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.repository.BmsUserRepository;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BmsAuthService {
    @Autowired
    BmsUserRepository bmsUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public String loginApp(BmsUser bmsUser){
        String username = bmsUser.getUsername();
        String password = bmsUser.getPassword();

        // Tìm kiếm người dùng trong cơ sở dữ liệu bằng username
        Optional<BmsUser> userOptional = Optional.ofNullable(bmsUserRepository.findByUsername(username));

        // Kiểm tra xem người dùng có tồn tại không
        if(userOptional.isPresent()){
            BmsUser user = userOptional.get();
            if(passwordEncoder.matches(password,user.getPassword())){
                System.out.println("Đăng nhập thành công");
                return user.getId();
            } else {
                System.out.println("Sai mật khẩu");
                return "-1";
            }
        } else {
            System.out.println("Người dùng không tồn tại");
            return "0";
        }
    }
}
