package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "bms_bus")
@Data
public class BmsBus {
    // Định danh duy nhất cho mỗi xe buýt
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Số hiệu xe buýt dùng để nhận diện
    @Column(name = "bus_number", length = 20, nullable = false)
    private String busNumber;

    // Biển số xe của xe buýt
    @Column(name = "license_plate", length = 20, nullable = false)
    private String licensePlate;

    // Sức chứa tối đa của xe buýt (số ghế)
    @Column(name = "capacity", nullable = false)
    private int capacity;

    // Trạng thái hoạt động của xe buýt (ví dụ: 'A' cho hoạt động, 'I' cho không hoạt động)
    @Column(name = "status", length = 1, nullable = false)
    private String status;

    // ID của công ty sở hữu xe buýt
    @Column(name = "company_id", nullable = false)
    private Long companyId;

    // Ngày tạo bản ghi xe buýt
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    // Constructor mặc định cần thiết cho JPA
    public BmsBus() {
      
    }
}
