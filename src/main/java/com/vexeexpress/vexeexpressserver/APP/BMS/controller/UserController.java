package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.UserDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.CompanyService;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.UserService;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;

import java.util.List;

@RestController
@RequestMapping("/bms/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;

    @GetMapping("/get-name-user/{id}")
    public ResponseEntity<?> getNameUser(@PathVariable Long id) {
        try {
            String name = userService.getNameUserById(id);
            if (name != null) {
                return ResponseEntity.ok(name);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi truy vấn thông tin người dùng");
        }
    }
    @GetMapping("/getNameUser/{id}")
    public ResponseEntity<String> getUserName(@PathVariable("id") Long userId) {
        try {
            System.out.println("User ID received: " + userId);  // Xác nhận ID nhận được
            String userName = userService.getUserNameById(userId);

            if (userName == null) {
                // Nếu không tìm thấy user, trả về HTTP 404 (Not Found)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy người dùng với ID: " + userId);
            }

            return ResponseEntity.ok(userName);
        } catch (Exception e) {
            // Ghi lỗi chi tiết
            e.printStackTrace();  // In chi tiết lỗi ra console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Có lỗi xảy ra: " + e.getMessage());
        }
    }
    // Dựa theo userId ể trả về companyId
    @GetMapping("/getCompanyIdByUserId/{userId}")
    public ResponseEntity<Long> getCompanyIdByUserId(@PathVariable Long userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request nếu userId không hợp lệ
        }
        Long companyId = userService.getCompanyIdByUserId(userId);
        if (companyId == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found nếu không tìm thấy công ty
        }
        return ResponseEntity.ok(companyId); // 200 OK nếu tìm thấy công ty
    }

    // Tạo nhân viên mới
    @PostMapping("/create-user")
    public ResponseEntity<BmsUser> createUser(@RequestBody UserDTO userDTO) {
        try {
            System.out.println("Dữ liệu user mới: " + userDTO);

            // Check if the company exists
            BmsBusCompany company = companyService.getCompanyById(userDTO.getCompanyId());
            if (company == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 if company not found
            }

            // Check if the username already exists
            if (userService.usernameExists(userDTO.getUsername())) {
                BmsUser errorResponse = new BmsUser();
                errorResponse.setName("Username already exists.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse); // 409 Conflict
            }

            // Hash the password before saving
            String encodedPassword = passwordEncoder().encode(userDTO.getPassword());

            // Create a new BmsUser object and set its fields
            BmsUser bmsUser = new BmsUser();
            bmsUser.setId(userDTO.getId());
            bmsUser.setName(userDTO.getName());
            bmsUser.setEmail(userDTO.getEmail());
            bmsUser.setBirth(userDTO.getBirth());
            bmsUser.setGender(userDTO.getGender());
            bmsUser.setPhone(userDTO.getPhone());
            bmsUser.setRole(userDTO.getRole());
            bmsUser.setPassword(encodedPassword); // Save the hashed password
            bmsUser.setStatus(userDTO.getStatus());
            bmsUser.setUsername(userDTO.getUsername());
            bmsUser.setCompany(company);

            // Save the user using the service
            BmsUser user = userService.createUser(bmsUser);

            // Return the created user with status 201 (Created)
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (IllegalArgumentException e) {
            // Handle specific exceptions (e.g., invalid input)
            BmsUser errorResponse = new BmsUser();
            errorResponse.setName("Invalid input: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            // Handle generic exceptions
            BmsUser errorResponse = new BmsUser();
            errorResponse.setName("Error creating user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    // Hiển thị danh sách nhân viên
    @GetMapping("/get-users/{companyId}")
    public List<BmsUser> getUsersByCompanyId(@PathVariable Long companyId){
        return userService.getUsersByCompanyId(companyId);
    }
    // Xoá nhân viên
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        System.out.println(id);
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    // Example: Update user
    @PostMapping("/update-user/{id}")
    public ResponseEntity<BmsUser> updateUser(@PathVariable Long id, @RequestBody BmsUser updatedUser) {
        try {
            BmsUser user = userService.updateUser(id, updatedUser);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<BmsUser>> getAllUsers(@RequestParam Long companyId) {
        try {
            List<BmsUser> users = userService.getAllUsersByCompanyId(companyId);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/employees")
    public ResponseEntity<List<BmsUser>> getEmployees(@RequestParam Long companyId) {
        try {
            List<BmsUser> employees = userService.getEmployeesByCompanyId(companyId);
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/drivers")
    public ResponseEntity<List<BmsUser>> getDrivers(@RequestParam Long companyId) {
        try {
            List<BmsUser> drivers = userService.getDriversByCompanyId(companyId);
            return ResponseEntity.ok(drivers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
