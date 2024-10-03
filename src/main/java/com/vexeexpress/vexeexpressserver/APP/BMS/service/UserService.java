package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.User.UserDTO_v2;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.UserDTO;
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
        // Hash the password before saving
        String hashedPassword = hashPassword(bmsUser.getPassword());
        bmsUser.setPassword(hashedPassword);
        return userRepository.save(bmsUser);
    }
    
    // Hàm mã hóa mật khẩu (bạn có thể sử dụng các thư viện mã hóa như BCrypt)
    private String hashPassword(String password) {
        // Giả sử bạn sử dụng BCrypt
        return new BCryptPasswordEncoder().encode(password);
    }

    public List<UserDTO> getUsersByCompanyId(Long companyId) {
        // Fetch the list of users by company ID
        List<BmsUser> users = userRepository.findByCompanyId(companyId);

        // Map the list of users to BmsUserDTO without using a separate mapping method
        List<UserDTO> userDTOs = users.stream()
                .map(user -> {
                    UserDTO dto = new UserDTO();
                    dto.setId(user.getId());
                    dto.setUsername(user.getUsername());
                    dto.setName(user.getName());
                    dto.setPhone(user.getPhone());
                    dto.setAddress(user.getAddress());
                    dto.setEmail(user.getEmail());
                    dto.setCccd(user.getCccd());
                    dto.setGender(user.getGender());
                    dto.setBirthDate(user.getBirthDate());
                    dto.setRole(user.getRole());
                    dto.setLicenseCategory(user.getLicenseCategory());
                    dto.setExpirationDate(user.getExpirationDate());
                    dto.setStatus(user.getStatus());
                    return dto;
                })
                .collect(Collectors.toList());
        return userDTOs;
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
                    user.setStatus(updatedUser.getStatus());
                    user.setName(updatedUser.getName());
                    user.setPhone(updatedUser.getPhone());
                    user.setAddress(updatedUser.getAddress());
                    user.setEmail(updatedUser.getEmail());
                    user.setCccd(updatedUser.getCccd());
                    user.setGender(updatedUser.getGender());
                    user.setBirthDate(updatedUser.getBirthDate());
                    user.setRole(updatedUser.getRole());
                    user.setLicenseCategory(updatedUser.getLicenseCategory());
                    user.setExpirationDate(updatedUser.getExpirationDate());

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

    public Optional<BmsUser> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public BmsUser lockUser(Long id) throws Exception {
        Optional<BmsUser> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            BmsUser user = optionalUser.get();
            user.setStatus(false);
            return userRepository.save(user);
        } else {
            throw new Exception("User not found with ID: " + id);
        }
    }

    public void changePassword(Long id, String newPassword) {
        BmsUser user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(hashPassword(newPassword));
        userRepository.save(user);
    }

    public List<UserDTO_v2> getNameUserByCompanyId(Long companyId) {
        List<BmsUser> users = userRepository.findByCompanyIdAndRole(companyId, 1);
        return users.stream().map(entity -> {
            UserDTO_v2 dto = new UserDTO_v2();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            return dto;
        }).collect(Collectors.toList());
    }
}
