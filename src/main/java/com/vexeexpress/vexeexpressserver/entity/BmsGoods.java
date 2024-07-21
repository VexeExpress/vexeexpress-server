package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "bms_goods")
@Data
public class BmsGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /// id chuyến
    @Column(name = "tripId")
    private Long tripId;

    @Column(name = "userId")
    private Long userId;
    
    /// Số điện thoại người gửi
    @Column(name = "senderPhone")
    private String senderPhone;
    /// Họ tên người gửi
    @Column(name = "senderName")
    private String senderName;
    /// Hình thức gửi hàng
    @Column(name = "shippingMethod")
    private String shippingMethod;
    /// Điểm lên
    @Column(name = "boardingPoint")
    private String boardingPoint;
    /// Số điện thoại người nhận
    @Column(name = "receiverPhone")
    private String receiverPhone;
    /// Họ tên người nhận
    @Column(name = "receiverName")
    private String receiverName;
    /// Hình thức trả hàng
    @Column(name = "returnMethod")
    private String returnMethod;
    /// Điểm trả
    @Column(name = "droppingPoint")
    private String droppingPoint;

    /// Tên hàng
    @Column(name = "goodsName")
    private String goodsName;
    /// Cước phí
    @Column(name = "fee")
    private Double fee;
    /// Đã trả
    @Column(name = "paidAmount")
    private Double paidAmount;
    /// Tổng tiền
    @Column(name = "totalAmount")
    private Double totalAmount;
    /// Ghi chú
    @Column(name = "note")
    private String note;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
