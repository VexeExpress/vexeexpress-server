package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.BmsTicketDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.utils.Ticket.SeatUpdatePayload;
import com.vexeexpress.vexeexpressserver.APP.BMS.utils.Ticket.TicketRequest_v2;
import com.vexeexpress.vexeexpressserver.entity.BmsTicket;
import com.vexeexpress.vexeexpressserver.entity.BmsTicket_v2;
import com.vexeexpress.vexeexpressserver.entity.BmsUser;
import com.vexeexpress.vexeexpressserver.repository.TicketRepository;
import com.vexeexpress.vexeexpressserver.repository.TicketRepository_v2;
import com.vexeexpress.vexeexpressserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TicketRepository_v2 ticketRepository_v2;
    @Autowired
    UserRepository userRepository;



    public BmsTicket createTicket(BmsTicket ticket) {
        return ticketRepository.save(ticket);
    }

    public void updateTicket(BmsTicket ticket) {
        ticketRepository.save(ticket); // Save sẽ cập nhật nếu vé đã tồn tại
    }
    public boolean cancelTicket(String seatNumber, String tripId) {
        BmsTicket ticket = ticketRepository.findBySeatNumberAndTripId(seatNumber, tripId);
        if (ticket != null) {
            ticketRepository.delete(ticket);
            return true;
        }
        return false;
    }

    public BmsTicket findTicketBySeatNumberAndTripId(String seatNumber, String tripId) {
        return ticketRepository.findBySeatNumberAndTripId(seatNumber, tripId);
    }

    public List<BmsTicket> findTicketsByTripId(String tripId) {
        return ticketRepository.findByTripId(tripId);
    }

    public List<String> bookTickets(TicketRequest_v2 bookingRequest) {
//        List<String> roomCodes = bookingRequest.getSelectedRooms();
//        List<String> existingRooms = roomCodes.stream()
//                .filter(roomCode -> ticketRepository_v2.existsByTripIdAndRoomCode(bookingRequest.getTripId(), roomCode))
//                .collect(Collectors.toList());
//
//        if (!existingRooms.isEmpty()) {
//            // If any room codes already exist, return an error message
//            return existingRooms.stream()
//                    .map(room -> "Room " + room + " already exists")
//                    .collect(Collectors.toList());
//        }
//
//        // Fetch the user associated with userId
//        BmsUser user = userRepository.findById(bookingRequest.getUserId())
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + bookingRequest.getUserId()));
//
//        // Book the tickets if no room codes exist
//        roomCodes.forEach(roomCode -> {
//            BmsTicket_v2 newTicket = new BmsTicket_v2();
//            newTicket.setTripId(bookingRequest.getTripId());
//            newTicket.setRoomCode(roomCode);
//            newTicket.setOfficeName(bookingRequest.getOfficeName());
//            newTicket.setUser(user); // Set the user object
//
//            // Set other ticket properties if necessary
//            ticketRepository_v2.save(newTicket);
//        });
//
//        return roomCodes.stream()
//                .map(room -> "Room " + room + " booked successfully")
//                .collect(Collectors.toList());
        return null;
    }


//    public List<BmsTicket_v2> getBookedRooms(Long tripId) {
//        return ticketRepository_v2.findBookedRoomsByTripId(tripId);
//
//    }







//    public void deleteRoomsByIds(List<Long> roomIds) {
//        System.out.println("Room IDs to delete: " + roomIds);
//        ticketRepository_v2.deleteAllById(roomIds);
//    }


//    public void updateSeats(List<SeatUpdatePayload> payloads) {
////        System.out.println(payloads);
////        for (SeatUpdatePayload payload : payloads) {
////            // Find the existing seat by its ID
////            BmsTicket_v2 seat = ticketRepository_v2.findById(payload.getId())
////                    .orElseThrow(() -> new RuntimeException("Seat not found with id " + payload.getId()));
////
////            // Update the seat with new values from the payload
////            seat.setPhone(payload.getPhone());
////            seat.setPickupPoint(payload.getPickupPoint());
////            seat.setDropoffPoint(payload.getDropoffPoint());
////            seat.setTicketPrice(payload.getTicketPrice());
////            seat.setPaymentMethod(payload.getPaymentMethod());
////            seat.setNote(payload.getNote());
////            seat.setDepartureDate(LocalDate.parse(payload.getDepartureDate()));
////            seat.setDepartureTime(LocalTime.parse(payload.getDepartureTime()));
////            seat.setFullName(payload.getFullName());
////            seat.setTicketCode(payload.getTicketCode());
//////            seat.setAgency(payload.getAgency());
////            seat.setOfficeName(payload.getOfficeName());
////
////            // Fetch the BmsUser entity using the userId from the payload
////            BmsUser user = userRepository.findById(payload.getUserId())
////                    .orElseThrow(() -> new RuntimeException("User not found with id " + payload.getUserId()));
////
////            // Set the BmsUser object in the seat
////            seat.setUser(user);
////
////            // Save the updated seat back to the database
////            ticketRepository_v2.save(seat);
////        }
//
//    }
//
//
//    public BmsTicket_v2 getTicketById(Long ticketId) {
//        return ticketRepository_v2.findById(ticketId).orElse(null);
//    }
//
//    public List<BmsTicket_v2> getTicketsByIds(List<Long> ticketIds) {
//        return ticketRepository_v2.findAllById(ticketIds);
//    }
}
