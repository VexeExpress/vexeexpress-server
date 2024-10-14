package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Office.OfficeDTO_v2;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Office.OfficeDTO_v3;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Office.OfficeDTO_v4;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.CompanyService;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.OfficeService;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsOffice;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bms/office")
@CrossOrigin(origins = "http://localhost:3000")
public class OfficeController {
    @Autowired
    OfficeService officeService;
    @Autowired
    CompanyService companyService;

    @GetMapping("/offices/{companyId}")
    public ResponseEntity<?> getOfficesByCompanyId(@PathVariable Long companyId) {
        try {
            List<OfficeDTO_v2> offices = officeService.getOfficesByCompanyId(companyId);

            if (offices == null || offices.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
            }
            return ResponseEntity.ok(offices); // 200 OK
        } catch (IllegalArgumentException e) {
            // Xử lý khi companyId không hợp lệ
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request
        } catch (EntityNotFoundException e) {
            // CompanyId null
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }
    @GetMapping("/offices-details/{companyId}")
    public ResponseEntity<?> getListOfficeDetailByCompanyId(@PathVariable Long companyId) {
        try {
            List<OfficeDTO_v3> offices = officeService.getListOfficeDetailByCompanyId(companyId);

            if (offices == null || offices.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
            }
            return ResponseEntity.ok(offices); // 200 OK
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOfficeById(@PathVariable Long id) {
        try {
            boolean isDeleted = officeService.deleteOfficeById(id);

            if (isDeleted) {
                // Trả về mã trạng thái 204 No Content nếu xóa thành công
                return ResponseEntity.noContent().build();
            } else {
                // Trả về mã trạng thái 404 Not Found nếu không tìm thấy văn phòng để xóa
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> createOffice_2(@RequestBody OfficeDTO_v4 dto) {
        try {
            BmsOffice createdOffice = officeService.createOffice_v2(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOffice);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOffice(@PathVariable Long id, @RequestBody OfficeDTO_v3 dto) {
        try {
            BmsOffice updatedOffice = officeService.updateOffice_v2(id, dto);
            if (updatedOffice != null) {
                return ResponseEntity.ok(updatedOffice);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    // Tạo văn phòng mới
//    @PostMapping("/create-office")
//    public ResponseEntity<BmsOffice> createOffice(@RequestBody BmsOffice bmsOffice) {
//        // Kiểm tra nếu đối tượng bmsOffice là null
//        System.out.println(bmsOffice);
//        if (bmsOffice == null) {
//            return ResponseEntity.badRequest().body(null); // 400 Bad Request
//        }
//
//        // Kiểm tra các trường bắt buộc
//        if (bmsOffice.getName() == null || bmsOffice.getName().trim().isEmpty() ||
//                bmsOffice.getPhone() == null || bmsOffice.getPhone().trim().isEmpty() ||
//                bmsOffice.getCode() == null || bmsOffice.getCode().trim().isEmpty() ||
//                bmsOffice.getAddress() == null || bmsOffice.getAddress().trim().isEmpty() ||
//                bmsOffice.getCompany() == null || bmsOffice.getCompany().getId() == null) {
//            return ResponseEntity.badRequest().body(null); // 400 Bad Request
//        }
//
//        // Thiết lập công ty dựa trên companyId
//        BmsBusCompany company = companyService.getCompanyById(bmsOffice.getCompany().getId());
//        if (company == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found nếu công ty không tồn tại
//        }
//        bmsOffice.setCompany(company);
//
//        // Kiểm tra nếu văn phòng đã tồn tại trong cùng một công ty
//        if (officeService.doesOfficeExist(bmsOffice.getName(), bmsOffice.getCompany().getId())) {
//            System.out.println("Văn phòng đã tồn tại trong công ty này");
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // 409 Conflict
//        }
//
//        // Tạo văn phòng mới
//        try {
//            BmsOffice createdOffice = officeService.createOffice(bmsOffice);
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdOffice); // 201 Created
//        } catch (Exception e) {
//            // Xử lý lỗi khi tạo văn phòng
//            System.err.println("Lỗi khi tạo văn phòng: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
//        }
//    }




//    @PutMapping("/update/{id}")
//    public ResponseEntity<Void> updateOffice(@PathVariable Long id, @RequestBody BmsOffice updatedOffice) {
//        boolean isUpdated = officeService.updateOffice(id, updatedOffice);
//        if (isUpdated) {
//            return ResponseEntity.noContent().build();  // 204 No Content
//        } else {
//            return ResponseEntity.notFound().build();  // 404 Not Found
//        }
//    }



}