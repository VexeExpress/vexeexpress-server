package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

<<<<<<< HEAD

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.TicketService;
import com.vexeexpress.vexeexpressserver.entity.BmsTicket;
import com.vexeexpress.vexeexpressserver.APP.BMS.utils.TicketRequest;

@RestController
@RequestMapping("/bms/ticket")
@CrossOrigin(origins = "http://localhost:5173")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/create-ticket")
    public ResponseEntity<String> createTicket(@RequestBody TicketRequest ticketRequest) {
        BmsTicket ticket = new BmsTicket(
            ticketRequest.getSeatNumber(),
            ticketRequest.getPhone(),
            ticketRequest.getName(),
            ticketRequest.getBoardingPoint(),
            ticketRequest.getDropOffPoint(),
            ticketRequest.getNotes(),
            ticketRequest.getPrice(),
            ticketRequest.getTripId()
        );
        ticketService.createTicket(ticket);
        return ResponseEntity.ok("Ticket created successfully");
    }

    @PutMapping("/update-ticket")
    public ResponseEntity<String> updateTicket(@RequestBody TicketRequest ticketRequest) {
        BmsTicket existingTicket = ticketService.findTicketBySeatNumberAndTripId(
            ticketRequest.getSeatNumber(), ticketRequest.getTripId()
        ); 
        if (existingTicket != null) {
            existingTicket.setPhone(ticketRequest.getPhone());
            existingTicket.setName(ticketRequest.getName());
            existingTicket.setBoardingPoint(ticketRequest.getBoardingPoint());
            existingTicket.setDropOffPoint(ticketRequest.getDropOffPoint());
            existingTicket.setNotes(ticketRequest.getNotes());
            existingTicket.setPrice(ticketRequest.getPrice());
            ticketService.updateTicket(existingTicket);
            return ResponseEntity.ok("Ticket updated successfully");
        } else {
            return ResponseEntity.status(404).body("Ticket not found");
        }
    }

    @DeleteMapping("/cancel-ticket")
    public ResponseEntity<String> cancelTicket(@RequestParam String seatNumber, @RequestParam String tripId) {
        boolean isCancelled = ticketService.cancelTicket(seatNumber, tripId);
        if (isCancelled) {
            return ResponseEntity.ok("Ticket canceled successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket not found");
        }
    }

    @GetMapping("/get-seats")
    public ResponseEntity<List<BmsTicket>> getSeats(@RequestParam String tripId) {
        List<BmsTicket> tickets = ticketService.findTicketsByTripId(tripId);
        return ResponseEntity.ok(tickets);
    }
=======
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Ticket.TicketDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bms/ticket")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketController {
    @Autowired
    private TicketService ticketService;




    @GetMapping("/get-ticket-data/{tripId}")
    public ResponseEntity<?> getTicketData(@PathVariable Long tripId) {
        try {
            System.out.println("Get Data Trip ID: " + tripId);
            List<TicketDTO> ticketDTOs = ticketService.getTicketsByTripId(tripId);

            if (ticketDTOs.isEmpty()) {
                return ResponseEntity.status(404).body("No tickets found for Trip ID: " + tripId);
            }

            return ResponseEntity.ok(ticketDTOs);
        } catch (Exception e) {
            System.err.println("Error retrieving ticket data: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to retrieve ticket data");
        }
    }




//    @PostMapping("/booking")
//    public ResponseEntity<?> bookTickets(@RequestBody TicketDTO_v1 bookingRequest) {
//        System.out.println("Booking new: " + bookingRequest);
//        List<String> bookingResults = ticketService.bookTickets(bookingRequest);
//
//        boolean hasErrors = bookingResults.stream().anyMatch(result -> result.contains("already exists"));
//
//        if (hasErrors) {
//            // Nếu có bất kỳ mã phòng nào đã tồn tại, trả về với status 409 (Conflict)
//            System.out.println("Vé đã tồn tại");
//            return ResponseEntity.status(409).body(bookingResults);
//        } else {
//            // Tất cả các phòng được đặt thành công, trả về với status 200 (OK)
//            System.out.println("Đặt thành công");
//            return ResponseEntity.ok(bookingResults);
//        }
//    }
//    @GetMapping("/booked-rooms/{tripId}")
//    public ResponseEntity<List<TicketDTO_v1>> getBookedRooms(@PathVariable Long tripId) {
//        // Lấy danh sách các phòng đã đặt dưới dạng BmsTicketDTO từ service
//        List<TicketDTO_v1> bookedRoomsDTO = ticketService.getBookedRooms(tripId);
//
//        // Kiểm tra nếu danh sách rỗng thì trả về mã 204 No Content
//        if (bookedRoomsDTO.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//
//        // Trả về danh sách phòng đã đặt với mã 200 OK
//        return ResponseEntity.ok(bookedRoomsDTO);
//    }
//
//    @PostMapping("/delete-rooms")
//    public ResponseEntity<String> deleteRooms(@RequestBody DeleteRoomsRequest request) {
//        try {
//            List<Long> roomIds = request.getRoomIds();
//            ticketService.deleteRoomsByIds(roomIds);
//            return ResponseEntity.ok("Rooms deleted successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Failed to delete rooms");
//        }
//    }
//    @PutMapping("/update")
//    public ResponseEntity<?> updateSeats(@RequestBody List<TicketDTO_v1> payloads) {
//        try {
//            System.out.println(payloads);
//            ticketService.updateSeats(payloads);
//            return ResponseEntity.ok("Seats updated successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Failed to update seats");
//        }
//    }
////    @GetMapping("/getTicketsByIds")
////    public ResponseEntity<List<BmsTicket_v2>> getTicketsByIds(@RequestParam List<Long> ticketIds) {
////        try {
////            List<BmsTicket_v2> tickets = ticketService.getTicketsByIds(ticketIds);
////            if (tickets != null && !tickets.isEmpty()) {
////                return ResponseEntity.ok(tickets);
////            } else {
////                return ResponseEntity.status(404).body(null);
////            }
////        } catch (Exception e) {
////            return ResponseEntity.status(500).body(null);
////        }
////    }
//
//
//    @PostMapping("/create-ticket")
//    public ResponseEntity<String> createTicket(@RequestBody TicketRequest ticketRequest) {
//        BmsTicket ticket = new BmsTicket(
//                ticketRequest.getSeatNumber(),
//                ticketRequest.getPhone(),
//                ticketRequest.getName(),
//                ticketRequest.getBoardingPoint(),
//                ticketRequest.getDropOffPoint(),
//                ticketRequest.getNotes(),
//                ticketRequest.getPrice(),
//                ticketRequest.getTripId()
//        );
//        ticketService.createTicket(ticket);
//        return ResponseEntity.ok("Ticket created successfully");
//    }
//    @PutMapping("/update-ticket")
//    public ResponseEntity<String> updateTicket(@RequestBody TicketRequest ticketRequest) {
//        BmsTicket existingTicket = ticketService.findTicketBySeatNumberAndTripId(
//                ticketRequest.getSeatNumber(), ticketRequest.getTripId()
//        );
//        if (existingTicket != null) {
//            existingTicket.setPhone(ticketRequest.getPhone());
//            existingTicket.setName(ticketRequest.getName());
//            existingTicket.setBoardingPoint(ticketRequest.getBoardingPoint());
//            existingTicket.setDropOffPoint(ticketRequest.getDropOffPoint());
//            existingTicket.setNotes(ticketRequest.getNotes());
//            existingTicket.setPrice(ticketRequest.getPrice());
//            ticketService.updateTicket(existingTicket);
//            return ResponseEntity.ok("Ticket updated successfully");
//        } else {
//            return ResponseEntity.status(404).body("Ticket not found");
//        }
//    }
//
//    @DeleteMapping("/cancel-ticket")
//    public ResponseEntity<String> cancelTicket(@RequestParam String seatNumber, @RequestParam String tripId) {
//        boolean isCancelled = ticketService.cancelTicket(seatNumber, tripId);
//        if (isCancelled) {
//            return ResponseEntity.ok("Ticket canceled successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket not found");
//        }
//    }
//
//    @GetMapping("/get-seats")
//    public ResponseEntity<List<BmsTicket>> getSeats(@RequestParam String tripId) {
//        List<BmsTicket> tickets = ticketService.findTicketsByTripId(tripId);
//        return ResponseEntity.ok(tickets);
//    }
>>>>>>> 5fd1ecb917d5307c412ce92c5dc6a709709352f6
}
