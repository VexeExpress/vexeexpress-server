package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.utils.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.BmsAuthService;
import com.vexeexpress.vexeexpressserver.APP.BMS.utils.LoginForm;

import java.util.Objects;

import com.vexeexpress.vexeexpressserver.APP.BMS.libs.ErrorMessage;
import com.vexeexpress.vexeexpressserver.APP.BMS.libs.ReturnMessage;


@RestController
@RequestMapping("/bms/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class BmsAuthController {
    @Autowired
    BmsAuthService bmsAuthService;
    
    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody LoginForm loginForm, HttpServletResponse response) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        String token = bmsAuthService.Login(username, password);

        // Kiểm tra thông tin nhập vào
        if (Objects.equals(username, "") && Objects.equals(password, "")) {
            return ResponseEntity.badRequest().body(ReturnMessage.INVALID_LOGIN_INFORMATION.getMessage());
        }

        // Kiểm tra trường hợp
        if (Objects.equals(token, ErrorMessage.NO_USER_FOUND.getMessage())) {
            return ResponseEntity.badRequest().body(ReturnMessage.ACCOUNT_DOESNT_EXIST.getMessage());
        } else if (Objects.equals(token, ErrorMessage.CANT_GENERATE_LOGIN_TOKEN.getMessage())) {
            return ResponseEntity.internalServerError().body(ReturnMessage.CANT_GENERATE_LOGIN_TOKEN.getMessage());
        } else if (Objects.equals(token, ErrorMessage.ERROR_IN_SERVER.getMessage())) {
            return ResponseEntity.internalServerError().body(ReturnMessage.ERROR_IN_SERVER.getMessage());
        }

        // Tạo cookie với thuộc tính HttpOnly
        ResponseCookie cookie = ResponseCookie.from("auth-login-token", token)
                .httpOnly(true) // Chỉ có thể truy cập từ server
                .secure(false) // Chỉ gửi qua HTTPS
                .path("/") // Đường dẫn cookie (điều chỉnh theo nhu cầu)
                .maxAge(24 * 60 * 60) // Thời gian sống của cookie (1 ngày)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok().body(ReturnMessage.LOGIN_SUCCESS.getMessage());
    }

    @GetMapping("/check-login")
    public ResponseEntity<?> CheckLogin(HttpServletRequest request) {
        // Lấy giá trị của cookie từ yêu cầu
        Cookie[] cookies = request.getCookies();
        String token = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("auth-login-token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        // Kiểm tra xem có token không
        if (token == null) {
            return ResponseEntity.badRequest().body(ReturnMessage.NO_LOGIN_TOKEN.getMessage());
        }

        // Kiểm tra token và trả về kết quả
        boolean isValid = JwtUtils.CheckValidLoginToken(token);

        // Trả về nếu token login không ho74p ls
        if (!isValid) {
            return ResponseEntity.badRequest().body(ReturnMessage.INVALID_TOKEN.getMessage());
        }

        return ResponseEntity.ok().body(ReturnMessage.LOGIN_SUCCESS.getMessage());
    }

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
