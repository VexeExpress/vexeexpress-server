package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Auth.DataLogin;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Auth.LoginForm;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.repository.CompanyRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import com.vexeexpress.vexeexpressserver.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CompanyRepository companyRepository;

    public ResponseEntity<?> login(String username, String password) {

        // Kiểm tra username và password trống
        if (StringUtils.isEmpty(username)) {
            return ResponseEntity.badRequest().body("Vui lòng nhập tài khoản");
        }
        if (StringUtils.isEmpty(password)) {
            return ResponseEntity.badRequest().body("Vui lòng nhập mật khẩu");
        }

        // username ít hơn 12 ký tự
        if (username.length() < 12) {
            return ResponseEntity.badRequest().body("Tài khoản không tồn tại");
        }

        // Tìm kiếm username trong cơ sở dữ liệu
        BmsUser user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().body("Tài khoản không tồn tại");
        }

        // Kiểm tra trạng thái tài khoản
        Boolean status = user.getStatus();
        if (status == null || !status) {
            return ResponseEntity.badRequest().body("Tài khoản bị khoá");
        }

        // Kiểm tra mật khẩu
        if (passwordEncoder.matches(password, user.getPassword())) {
            // Đăng nhập thành công, trả về ID của người dùng
            System.out.println("ID User Login: " + user.getId());
            return ResponseEntity.ok(user.getId());

        } else {
            return ResponseEntity.badRequest().body("Mật khẩu không chính xác");
        }
    }

    public DataLogin login_v2(LoginForm loginForm) {
        BmsUser user = userRepository.findByUsername(loginForm.getUsername());
        if (user == null) {
            return null; // 404
        }
        if (!passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
            throw new IllegalStateException("Invalid password");
        }
        if (!user.getStatus()) {
            throw new IllegalStateException("User is not active");
        }
        if (!user.getCompany().getStatus()) {
            throw new IllegalStateException("Company is not active");
        }
        DataLogin dataLogin = new DataLogin();
        dataLogin.setId(user.getId());
        dataLogin.setName(user.getName());
        dataLogin.setCompanyId(user.getCompany().getId());
        return dataLogin;
    }
}
