package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.User.UserDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.User.UserDTO_v2;
import com.vexeexpress.vexeexpressserver.config.SecurityConfig;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsOffice;
import com.vexeexpress.vexeexpressserver.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import com.vexeexpress.vexeexpressserver.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

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

//    public List<UserDTO> getUsersByCompanyId(Long companyId) {
//        // Fetch the list of users by company ID
//        List<BmsUser> users = userRepository.findByCompanyId(companyId);
//
//        // Map the list of users to BmsUserDTO without using a separate mapping method
//        List<UserDTO> userDTOs = users.stream()
//                .map(user -> {
//                    UserDTO dto = new UserDTO();
//                    dto.setId(user.getId());
//                    dto.setUsername(user.getUsername());
//                    dto.setName(user.getName());
//                    dto.setPhone(user.getPhone());
//                    dto.setAddress(user.getAddress());
//                    dto.setEmail(user.getEmail());
//                    dto.setCccd(user.getCccd());
//                    dto.setGender(user.getGender());
//                    dto.setBirthDate(user.getBirthDate());
//                    dto.setRole(user.getRole());
//                    dto.setLicenseCategory(user.getLicenseCategory());
//                    dto.setExpirationDate(user.getExpirationDate());
//                    dto.setStatus(user.getStatus());
//                    return dto;
//                })
//                .collect(Collectors.toList());
//        return userDTOs;
//    }


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

//    public boolean usernameExists(String username) {
//        return userRepository.existsByUsername(username);
//    }

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





    public List<UserDTO_v2> getNameUserByCompanyId(Long companyId) {
        List<BmsUser> users = userRepository.findByCompanyIdAndRole(companyId, 1);
        return users.stream().map(entity -> {
            UserDTO_v2 dto = new UserDTO_v2();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            return dto;
        }).collect(Collectors.toList());
    }

    /////
    public List<UserDTO> getListUserByCompanyId(Long companyId) {
        if (companyId == null) {
            throw new IllegalArgumentException("companyId must not be null");
        }
        List<BmsUser> users = userRepository.findByCompanyId(companyId);
        if(users == null || users.isEmpty()) {
            return null;
        }
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    private UserDTO convertToDTO(BmsUser user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setCccd(user.getCccd());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        dto.setBirthDate(user.getBirthDate());
        dto.setExpirationDate(user.getExpirationDate());
        dto.setRole(user.getRole());
        dto.setGender(user.getGender());
        dto.setUsername(user.getUsername());
        dto.setLicenseCategory(user.getLicenseCategory());
        dto.setStatus(user.getStatus());
        dto.setName(user.getName());
        return dto;
    }

    public BmsUser createUser_v2(UserDTO dto) {
        System.out.println(dto);
        Optional<BmsBusCompany> companyOpt = companyRepository.findById(dto.getCompanyId());
        if (companyOpt.isEmpty()) {
            throw new IllegalArgumentException("Company ID không hợp lệ.");
        }
        if (userRepository.existsByUsernameAndCompany_Id(dto.getUsername(), dto.getCompanyId())) {
            throw new IllegalArgumentException("Tên tài khoản đã tồn tại trong công ty này.");
        }
        BmsUser user = new BmsUser();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setGender(dto.getGender());
        user.setExpirationDate(dto.getExpirationDate());
        user.setLicenseCategory(dto.getLicenseCategory());
        user.setCccd(dto.getCccd());
        user.setBirthDate(dto.getBirthDate());
        user.setEmail(dto.getEmail());
        user.setCompany(companyOpt.get());
        user.setRole(dto.getRole());
        user.setStatus(dto.getStatus());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    public BmsUser updateUser_v2(Long id, UserDTO dto) {
        System.out.println("Bắt đầu cập nhật người dùng với ID: " + id);
        System.out.println("Kiểm tra dữ liệu của UserDTO:" + dto);

        // Tìm người dùng hiện tại theo ID
        Optional<BmsUser> optionalBmsUser = userRepository.findById(id);
        System.out.println("Kết quả tìm kiếm người dùng: " + optionalBmsUser.isPresent());

        // Nếu người dùng tồn tại
        if (optionalBmsUser.isPresent()) {
            BmsUser currentUser = optionalBmsUser.get();
            System.out.println("Người dùng hiện tại: " + currentUser.getUsername());

            // Kiểm tra nếu companyId hợp lệ
            Optional<BmsBusCompany> companyOpt = companyRepository.findById(dto.getCompanyId());
            if (companyOpt.isEmpty()) {
                throw new IllegalArgumentException("Company ID không hợp lệ.");
            }

            // Kiểm tra nếu có tài khoản khác (khác ID hiện tại) đã sử dụng username này trong công ty
            boolean usernameExists = userRepository.existsByUsernameAndCompany_IdAndIdNot(dto.getUsername(), dto.getCompanyId(), currentUser.getId());
            System.out.println("Kiểm tra tồn tại tên tài khoản: " + dto.getUsername() + ", công ty: " + dto.getCompanyId() + ", ID hiện tại: " + currentUser.getId());
            System.out.println("Kết quả kiểm tra: " + usernameExists);

            if (usernameExists) {
                System.out.println("Tên tài khoản '" + dto.getUsername() + "' đã tồn tại trong công ty: " + dto.getCompanyId());
                return null; // Trả về null nếu tên tài khoản đã tồn tại
            }

            // Cập nhật thông tin người dùng
            currentUser.setName(dto.getName());
            currentUser.setUsername(dto.getUsername());
            currentUser.setGender(dto.getGender());
            currentUser.setExpirationDate(dto.getExpirationDate());
            currentUser.setLicenseCategory(dto.getLicenseCategory());
            currentUser.setCccd(dto.getCccd());
            currentUser.setBirthDate(dto.getBirthDate());
            currentUser.setEmail(dto.getEmail());
            currentUser.setRole(dto.getRole());
            currentUser.setStatus(dto.getStatus());
            currentUser.setPhone(dto.getPhone());
            currentUser.setAddress(dto.getAddress());

            System.out.println("Cập nhật người dùng thành công, chuẩn bị lưu.");
            return userRepository.save(currentUser);
        } else {
            System.out.println("Không tìm thấy người dùng với ID: " + id);
            return null;
        }
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
    public void deleteUser(Long id) throws Exception {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new Exception("User not found");
        }
    }
}
