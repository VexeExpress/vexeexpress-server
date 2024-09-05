package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import java.util.List;
import java.util.Optional;

import com.vexeexpress.vexeexpressserver.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import com.vexeexpress.vexeexpressserver.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CompanyRepository companyRepository;

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
        String encodedPassword = passwordEncoder.encode(bmsUser.getPassword());
        bmsUser.setPassword(encodedPassword);
        return userRepository.save(bmsUser);
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


}
