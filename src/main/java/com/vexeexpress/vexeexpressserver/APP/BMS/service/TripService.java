package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Trip.TripDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Trip.TripDTO_v2;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.Trip.TripDTO_v3;
import com.vexeexpress.vexeexpressserver.entity.*;
import com.vexeexpress.vexeexpressserver.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TripService {
    @Autowired
    TripRepository tripRepository;
    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    BmsBusRepository busRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    SeatMapRepository seatMapRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    TicketRepository ticketRepository;

    public BmsTrip createTrip(TripDTO tripDTO) throws Exception {
        System.out.println("New Data: " + tripDTO);

        // Kiểm tra và lấy thông tin SeatMap
        System.out.println("Kiểm tra seat map...");
        Optional<BmsSeatMap> seatMapOptional = seatMapRepository.findById(tripDTO.getSeatMapId());
        if (!seatMapOptional.isPresent()) {
            throw new Exception("Seat map not found.");
        }
        System.out.println("Seat map được tìm thấy: " + seatMapOptional.get().getId());

        // Lấy thông tin công ty từ companyId
        System.out.println("Kiểm tra công ty...");
        Optional<BmsBusCompany> companyOptional = companyRepository.findById(tripDTO.getCompanyId());
        if (!companyOptional.isPresent()) {
            throw new Exception("Company not found.");
        }
        System.out.println("Công ty được tìm thấy: " + companyOptional.get().getId());

        // Tạo chuyến đi
        BmsTrip createdTrip = new BmsTrip();
        createdTrip.setRouterId(tripDTO.getRouterId());
        createdTrip.setDateTrip(tripDTO.getDateTrip());
        createdTrip.setVehicleId(tripDTO.getVehicleId());
        createdTrip.setUserId(tripDTO.getUserId());
        createdTrip.setSeatMapId(tripDTO.getSeatMapId());
        createdTrip.setTime(tripDTO.getTime());
        createdTrip.setNote(tripDTO.getNote());
        createdTrip.setCompany(companyOptional.get());

        System.out.println("Tạo chuyến đi với các thông tin sau:");
        System.out.println("Router ID: " + createdTrip.getRouterId());
        System.out.println("Date Trip: " + createdTrip.getDateTrip());
        System.out.println("Vehicle ID: " + createdTrip.getVehicleId());
        System.out.println("User IDs: " + createdTrip.getUserId());
        System.out.println("Seat Map ID: " + createdTrip.getSeatMapId());
        System.out.println("Time: " + createdTrip.getTime());
        System.out.println("Note: " + createdTrip.getNote());
        System.out.println("Company ID: " + createdTrip.getCompany().getId());

        // Lấy danh sách ghế và tạo vé
        List<BmsTicket> tickets = new ArrayList<>();
        List<BmsSeat> seats = seatRepository.findByBmsSeatMap_Id(tripDTO.getSeatMapId());
        System.out.println("Danh sách ghế: " + seats.size() + " ghế được tìm thấy.");
        for (BmsSeat seat : seats) {
            System.out.println("Ghế: " + seat.getName());
            BmsTicket ticket = new BmsTicket();
            ticket.setRoomCode(seat.getName());
            ticket.setSeat(seat);
            ticket.setRoomCode(seat.getName());
            ticket.setTrip(createdTrip);
            tickets.add(ticket);
        }
        createdTrip.setTickets(tickets); // Gán danh sách vé vào chuyến đi

        // Lưu chuyến đi
        createdTrip = tripRepository.save(createdTrip); // Lưu chuyến đi vào cơ sở dữ liệu
        System.out.println("Chuyến đi đã được lưu với ID: " + createdTrip.getId());

        // Lưu tất cả vé vào cơ sở dữ liệu
        ticketRepository.saveAll(tickets);
        System.out.println("Tất cả vé đã được lưu thành công.");

        // In ra thông tin chuyến đi đã tạo
        System.out.println("Chuyến đi đã tạo:");
        System.out.println("ID: " + createdTrip.getId());
        System.out.println("Router ID: " + createdTrip.getRouterId());
        System.out.println("Date Trip: " + createdTrip.getDateTrip());
        System.out.println("Time: " + createdTrip.getTime());
        System.out.println("Note: " + createdTrip.getNote());
        System.out.println("Company: " + createdTrip.getCompany().getId());

        System.out.println("Danh sách vé:");
        for (BmsTicket ticket : tickets) {
            System.out.println("Ticket ID: " + ticket.getId() + ", Seat: " + ticket.getSeat().getName());
        }

        return createdTrip; // Trả về chuyến đi đã tạo
    }
    public List<TripDTO_v2> searchTrips(Long companyId, LocalDate dateTrip, Long routerId) {
        System.out.println("CompanyID: " + companyId);
        System.out.println("Date: " + dateTrip);
        System.out.println("RouterID: " + routerId);

        long startTime = System.currentTimeMillis();

        Optional<BmsBusCompany> companyOptional = companyRepository.findById(companyId);
        if (!companyOptional.isPresent()) {
            throw new EntityNotFoundException("Company not found");
        }
        BmsBusCompany company = companyOptional.get();
        List<BmsTrip> trips = tripRepository.findByCompanyAndDateTripAndRouterId(company, dateTrip, routerId);

        long duration = System.currentTimeMillis() - startTime;

        System.out.println("Number of trips found: " + trips.size());
        System.out.println("Query executed in: " + duration + " ms");

        return trips.stream().map(trip -> {
            TripDTO_v2 tripDTO = new TripDTO_v2();

            tripDTO.setId(trip.getId());
            tripDTO.setTime(trip.getTime());

            Optional<BmsSeatMap> seatMapOptional = seatMapRepository.findById(trip.getSeatMapId());
            seatMapOptional.ifPresent(seatMap -> {
                tripDTO.setSeatMapName(seatMap.getSeatMapName());
            });

            if (trip.getVehicleId() != null) {
                Optional<BmsVehicle> vehicleOptional = vehicleRepository.findById(trip.getVehicleId());
                vehicleOptional.ifPresent(vehicle -> {
                    tripDTO.setLicensePlate(vehicle.getLicensePlate());
                });
            } else {
                tripDTO.setLicensePlate(null);
            }

            if (trip.getUserId() != null && !trip.getUserId().isEmpty()) {
                List<String> userNames = new ArrayList<>();
                List<Long> userIds = trip.getUserId().stream().map(Integer::longValue).collect(Collectors.toList());
                List<BmsUser> users = userRepository.findAllById(userIds);

                for (BmsUser user : users) {
                    userNames.add(user.getName());
                }
                tripDTO.setUser(userNames);
            } else {
                tripDTO.setUser(null);
            }

            System.out.println(tripDTO);
            return tripDTO;
        }).collect(Collectors.toList());
    }

//    public BmsTrip createTrip(TripDTO tripDTO) {
//        try {
//            BmsTrip trip = new BmsTrip();
//
//            trip.setDateTrip(tripDTO.getDateTrip());
//            trip.setNote(tripDTO.getNote());
//            trip.setTime(tripDTO.getTime());
//            trip.setUserId(tripDTO.getUserId());
//            trip.setVehicleId(tripDTO.getVehicleId());
//            trip.setRouterId(tripDTO.getRouterId());
//            trip.setSeatMapId(tripDTO.getSeatMapId());
//
//            BmsBusCompany company = companyRepository.findById(tripDTO.getCompanyId())
//                    .orElseThrow(() -> new RuntimeException("Company not found with ID: " + tripDTO.getCompanyId()));
//            trip.setCompany(company);
//
//
//            System.out.println("Saving trip: " + trip);
//            return tripRepository.save(trip);
//
//        } catch (DataIntegrityViolationException e) {
//            System.err.println("Data integrity violation: " + e.getMessage());
//            throw new RuntimeException("Failed to create trip due to data integrity violation", e);
//        } catch (Exception e) {
//            System.err.println("Error while creating trip: " + e.getMessage());
//            throw new RuntimeException("Failed to create trip", e);
//        }
//    }






//    public BmsTrip createTrip(TripDTO tripDTO) {
//        try {
//            // Tạo thực thể BmsTrip từ DTO
//            BmsTrip trip = new BmsTrip();
//            trip.setValueChairDiagram(tripDTO.getValueChairDiagram());
//            trip.setValueDayDeparture(LocalDate.parse(tripDTO.getValueDayDeparture().substring(0, 10)));
//            trip.setValueTimeDeparture(LocalTime.parse(tripDTO.getValueTimeDeparture()));
//            trip.setValueNote(tripDTO.getValueNote());
//            trip.setValueDriver(tripDTO.getValueDriver());
//
//            // Fetch and set related entities
//            BmsBusCompany company = companyRepository.findById(tripDTO.getCompanyId())
//                    .orElseThrow(() -> new RuntimeException("Company not found for ID: " + tripDTO.getCompanyId()));
//            // Xử lý nếu vehicleId không phải là null
//            if (tripDTO.getValueVehicle() != null) {
//                BmsVehicle vehicle = vehicleRepository.findById(tripDTO.getValueVehicle())
//                        .orElseThrow(() -> new RuntimeException("Vehicle not found for ID: " + tripDTO.getValueVehicle()));
//                trip.setVehicle(vehicle);
//            } else {
//                trip.setVehicle(null); // Nếu không có vehicle, đặt null
//            }
//            BmsRouter router = routerRepository.findById(tripDTO.getValueRouter())
//                    .orElseThrow(() -> new RuntimeException("Router not found for ID: " + tripDTO.getValueRouter()));
//
//            trip.setCompany(company);
//            trip.setRouter(router);
//
//            // In thông tin đối tượng để kiểm tra
//            System.out.println("Creating trip: " + trip);
//
//            // Lưu đối tượng vào cơ sở dữ liệu
//            return tripRepository.save(trip);
//        } catch (Exception e) {
//            // In lỗi ra console và ném lại lỗi
//            System.err.println("Error creating trip: " + e.getMessage());
//            throw e; // Ném lỗi để xử lý ở nơi khác hoặc thông báo cho người dùng
//        }
//    }








    //    public List<BmsTrip> searchTrips(Long valueRouter, String valueDayDeparture, Long companyId) {
//        System.out.println("Received request: valueRouter=" + valueRouter + ", valueDayDeparture=" + valueDayDeparture + ", companyId=" + companyId);
//        List<BmsTrip> allTrips = getAllTrips();
//        System.out.println("All trip: " + allTrips);
//        LocalDate requestedDate = LocalDate.parse(valueDayDeparture);
//        List<BmsTrip> filteredTrips = allTrips.stream()
//                .filter(trip -> trip.getRouter().getId().equals(valueRouter))
//                .filter(trip -> trip.getValueDayDeparture().equals(requestedDate))
//                .filter(trip -> trip.getCompany().getId().equals(companyId))
//                .collect(Collectors.toList());
//        System.out.println("Dữ liệu tìm thấy: " + filteredTrips);
//
//
//        return filteredTrips;
//
//    }
//    public List<TripDTO_v2> searchTrips(Long valueRouter, String valueDayDeparture, Long companyId) {
//        System.out.println("Received request: valueRouter=" + valueRouter + ", valueDayDeparture=" + valueDayDeparture + ", companyId=" + companyId);
//
//        List<BmsTrip> allTrips = getAllTrips();
//        System.out.println("All trip: " + allTrips);
//
//        LocalDate requestedDate = LocalDate.parse(valueDayDeparture);
//
//        // Filter trips based on the conditions
//        List<BmsTrip> filteredTrips = allTrips.stream()
//                .filter(trip -> trip.getRouter().getId().equals(valueRouter))
//                .filter(trip -> trip.getValueDayDeparture().equals(requestedDate))
//                .filter(trip -> trip.getCompany().getId().equals(companyId))
//                .collect(Collectors.toList());
//
//        System.out.println("Dữ liệu tìm thấy: " + filteredTrips);
//
//        // Map the filtered BmsTrip to TripDTO_v2
//        List<TripDTO_v2> tripDTOs = filteredTrips.stream()
//                .map(trip -> new TripDTO_v2(
//                        trip.getId(),
//                        trip.getValueTimeDeparture(),
//                        trip.getValueChairDiagram()
//                ))
//                .collect(Collectors.toList());
//        System.out.println("Data to return: " + tripDTOs);
//        return tripDTOs;
//    }
    public List<BmsTrip> getAllTrips() {
        return tripRepository.findAll();
    }

//    public TripDTO_v3 getTripDetailsById(Long tripId) {
//        return tripRepository.findById(tripId)
//                .map(this::convertToTripDTO)
//                .orElse(null);
//    }

//    private TripDTO_v3 convertToTripDTO(BmsTrip bmsTrip) {
//        if (bmsTrip == null) {
//            return null;
//        }
//        TripDTO_v3 dto = new TripDTO_v3();
//        dto.setId(bmsTrip.getId());
//        dto.setDate(bmsTrip.getDateTrip());
//        dto.setTime(bmsTrip.getTime());
//        dto.setNote(bmsTrip.getNote());
//
//        Long routerId = bmsTrip.getRouterId();
//        if (routerId != null) {
//            Optional<BmsRouter> routerOpt = routerRepository.findById(routerId);
//            if (routerOpt.isPresent()) {
//                dto.setRouterName(routerOpt.get().getRouteName());
//            } else {
//                dto.setRouterName(null);
//            }
//        } else {
//            dto.setRouterName(null);
//        }
//
//        List<Integer> userIds = bmsTrip.getUserId();
//        System.out.println("User IDs (Integer): " + userIds);
//        List<Long> longUserIds = new ArrayList<>();
//        if (userIds != null && !userIds.isEmpty()) {
//            for (Integer id : userIds) {
//                longUserIds.add(id.longValue());
//            }
//        } else {
//            System.out.println("No User IDs found to convert.");
//        }
//        List<BmsUser> users = userRepository.findByIdIn(longUserIds);
//        System.out.println("User IDs (Long): " + longUserIds);
//        List<String> userDetails = users.stream()
//                .map(user -> user.getName() + " (" + user.getPhone() + ") ") // Combine name and phone
//                .collect(Collectors.toList());
//        dto.setUser(userDetails);
//
//        Long seatMapId = bmsTrip.getSeatMapId();
//        if(seatMapId != null) {
//            Optional<BmsSeatMap> seatMapOptional = seatMapRepository.findById(seatMapId);
//            if(seatMapOptional.isPresent()) {
//                dto.setSeatMap(seatMapOptional.get().getSeatMapName());
//            } else {
//                dto.setSeatMap(null);
//            }
//        } else {
//            dto.setSeatMap(null);
//        }
//        Long vehicleId = bmsTrip.getVehicleId();
//        if(vehicleId != null) {
//            Optional<BmsVehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
//            if(vehicleOptional.isPresent()) {
//                dto.setLicensePlate(vehicleOptional.get().getLicensePlate());
//                dto.setVehcilePhone(vehicleOptional.get().getPhone());
//            } else {
//                dto.setVehcilePhone(null);
//                dto.setLicensePlate(null);
//            }
//        } else {
//            dto.setVehcilePhone(null);
//            dto.setLicensePlate(null);
//        }
//
//
//        System.out.println(dto);
//        return dto;
//    }


//    public TripDTO_v3 getTripDetails(Long tripId) {
//        // Kiểm tra Optional để tránh NullPointerException khi không có dữ liệu trip
//        Optional<BmsTrip> tripOptional = tripRepository.findById(tripId);
//
//        // Nếu trip không tồn tại, trả về null
//        if (tripOptional.isEmpty()) {
//            return null;
//        }
//
//        // Lấy dữ liệu trip từ Optional
//        BmsTrip trip = tripOptional.get();
//        TripDTO_v3 dto = new TripDTO_v3();
//
//        // Đảm bảo tất cả các giá trị từ trip không gây lỗi nếu null
//        dto.setTripId(trip.getId());
//        dto.setValueChairDiagram(trip.getValueChairDiagram() != null ? trip.getValueChairDiagram() : "");
//        dto.setValueNote(trip.getValueNote() != null ? trip.getValueNote() : ""); // Mặc định là chuỗi rỗng nếu null
//        dto.setValueDayDeparture(trip.getValueDayDeparture() != null ? trip.getValueDayDeparture() : LocalDate.now()); // Mặc định là ngày hiện tại
//        dto.setValueTimeDeparture(trip.getValueTimeDeparture() != null ? trip.getValueTimeDeparture() : LocalTime.now()); // Mặc định là giờ hiện tại
//
//        // Xử lý danh sách driverIds
//        List<Integer> driverIds = trip.getValueDriver();
//        if (driverIds != null && !driverIds.isEmpty()) {
//            // Convert List<Integer> to List<Long>
//            List<Long> longDriverIds = driverIds.stream()
//                    .map(Long::valueOf)  // Convert Integer to Long
//                    .collect(Collectors.toList());
//
//            // Kiểm tra và lấy driver details (name và phone), mặc định trả về chuỗi rỗng nếu null
//            List<String> driverDetails = userRepository.findByIdIn(longDriverIds)
//                    .stream()
//                    .map(user -> {
//                        String name = user.getName() != null ? user.getName() : "Unknown";
//                        String phone = user.getPhone() != null ? user.getPhone() : "Unknown";
//                        return name + " (" + phone + ")";
//                    })
//                    .collect(Collectors.toList());
//
//            dto.setValueDriverNames(driverDetails);
//        } else {
//            dto.setValueDriverNames(new ArrayList<>()); // Mặc định là danh sách rỗng nếu không có drivers
//        }
//
//        // Xử lý router, mặc định là "Unknown" nếu router null
//        if (trip.getRouter() != null) {
//            dto.setRouterName(trip.getRouter().getRouteName() != null ? trip.getRouter().getRouteName() : "Unknown");
//        } else {
//            dto.setRouterName("Unknown");
//        }
//
//        // Xử lý vehicle, mặc định là giá trị hợp lý nếu vehicle null
//        if (trip.getVehicle() != null) {
//            dto.setLicensePlate(trip.getVehicle().getLicensePlate() != null ? trip.getVehicle().getLicensePlate() : "Unknown");
//            dto.setVehiclePhone(trip.getVehicle().getPhone() != null ? trip.getVehicle().getPhone() : "Unknown");
//        } else {
//            dto.setLicensePlate("Unknown");
//            dto.setVehiclePhone("Unknown");
//        }
//
//        System.out.println(dto);
//
//        return dto;
//    }


//    public List<TripDTO> getInfoTrip(Long companyId) {
//        return tripRepository.findByCompanyId(companyId)
//                .stream() // Convert List to Stream
//                .map(trip -> {
//                    // Convert Trip entity to TripDTO
//                    TripDTO dto = new TripDTO();
//                    dto.setId(trip.getId());
//                    dto.setCompanyId(trip.getCompany().getId());
//                    dto.setValueChairDiagram(trip.getValueChairDiagram());
//                    dto.setValueDayDeparture(trip.getValueDayDeparture());
//                    dto.setValueTimeDeparture(trip.getValueTimeDeparture());
//                    dto.setVehicleId(trip.getVehicle().getId());
//                    dto.setRouterId(trip.getRouter().getId());
//                    dto.setValueNote(trip.getValueNote());
//                    dto.setValueDriver(trip.getValueDriver());
//                    return dto;
//                })
//                .collect(Collectors.toList()); // Collect the mapped DTOs into a List
//    }


}
