package com.vexeexpress.vexeexpressserver.entity;

<<<<<<< HEAD
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bms_ticket")
public class BmsTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "phone")
    private String phone;

    @Column(name = "name")
    private String name;

    @Column(name = "boarding_point")
    private String boardingPoint;

    @Column(name = "drop_off_point")
    private String dropOffPoint;

    @Column(name = "notes")
    private String notes;

    @Column(name = "price")
    private String price;

    @Column(name = "trip_id")
    private String tripId;

    // Getters và setters

    // Constructors
    public BmsTicket() {}
    public BmsTicket(String seatNumber, String phone, String name, String boardingPoint, String dropOffPoint, String notes, String price, String tripId) {
        this.seatNumber = seatNumber;
        this.phone = phone;
        this.name = name;
        this.boardingPoint = boardingPoint;
        this.dropOffPoint = dropOffPoint;
        this.notes = notes;
        this.price = price;
        this.tripId = tripId;
    }
     // Getters and setters
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBoardingPoint() {
        return boardingPoint;
    }

    public void setBoardingPoint(String boardingPoint) {
        this.boardingPoint = boardingPoint;
    }

    public String getDropOffPoint() {
        return dropOffPoint;
    }

    public void setDropOffPoint(String dropOffPoint) {
        this.dropOffPoint = dropOffPoint;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }
}
=======
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

    @Column(name = "selected", nullable = false)
    private Boolean selected;



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

    public BmsTicket() {
        this.selected = false;
    }
}

>>>>>>> 5fd1ecb917d5307c412ce92c5dc6a709709352f6
