package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<?> login(String username, String password) {
        // Kiểm tra username và password trống
        if (username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().body("Vui lòng nhập tài khoản"); // Lỗi tên người dùng trống
        }
        if (password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Vui lòng nhập mật khẩu"); // Lỗi mật khẩu trống
        }
        // username ít hơn 12 ký tự
        if (username.length() < 12) {
            return ResponseEntity.badRequest().body("Tài khoản không tồn tại"); // Tài khoản không tồn tại
        }
        // Tìm kiếm username trong cơ sở dữ liệu
        BmsUser user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().body("Tài khoản không tồn tại"); // Tài khoản không tồn tại
        } else {
            String status = user.getStatus();
            switch (status) {
                case "1":
                    // Tài khoản đang hoạt động, kiểm tra mật khẩu
                    String hashedPassword = user.getPassword();
                    if (passwordEncoder.matches(password, hashedPassword)) {
                        // Đăng nhập thành công, trả về ID của người dùng
                        return ResponseEntity.ok(user.getId());
                    } else {
                        return ResponseEntity.badRequest().body("Mật khẩu không chính xác"); // Sai mật khẩu
                    }
                case "2":
                    // Tài khoản bị khoá
                    return ResponseEntity.badRequest().body("Tài khoản bị khoá");
                default:
                    // Trường hợp khác không được xử lý
                    return ResponseEntity.badRequest().body("Lỗi không xác định");

            }
        }
    }

}
