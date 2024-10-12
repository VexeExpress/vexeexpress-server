package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Auth.DataLogin;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.AuthService;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Auth.LoginForm;

import com.vexeexpress.vexeexpressserver.APP.BMS.libs.ReturnMessage;


@RestController
@RequestMapping("/bms/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    @Autowired
    AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        try {
            // Gọi phương thức login từ BmsAuthService
            ResponseEntity<?> response = authService.login(username, password);
            return response; // Trả về phản hồi từ service
        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về phản hồi lỗi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Đã xảy ra lỗi trong quá trình đăng nhập: " + e.getMessage());
        }
    }

    @PostMapping("/login-2")
    public ResponseEntity<DataLogin> login_v2(@RequestBody LoginForm loginForm) {
        try {
            DataLogin dataLogin = authService.login_v2(loginForm);

            if (dataLogin == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 - Không tìm thấy người dùng
            }

            return ResponseEntity.ok(dataLogin); // 200 - Thành công

        } catch (IllegalStateException e) {
            if (e.getMessage().equals("Invalid password")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401 - Mật khẩu không đúng
            }
            if (e.getMessage().equals("User is not active")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 - Người dùng bị khóa
            } else if (e.getMessage().equals("Company is not active")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 - Công ty bị khóa
            }

            // Trường hợp ngoại lệ khác
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 - Lỗi server
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 - Lỗi server
        }
    }



//    @GetMapping("/check-login")
//    public ResponseEntity<?> CheckLogin(HttpServletRequest request) {
//        // Lấy giá trị của cookie từ yêu cầu
//        Cookie[] cookies = request.getCookies();
//        String token = null;
//
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("auth-login-token".equals(cookie.getName())) {
//                    token = cookie.getValue();
//                    break;
//                }
//            }
//        }
//
//        // Kiểm tra xem có token không
//        if (token == null) {
//            return ResponseEntity.badRequest().body(ReturnMessage.NO_LOGIN_TOKEN.getMessage());
//        }
//
//        // Kiểm tra token và trả về kết quả
//        boolean isValid = JwtUtils.CheckValidLoginToken(token);
//
//        // Trả về nếu token login không ho74p ls
//        if (!isValid) {
//            return ResponseEntity.badRequest().body(ReturnMessage.INVALID_TOKEN.getMessage());
//        }
//
//        return ResponseEntity.ok().body(ReturnMessage.LOGIN_SUCCESS.getMessage());
//    }

    @DeleteMapping("/logout")
    public  ResponseEntity<?> Logout(HttpServletResponse response) {
        // Tạo cookie với tên giống như cookie đã thiết lập nhưng với giá trị rỗng
        Cookie cookie = new Cookie("auth-login-token", null);
        cookie.setHttpOnly(true); // Đặt thuộc tính HttpOnly
        cookie.setSecure(true); // Đặt thuộc tính Secure
        cookie.setPath("/"); // Đặt đường dẫn của cookie
        cookie.setMaxAge(0); // Đặt thời gian sống của cookie thành 0 để xóa

        // Thêm cookie vào response để xóa trên client
        response.addCookie(cookie);

        return ResponseEntity.ok().body(ReturnMessage.LOGOUT_SUCCESS.getMessage());
    }
}
