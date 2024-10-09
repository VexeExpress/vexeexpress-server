package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.OfficeDTO;
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

    // Hiện danh sách văn phòng dựa theo companyId
    @GetMapping("/offices/{companyId}")
    public ResponseEntity<?> getOfficesByCompanyId(@PathVariable Long companyId) {
        try {
            List<OfficeDTO> offices = officeService.getOfficesByCompanyId(companyId);

            if (offices.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(offices);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi máy chủ nội bộ");
        }
    }

    // Tạo văn phòng mới
    @PostMapping("/create-office")
    public ResponseEntity<BmsOffice> createOffice(@RequestBody BmsOffice bmsOffice) {
        // Kiểm tra nếu đối tượng bmsOffice là null
        System.out.println(bmsOffice);
        if (bmsOffice == null) {
            return ResponseEntity.badRequest().body(null); // 400 Bad Request
        }

        // Kiểm tra các trường bắt buộc
        if (bmsOffice.getName() == null || bmsOffice.getName().trim().isEmpty() ||
                bmsOffice.getPhone() == null || bmsOffice.getPhone().trim().isEmpty() ||
                bmsOffice.getCode() == null || bmsOffice.getCode().trim().isEmpty() ||
                bmsOffice.getAddress() == null || bmsOffice.getAddress().trim().isEmpty() ||
                bmsOffice.getCompany() == null || bmsOffice.getCompany().getId() == null) {
            return ResponseEntity.badRequest().body(null); // 400 Bad Request
        }

        // Thiết lập công ty dựa trên companyId
        BmsBusCompany company = companyService.getCompanyById(bmsOffice.getCompany().getId());
        if (company == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found nếu công ty không tồn tại
        }
        bmsOffice.setCompany(company);

        // Kiểm tra nếu văn phòng đã tồn tại trong cùng một công ty
        if (officeService.doesOfficeExist(bmsOffice.getName(), bmsOffice.getCompany().getId())) {
            System.out.println("Văn phòng đã tồn tại trong công ty này");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // 409 Conflict
        }

        // Tạo văn phòng mới
        try {
            BmsOffice createdOffice = officeService.createOffice(bmsOffice);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOffice); // 201 Created
        } catch (Exception e) {
            // Xử lý lỗi khi tạo văn phòng
            System.err.println("Lỗi khi tạo văn phòng: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
        }
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOfficeById(@PathVariable Long id) {
        // Gọi service để thực hiện xóa và kiểm tra kết quả
        boolean isDeleted = officeService.deleteOfficeById(id);

        if (isDeleted) {
            // Trả về mã trạng thái 204 No Content nếu xóa thành công
            return ResponseEntity.noContent().build();
        } else {
            // Trả về mã trạng thái 404 Not Found nếu không tìm thấy văn phòng để xóa
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateOffice(@PathVariable Long id, @RequestBody BmsOffice updatedOffice) {
        boolean isUpdated = officeService.updateOffice(id, updatedOffice);
        if (isUpdated) {
            return ResponseEntity.noContent().build();  // 204 No Content
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }



}