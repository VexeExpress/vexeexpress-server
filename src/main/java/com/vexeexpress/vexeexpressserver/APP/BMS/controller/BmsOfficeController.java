package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.BmsOfficeService;
import com.vexeexpress.vexeexpressserver.entity.BmsOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bms/office")
@CrossOrigin(origins = "http://localhost:5173")
public class BmsOfficeController {
    @Autowired
    BmsOfficeService bmsOfficeService;

    // Hiện danh sách văn phòng dựa theo companyId
    @GetMapping("/offices/{companyId}")
    public ResponseEntity<List<BmsOffice>> getOfficesByCompanyId(@PathVariable String companyId) {
        if (companyId == null || companyId.trim().isEmpty()) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        try {
            List<BmsOffice> offices = bmsOfficeService.getOfficesByCompanyId(companyId);

            if (offices.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 No Content
            }
            return ResponseEntity.ok(offices); // 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }
    // Tạo văn phòng mới
    @PostMapping("/create-office")
    public ResponseEntity<BmsOffice> createOffice(@RequestBody BmsOffice bmsOffice) {
        if (bmsOffice == null || bmsOffice.getName() == null || bmsOffice.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }

        // Kiểm tra nếu văn phòng đã tồn tại
        if (bmsOfficeService.doesOfficeExist(bmsOffice.getName())) {
            System.out.println("Văn phòng đã tồn tại");
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict
        }

        BmsOffice createdOffice = bmsOfficeService.createOffice(bmsOffice);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOffice); // 201 Created
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOfficeById(@PathVariable Long id) {
        // Gọi service để thực hiện xóa và kiểm tra kết quả
        boolean isDeleted = bmsOfficeService.deleteOfficeById(id);

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
        boolean isUpdated = bmsOfficeService.updateOffice(id, updatedOffice);
        if (isUpdated) {
            return ResponseEntity.noContent().build();  // 204 No Content
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }



}