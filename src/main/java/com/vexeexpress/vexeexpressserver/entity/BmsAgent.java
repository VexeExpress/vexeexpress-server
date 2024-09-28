package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "bms_agent")
@Data
public class BmsAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name",length = 50, nullable = false)
    private String name;

    @Column(name = "phone", length = 10, nullable = false)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private BmsBusCompany company;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
