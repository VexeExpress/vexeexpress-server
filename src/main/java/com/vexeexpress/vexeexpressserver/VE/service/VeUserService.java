package com.vexeexpress.vexeexpressserver.VE.service;

import com.vexeexpress.vexeexpressserver.VE.entity.VeUser;
import com.vexeexpress.vexeexpressserver.VE.repository.VeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service
public class VeUserService {
    @Autowired
    private VeUserRepository veUserRepository;

    public String register(VeUser veUser) {
        try {
            // Email Validation
            if (veUser.getEmail() == null || veUser.getEmail().isEmpty()) {
                return "Vui lòng nhập email";
            }
            VeUser existingUser = veUserRepository.findByEmail(veUser.getEmail());
            if (existingUser != null) {
                return "Email đã được sử dụng";
            }

            // Password Validation
            if (veUser.getPassword() == null || veUser.getPassword().isEmpty()) {
                return "Vui lòng nhập mật khẩu";
            }
            // Consider minimum password length (e.g., 8 characters)
            int minPasswordLength = 8;
            if (veUser.getPassword().length() < minPasswordLength) {
                return "Mật khẩu phải có ít nhất " + minPasswordLength + " ký tự";
            }

            // Name Validation (Optional)
            if (veUser.getName() == null || veUser.getName().isEmpty()) {
                return "Vui lòng nhập tên";
            }

            // Password Encoding
            String encodedPassword = new BCryptPasswordEncoder().encode(veUser.getPassword());
            veUser.setPassword(encodedPassword);

            // Save User
            veUserRepository.save(veUser);
            return "Đăng ký thành công";
        } catch (Exception e) {
            return "Hệ thống tạm thời đang gặp sự cố. Vui lòng thử lại sau.";
        }
    }
    public String login(VeUser veUser){
        try {
            // Kiểm tra xem email và mật khẩu có được cung cấp không
            if (veUser.getEmail() == null || veUser.getEmail().isEmpty() || veUser.getPassword() == null || veUser.getPassword().isEmpty()) {
                return "Vui lòng nhập đầy đủ thông tin đăng nhập";
            }

            // Xác thực thông tin đăng nhập
            VeUser existingUser = veUserRepository.findByEmail(veUser.getEmail());

            // Kiểm tra xem người dùng có tồn tại không
            if(existingUser == null) {
                return "Tài khoản không tồn tại";
            }

            // Kiểm tra xem mật khẩu có đúng không
            boolean passwordMatches = new BCryptPasswordEncoder().matches(veUser.getPassword(), existingUser.getPassword());
            if(!passwordMatches){
                return "Mật khẩu không chính xác";
            }

            // Nếu thông tin đăng nhập hợp lệ
            return "Đăng nhập thành công";
        } catch (Exception e) {
            return "Hệ thống tạm thời đang gặp sự cố. Vui lòng thử lại sau.";
        }
    }
    public String loginGoogle(VeUser veUser){
        try{
            return "Đăng nhập thành công với tên người dùng:";
        } catch (Exception e) {
            return "Đăng nhập bằng Google thất bại";
        }
    }
    public String loginFacebook(VeUser veUser){
        try{
            return "Đăng nhập thành công với tên người dùng:";
        } catch (Exception e) {
            return "Đăng nhập bằng Facebook thất bại";
        }
    }

}
