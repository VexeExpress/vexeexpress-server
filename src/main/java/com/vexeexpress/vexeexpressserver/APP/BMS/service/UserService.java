package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import com.vexeexpress.vexeexpressserver.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public String getNameUserById(Long id) {
        Optional<BmsUser> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            BmsUser user = optionalUser.get();
            return user.getName();
        } else {
            return null;
        }
    }
    public Long getCompanyIdByUserId(Long userId) {
        if (userId == null) {
            return null; // Trả về null nếu userId không hợp lệ
        }
        Optional<BmsUser> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userOptional.get().getCompany().getId();
        } else {
            return null; // Trả về null nếu không tìm thấy người dùng
        }
    }


    public BmsUser createUser(BmsUser bmsUser) {
        return userRepository.save(bmsUser);
    }
    
    // Hàm mã hóa mật khẩu (bạn có thể sử dụng các thư viện mã hóa như BCrypt)
    private String hashPassword(String password) {
        // Giả sử bạn sử dụng BCrypt
        return new BCryptPasswordEncoder().encode(password);
    }

    public List<BmsUser> getUsersByCompanyId(Long companyId) {
        return userRepository.findByCompanyId(companyId);
    }

    public void deleteUser(Long id) throws Exception {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new Exception("User not found");
        }
    }
    public BmsUser updateUser(Long id, BmsUser updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setPassword(updatedUser.getPassword());
                    user.setName(updatedUser.getName());
                    user.setPhone(updatedUser.getPhone());
                    user.setBirth(updatedUser.getBirth());
                    user.setRole(updatedUser.getRole());
                    user.setGender(updatedUser.getGender());
                    user.setEmail(updatedUser.getEmail());
                    user.setStatus(updatedUser.getStatus());
                    return userRepository.save(user);
                })
                .orElse(null);
    }


    public String getUserNameById(Long userId) {
        // Tìm kiếm người dùng theo ID
        BmsUser user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy người dùng với ID: " + userId));
        // Trả về tên người dùng nếu tìm thấy
        return user.getName();
    }

    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public List<BmsUser> getAllUsersByCompanyId(Long companyId) {
        return userRepository.findAllByCompanyId(companyId);
    }

    public List<BmsUser> getEmployeesByCompanyId(Long companyId) {
        return userRepository.findEmployeesByCompanyId(companyId);
    }

    public List<BmsUser> getDriversByCompanyId(Long companyId) {
        return userRepository.findDriversByCompanyId(companyId);
    }
}
