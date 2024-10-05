package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bms_ticket")
@Data
public class BmsTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "room_code")
    private String roomCode;

//    @Column(name = "phone")
//    private String phone;
//
//    @Column(name = "pickup_point")
//    private String pickupPoint;
//
//    @Column(name = "dropoff_point")
//    private String dropoffPoint;
//
//    @Column(name = "ticket_price")
//    private Double ticketPrice;
//
//    @Column(name = "payment_method")
//    private Integer paymentMethod;
//
//    @Column(name = "note")
//    private String note;
//
//    @Column(name = "departure_date")
//    private LocalDate departureDate;
//
//    @Column(name = "departure_time")
//    private LocalTime departureTime;
//
//    @Column(name = "full_name")
//    private String fullName;
//
//    @Column(name = "ticket_code")
//    private String ticketCode;
//
//    @Column(name = "ticket_status")
//    private Integer ticketStatus;

    @ManyToOne
    @JoinColumn(name = "agency_id", referencedColumnName = "id")
    private BmsAgent agency;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private BmsUser user;

    @ManyToOne
    @JoinColumn(name = "office_id", referencedColumnName = "id")
    private BmsOffice office;

    @ManyToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private BmsSeat seat;

    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private BmsTrip trip;
}

