package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Ticket.TicketDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Ticket.TicketDTO_v2;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Ticket.TicketDTO_v3;
import com.vexeexpress.vexeexpressserver.entity.*;
import com.vexeexpress.vexeexpressserver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TripRepository tripRepository;
    @Autowired
    OfficeRepository officeRepository;
    @Autowired
    AgentRepository agentRepository;


    public List<TicketDTO> getTicketsByTripId(Long tripId) {
        List<BmsTicket> tickets = ticketRepository.findByTripId(tripId);
        return tickets.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private TicketDTO convertToDTO(BmsTicket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());

        if (ticket.getSeat() != null) {
            TicketDTO_v2 dto_v2 = new TicketDTO_v2();
            dto_v2.setId(ticket.getSeat().getId());
            dto_v2.setFloor(ticket.getSeat().getFloor());
            dto_v2.setRow(ticket.getSeat().getRow());
            dto_v2.setSeatColumn(ticket.getSeat().getSeatColumn());
            dto_v2.setName(ticket.getSeat().getName());
            dto_v2.setStatus(ticket.getSeat().getStatus());

            if(ticket.getSeat().getBmsSeatMap() != null) {
                TicketDTO_v3 dto_v3 = new TicketDTO_v3();
                dto_v3.setId(ticket.getSeat().getBmsSeatMap().getId());
                dto_v3.setSeatMapName(ticket.getSeat().getBmsSeatMap().getSeatMapName());
                dto_v3.setFloor(ticket.getSeat().getBmsSeatMap().getFloor());
                dto_v3.setRow(ticket.getSeat().getBmsSeatMap().getRow());
                dto_v3.setSeatColumn(ticket.getSeat().getBmsSeatMap().getSeatColumn());

                dto_v2.setSeatMap(dto_v3);

            }
            ticketDTO.setSeat(dto_v2);
        }
        return ticketDTO;
    }

    public void updateTicketSelection(Long ticketId, boolean selected) {
        BmsTicket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found with id: " + ticketId));
        ticket.setSelected(selected);
        ticketRepository.save(ticket);
    }


//    public BmsTicket createTicket(BmsTicket ticket) {
//        return ticketRepository.save(ticket);
//    }
//
//    public void updateTicket(BmsTicket ticket) {
//        ticketRepository.save(ticket); // Save sẽ cập nhật nếu vé đã tồn tại
//    }
//    public boolean cancelTicket(String seatNumber, String tripId) {
//        BmsTicket ticket = ticketRepository.findBySeatNumberAndTripId(seatNumber, tripId);
//        if (ticket != null) {
//            ticketRepository.delete(ticket);
//            return true;
//        }
//        return false;
//    }
//
//    public BmsTicket findTicketBySeatNumberAndTripId(String seatNumber, String tripId) {
//        return ticketRepository.findBySeatNumberAndTripId(seatNumber, tripId);
//    }
//
//    public List<BmsTicket> findTicketsByTripId(String tripId) {
//        return ticketRepository.findByTripId(tripId);
//    }
//
////    public List<String> bookTickets(TicketDTO_v1 bookingRequest) {
////        List<String> bookingResults = new ArrayList<>();
////        // Fetch related entities (trip, user, office) based on IDs
////        BmsTrip trip = tripRepository.findById(bookingRequest.getTripId())
////                .orElseThrow(() -> new RuntimeException("Trip not found with id: " + bookingRequest.getTripId()));
////        BmsUser user = userRepository.findById(bookingRequest.getUserId())
////                .orElseThrow(() -> new RuntimeException("User not found with id: " + bookingRequest.getUserId()));
////        BmsOffice office = officeRepository.findById(bookingRequest.getOfficeId())
////                .orElseThrow(() -> new RuntimeException("Office not found with id: " + bookingRequest.getOfficeId()));
////
////        // Iterate through each selected room code
////        for (String roomCode : bookingRequest.getSelectedRooms()) {
////            boolean isAlreadyBooked = ticketRepository_v2.existsByTripAndRoomCode(trip, roomCode);
////
////            if (isAlreadyBooked) {
////                bookingResults.add("Room " + roomCode + " already exists for trip " + bookingRequest.getTripId());
////            } else {
////                // Create a new BmsTicket_v2 entity for the available room
////                BmsTicket_v2 newTicket = new BmsTicket_v2();
////                newTicket.setTrip(trip);
////                newTicket.setRoomCode(roomCode);
////                newTicket.setUser(user);
////                newTicket.setOffice(office);
////
////
////
////                // Save the new ticket
////                ticketRepository_v2.save(newTicket);
////                bookingResults.add("Room " + roomCode + " booked successfully.");
////            }
////        }
////        return bookingResults;
////    }
//    public List<String> bookTickets(TicketDTO_v1 bookingRequest) {
//        List<String> bookingResults = new ArrayList<>();
//        List<String> unavailableRooms = new ArrayList<>();
//
//        // Lấy thông tin liên quan (chuyến đi, người dùng, văn phòng) dựa trên ID
//        BmsTrip trip = tripRepository.findById(bookingRequest.getTripId())
//                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyến đi với ID: " + bookingRequest.getTripId()));
//        BmsUser user = userRepository.findById(bookingRequest.getUserId())
//                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + bookingRequest.getUserId()));
//        BmsOffice office = officeRepository.findById(bookingRequest.getOfficeId())
//                .orElseThrow(() -> new RuntimeException("Không tìm thấy văn phòng với ID: " + bookingRequest.getOfficeId()));
//
//        // Kiểm tra từng mã phòng đã chọn
//        for (String roomCode : bookingRequest.getSelectedRooms()) {
//            boolean isAlreadyBooked = ticketRepository_v2.existsByTripAndRoomCode(trip, roomCode);
//
//            if (isAlreadyBooked) {
//                unavailableRooms.add(roomCode); // Thêm phòng không khả dụng vào danh sách
//            }
//        }
//
//        // Nếu có phòng không khả dụng, trả về thông báo lỗi
//        if (!unavailableRooms.isEmpty()) {
//            throw new RuntimeException("Ghế " + String.join(", ", unavailableRooms) + " hiện không khả dụng");
//        }
//
//        // Nếu tất cả các ghế đều khả dụng, tiến hành đặt vé
//        for (String roomCode : bookingRequest.getSelectedRooms()) {
//            // Tạo một vé mới cho từng ghế khả dụng
//            BmsTicket_v2 newTicket = new BmsTicket_v2();
//            newTicket.setTrip(trip);
//            newTicket.setRoomCode(roomCode);
//            newTicket.setUser(user);
//            newTicket.setOffice(office);
//
//            // Lưu vé mới
//            ticketRepository_v2.save(newTicket);
//            bookingResults.add("Đặt ghế " + roomCode + " thành công.");
//        }
//
//        return bookingResults;
//    }
//
//
//
//    public List<TicketDTO_v1> getBookedRooms(Long tripId) {
//        // Fetch the list of BmsTicket_v2 entities from the repository
//        List<BmsTicket_v2> bookedRooms = ticketRepository_v2.findBookedRoomsByTripId(tripId);
//
//        // Convert the BmsTicket_v2 objects into TicketDTO_v1 objects
//        return bookedRooms.stream().map(room -> {
//            // Create a new TicketDTO_v1 and map fields from BmsTicket_v2
//            TicketDTO_v1 dto = new TicketDTO_v1();
//            dto.setRoomCode(room.getRoomCode());
//            dto.setFullName(room.getFullName());  // Avoid null pointer exception
//            dto.setPhone(room.getPhone());
//            dto.setPickupPoint(room.getPickupPoint());
//            dto.setDropoffPoint(room.getDropoffPoint());
//            dto.setTicketPrice(room.getTicketPrice());
//            dto.setNote(room.getNote());
//            dto.setPaymentMethod(room.getPaymentMethod());
//            dto.setUserName(room.getUser() != null ? room.getUser().getName() : null);
//            dto.setOfficeName(room.getOffice() != null ? room.getOffice().getName() : null);
//            dto.setTripId(room.getTrip() != null ? room.getTrip().getId() : null);
//            dto.setId(room.getId());
//            dto.setAgencyId(room.getAgency() != null ? room.getAgency().getId() : null);
//            dto.setAgencyName(room.getAgency() != null ? room.getAgency().getName() : null);
//            return dto;  // Return the mapped DTO
//        }).collect(Collectors.toList());  // Collect the DTOs into a list
//    }
//
//
//
//
//
//
//
//    public void deleteRoomsByIds(List<Long> roomIds) {
//        System.out.println("Room IDs to delete: " + roomIds);
//        ticketRepository_v2.deleteAllById(roomIds);
//    }
//    public void updateSeats(List<TicketDTO_v1> seatUpdates) {
//        for (TicketDTO_v1 seatUpdate : seatUpdates) {
//            Optional<BmsTicket_v2> seatOptional = ticketRepository_v2.findById(seatUpdate.getId());
//
//            if (seatOptional.isPresent()) {
//                BmsTicket_v2 seat = seatOptional.get();
//
//                // Check if the agencyId is 0, then remove the agency
//                if (seatUpdate.getAgencyId() == null || seatUpdate.getAgencyId() == 0) {
//                    seat.setAgency(null);  // Remove the agency by setting it to null
//                } else {
//                    // If agencyId is not 0, retrieve the BmsAgent
//                    Optional<BmsAgent> agentOptional = agentRepository.findById(seatUpdate.getAgencyId());
//                    if (agentOptional.isPresent()) {
//                        BmsAgent agent = agentOptional.get();
//                        seat.setAgency(agent);  // Set the retrieved BmsAgent
//                    }
//                }
//
//                // Update the seat with the other data from the DTO
//                seat.setPhone(seatUpdate.getPhone());
//                seat.setPickupPoint(seatUpdate.getPickupPoint());
//                seat.setDropoffPoint(seatUpdate.getDropoffPoint());
//                seat.setTicketPrice(seatUpdate.getTicketPrice());
//                seat.setPaymentMethod(seatUpdate.getPaymentMethod());
//                seat.setNote(seatUpdate.getNote());
//                seat.setFullName(seatUpdate.getFullName());
//                seat.setTicketCode(seatUpdate.getTicketCode());
//
//                // Save the updated seat
//                ticketRepository_v2.save(seat);
//            }
//        }
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
