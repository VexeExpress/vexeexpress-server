package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import com.vexeexpress.vexeexpressserver.repository.UserRepository;

@Service
public class BmsUserService {
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


    public BmsUser createUser(BmsUser bmsUser) {
        return userRepository.save(bmsUser);
    }

    public Long getCompanyIdByUserId(String userId) {
        Long userIdLong;
        try {
            userIdLong = Long.parseLong(userId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid userId format: " + userId);
        }

        BmsUser user = userRepository.findById(userIdLong)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getCompanyId();
    }
}
