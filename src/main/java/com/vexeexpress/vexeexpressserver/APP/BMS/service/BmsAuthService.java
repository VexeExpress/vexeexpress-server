package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.libs.ErrorMessage;
import com.vexeexpress.vexeexpressserver.APP.BMS.utils.JwtUtils;
import com.vexeexpress.vexeexpressserver.entity.BmsAgent;
import io.micrometer.common.util.StringUtils;
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
            return ResponseEntity.ok(user.getId());

        } else {
            return ResponseEntity.badRequest().body("Mật khẩu không chính xác");
        }
    }

}
