package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "bms_office")
@Data
public class BmsOffice {
    // Định danh duy nhất cho mỗi văn phòng
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Tên của văn phòng
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    // Số điện thoại của văn phòng
    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    // Mã của văn phòng
    @Column(name = "code", length = 20, nullable = false)
    private String code;

    // Địa chỉ của văn phòng
    @Column(name = "address", length = 255, nullable = false)
    private String address;

    // Ghi chú về văn phòng
    @Column(name = "note", length = 255)
    private String note;

    // ID của công ty sở hữu văn phòng
    @Column(name = "company_id", nullable = false)
    private String companyId;
}
