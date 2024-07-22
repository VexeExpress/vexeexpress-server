package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.libs.ErrorMessage;
import com.vexeexpress.vexeexpressserver.APP.BMS.utils.JwtUtils;
import com.vexeexpress.vexeexpressserver.entity.BmsAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import com.vexeexpress.vexeexpressserver.repository.UserRepository;

import static com.vexeexpress.vexeexpressserver.APP.BMS.utils.JwtUtils.CheckValidLoginToken;

@Service
public class BmsAuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public String Login(String username, String password) {
        // Tìm kiếm username trong cơ sở dữ liệu
        BmsUser user = userRepository.findByUsername(username);
        if (user == null) {
            return ErrorMessage.NO_USER_FOUND.getMessage();
        }

        String status = user.getStatus();
        switch (status) {
            case "1":
                // Tài khoản đang hoạt động, kiểm tra mật khẩu
                String hashedPassword = user.getPassword();
                if (passwordEncoder.matches(password, hashedPassword)) {
                    return JwtUtils.GenerateToken(username);
                }

                return ErrorMessage.CANT_GENERATE_LOGIN_TOKEN.getMessage();
            case "2":
                // Tài khoản bị khoá
                return ErrorMessage.ACCOUNT_IS_LOCKED.getMessage();
            default:
                // Trường hợp khác không được xử lý
                return ErrorMessage.ERROR_IN_SERVER.getMessage();
        }
    }

    public boolean CheckLogin(String token) {
        return CheckValidLoginToken(token);
    }
}
