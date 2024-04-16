package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.repository.Bms_BusCompanyRepository;
import com.vexeexpress.vexeexpressserver.APP.BMS.repository.Bms_UserRepository;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Bms_AuthService {
    @Autowired
    Bms_UserRepository bmsUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Bms_BusCompanyRepository bmsBusCompanyRepository;

    public String loginApp(String username, String password) {
        BmsUser user = bmsUserRepository.findByUsername(username);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                if (user.getStatus()) {
                    Optional<BmsBusCompany> bmsBusCompanyOptional = bmsBusCompanyRepository.findById(user.getCompanyId());
                    if (bmsBusCompanyOptional.isPresent()) {
                        BmsBusCompany bmsBusCompany = bmsBusCompanyOptional.get();
                        if (bmsBusCompany.getActiveStatus()) {
                            return user.getId(); // Trả về ID của người dùng
                        } else {
                            return "5"; // Mã lỗi công ty bị vô hiệu hóa
                        }
                    } else {
                        return "6"; // Mã lỗi công ty không tồn tại
                    }
                } else {
                    return "3"; // Mã lỗi tài khoản đã bị khoá
                }
            } else {
                return "-1"; // Mã lỗi mật khẩu không đúng
            }
        } else {
            return "0"; // Mã lỗi tài khoản không tồn tại
        }
    }

}
