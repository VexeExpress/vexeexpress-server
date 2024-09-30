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
import java.util.Optional;

@RestController
@RequestMapping("/bms/user")
@CrossOrigin(origins = "http://localhost:3000")
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
        System.out.println("ID: " + userId);
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
    public ResponseEntity<BmsUser> createUser(@RequestBody BmsUser bmsUser) {
        try {
            System.out.println("Dữ liệu user mới: " + bmsUser);

            // Check if the company exists
            BmsBusCompany company = companyService.getCompanyById(bmsUser.getCompany().getId());
            if (company == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 if company not found
            }
            bmsUser.setCompany(company);

            // Check if the username already exists
            if (userService.usernameExists(bmsUser.getUsername())) {
                BmsUser errorResponse = new BmsUser();
                errorResponse.setName("Username already exists.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse); // 409 Conflict
            }

            // Hash the password before saving
            String encodedPassword = passwordEncoder().encode(bmsUser.getPassword());


            // Save the user using the service
            BmsUser user = userService.createUser(bmsUser);

            // Return the created user with status 201 (Created)
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }  catch (Exception e) {
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
    public List<UserDTO> getUsersByCompanyId(@PathVariable Long companyId){
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

    @PutMapping("/update-user/{id}")
    public ResponseEntity<BmsUser> updateUser(@PathVariable Long id, @RequestBody BmsUser updatedUser) {
        try {
            // Check if the username already exists for another user
            Optional<BmsUser> existingUser = userService.findByUsername(updatedUser.getUsername());

            if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
                // Return conflict if the username already exists for another user
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }

            // Proceed with updating the user
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
    @PostMapping("/lock-user/{id}")
    public ResponseEntity<BmsUser> lockUser(@PathVariable Long id) {
        try {
            BmsUser lockedUser = userService.lockUser(id);
            return new ResponseEntity<>(lockedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  // Return 404 if the user is not found
        }
    }
    @PostMapping("/change-pass-user/{id}")
    public ResponseEntity<String> changePassword(@PathVariable Long id) {
        try {
            userService.changePassword(id, "12345678");
            return ResponseEntity.ok("Password changed successfully to 12345678");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error changing password");
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
