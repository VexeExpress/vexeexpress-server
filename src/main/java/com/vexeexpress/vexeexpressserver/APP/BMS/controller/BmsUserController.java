package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.BmsUserService;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;

import java.util.List;

@RestController
@RequestMapping("/bms/user")
@CrossOrigin(origins = "http://localhost:5173")
public class BmsUserController {
    @Autowired
    BmsUserService bmsUserService;

    @GetMapping("/get-name-user/{id}")
    public ResponseEntity<?> getNameUser(@PathVariable Long id) {
        try {
            String name = bmsUserService.getNameUserById(id);
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
    // Tạo nhân viên mới
    @PostMapping("/create-user")
    public BmsUser createUser(@RequestBody BmsUser bmsUser) {
        System.out.println(bmsUser);
        return bmsUserService.createUser(bmsUser);
    }
    // Hiển thị danh sách nhân viên
    @GetMapping("/get-users/{companyId}")
    public List<BmsUser> getUsersByCompanyId(@PathVariable Long companyId){
        return bmsUserService.getUsersByCompanyId(companyId);
    }
    // Xoá nhân viên
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        System.out.println(id);
        try {
            bmsUserService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

      // Cập nhật thông tin nhân viên
      @PostMapping("/update-user/{id}")
      public ResponseEntity<BmsUser> updateUser(@PathVariable Long id, @RequestBody BmsUser updatedUser) {
          try {
              BmsUser user = bmsUserService.updateUser(id, updatedUser);
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
}
