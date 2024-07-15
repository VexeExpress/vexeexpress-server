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

    // Fetch all users by companyId
    @GetMapping("/get-users/{companyId}")
    public List<BmsUser> getUsersByCompanyId(@PathVariable Long companyId){
        return bmsUserService.getUsersByCompanyId(companyId);
    }

    // Fetch employees by companyId
    @GetMapping("/get-employees/{companyId}")
    public List<BmsUser> getEmployeesByCompanyId(@PathVariable Long companyId){
        return bmsUserService.getEmployeesByCompanyId(companyId);
    }

    // Fetch drivers by companyId
    @GetMapping("/get-drivers/{companyId}")
    public List<BmsUser> getDriversByCompanyId(@PathVariable Long companyId){
        return bmsUserService.getDriversByCompanyId(companyId);
    }

    // Other endpoints for your controller (create, update, delete, etc.)...
    
    // Example: Create user
    @PostMapping("/create-user")
    public BmsUser createUser(@RequestBody BmsUser bmsUser) {
        return bmsUserService.createUser(bmsUser);
    }

    // Example: Update user
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

    // Example: Delete user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            bmsUserService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
