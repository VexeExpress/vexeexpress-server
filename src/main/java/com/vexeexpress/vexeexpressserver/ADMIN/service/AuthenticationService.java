package com.vexeexpress.vexeexpressserver.ADMIN.service;

import com.vexeexpress.vexeexpressserver.ADMIN.entity.Account;
import com.vexeexpress.vexeexpressserver.ADMIN.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private AccountRepository accountRepository;

    public String login(Account account) {
        try {
            Account existingAccount = accountRepository.findByUsername(account.getUsername());
            if (existingAccount == null){
                return "Tài khoản không tồn tại";
            }
            if(!existingAccount.getPassword().equals(account.getPassword())){
                return "Mật khẩu không chính xác";
            }
            return "Đăng nhập thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "Đã xảy ra lỗi khi xử lý yêu cầu đăng nhập";
        }
    }

}
