package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import com.vexeexpress.vexeexpressserver.repository.UserRepository;

@Service
public class BmsAuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public String login(String username, String password) {
        // Kiểm tra username và password trống
        if (username == null || username.isEmpty()) {
            return "1"; // Lỗi tên người dùng trống
        }
        if (password == null || password.isEmpty()) {
            return "2"; // Lỗi mật khẩu trống
        }
        // username ít hơn 12 ký tự
        if (username.length() < 12) {
            return "3"; // Tài khoản không tồn tại
        }
        // Tìm kiếm username trong cơ sở dữ liệu
        BmsUser user = userRepository.findByUsername(username);
        if (user == null) {
            return "3"; // Tài khoản không tồn tại
        }  else {
            String hashedPassword = user.getPassword();
            if(passwordEncoder.matches(password, hashedPassword)) {
                return "5"; // Đăng nhập thành công
            } else {
                return "4"; // Sai mật khẩu
            }
        }
    }

}
