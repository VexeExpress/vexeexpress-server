package com.vexeexpress.vexeexpressserver.APP.ADMIN.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import com.vexeexpress.vexeexpressserver.APP.BMS.repository.BmsBusCompanyRepository;
import com.vexeexpress.vexeexpressserver.APP.BMS.repository.BmsUserRepository;
import com.vexeexpress.vexeexpressserver.APP.ADMIN.service.BmsBusCompanyService;
import com.vexeexpress.vexeexpressserver.APP.ADMIN.service.BmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bms/user")
@CrossOrigin(origins = "http://localhost:3000")
public class BmsUserController {
    @Autowired
    private BmsUserService bmsUserService;
    @Autowired
    private BmsBusCompanyService bmsBusCompanyService;
    @Autowired
    private BmsUserRepository bmsUserRepository;
    @Autowired
    private BmsBusCompanyRepository bmsBusCompanyRepository;


    @PostMapping("/add-user")
    public String addUser(@RequestBody Object requestBody){
        System.out.println("Dữ liệu đầu vào từ client: " + requestBody.toString());
        BmsUser bmsUser = extractUserFromRequestBody(requestBody);
        BmsBusCompany bmsBusCompany= extractBusCompanyFromRequestBody(requestBody);
        boolean initializeCompany = extractInitializeCompanyFromRequestBody(requestBody);
        System.out.println("User: " + bmsUser);
        System.out.println("Company: " + bmsBusCompany);
        System.out.println("initialize: " + initializeCompany);
        if (initializeCompany) {
            // Kiểm tra sự tồn tại của nhà xe
            BmsBusCompany existingCompany = bmsBusCompanyRepository.findByCompanyName(bmsBusCompany.getCompanyName());
            if(existingCompany != null){
                return "Nhà xe đã tồn tại";
            }
            // Kiểm tra sự tồn tại của người dùng
            BmsUser existingUser = bmsUserRepository.findByUsername(bmsUser.getUsername());
            if(existingUser != null){
                return "Tài khoản đã tồn tại";
            }
            // Thêm người dùng và nhà xe
            String userId = bmsUserService.addUser(bmsUser);
            System.out.println(userId);
            if(userId != null){
                bmsBusCompany.setIdUser(userId);
                return bmsBusCompanyService.addBusCompany(bmsBusCompany);
            } else {
                return "Tạo tài khoản thất bại";
            }
        } else {
            // Không khởi tạo nhà xe, chỉ thêm người dùng
            return bmsUserService.addUser(bmsUser);
        }
    }
    @GetMapping("/get-users")
    public List<Map<String, String>> getUsers(BmsUser bmsUser){
        return bmsUserService.getListUser(bmsUser);
    }
    private BmsUser extractUserFromRequestBody(@RequestBody Object requestBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> requestBodyMap = objectMapper.convertValue(requestBody, Map.class);
        BmsUser bmsUser = new BmsUser();
        bmsUser.setOwnerName((String) requestBodyMap.get("ownerName"));
        bmsUser.setPhoneNumber((String) requestBodyMap.get("phoneNumber"));
        bmsUser.setUsername((String) requestBodyMap.get("username"));
        bmsUser.setPassword((String) requestBodyMap.get("password"));
        bmsUser.setAddress((String) requestBodyMap.get("address"));
        bmsUser.setActivateAccount((Boolean) requestBodyMap.get("activateAccount"));
        return bmsUser;
    }
    private BmsBusCompany extractBusCompanyFromRequestBody(Object requestBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> requestBodyMap = objectMapper.convertValue(requestBody, Map.class);
        BmsBusCompany bmsBusCompany = new BmsBusCompany();
        bmsBusCompany.setCompanyName((String) requestBodyMap.get("companyName"));
        return bmsBusCompany;
    }
    private boolean extractInitializeCompanyFromRequestBody(Object requestBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> requestBodyMap = objectMapper.convertValue(requestBody, Map.class);
        Object initializeCompanyValue = requestBodyMap.get("initializeCompany");
        if (initializeCompanyValue instanceof Boolean) {
            return (Boolean) initializeCompanyValue;
        } else {
            return false;
        }
    }


}
