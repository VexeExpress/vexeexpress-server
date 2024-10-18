package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.SeatMap.*;
import com.vexeexpress.vexeexpressserver.entity.*;
import com.vexeexpress.vexeexpressserver.repository.CompanyRepository;
import com.vexeexpress.vexeexpressserver.repository.SeatMapRepository;
import com.vexeexpress.vexeexpressserver.repository.SeatRepository;
import com.vexeexpress.vexeexpressserver.repository.TripRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatMapService {
    @Autowired
    SeatMapRepository seatMapRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    TripRepository tripRepository;
    @Autowired
    SeatRepository seatRepository;

//    public BmsSeatMap createSeatMap(SeatMapDTO seatMapDTO) {
//        // Create BmsSeatMap object from DTO
//        BmsSeatMap seatMap = new BmsSeatMap();
//        seatMap.setSeatMapName(seatMapDTO.getSeatMapName());
//        seatMap.setSeatColumn(seatMapDTO.getColumn());
//        seatMap.setRow(seatMapDTO.getRow());
//        seatMap.setFloor(seatMapDTO.getFloor());
//
//
//        // Get company info from database; ignore ResourceNotFoundException
//        Optional<BmsBusCompany> optionalCompany = companyRepository.findById(seatMapDTO.getCompany().getId());
//        if (optionalCompany.isPresent()) {
//            seatMap.setCompany(optionalCompany.get());
//        } else {
//            seatMap.setCompany(null);
//        }
//
//        // Map seat list from DTO to entity
//        List<BmsSeat> seats = seatMapDTO.getSeats().stream().map(seatDTO -> {
//            BmsSeat seat = new BmsSeat();
//            seat.setFloor(seatDTO.getFloor());
//            seat.setRow(seatDTO.getRow());
//            seat.setSeatColumn(seatDTO.getColumn());
//            seat.setName(seatDTO.getName());
//            seat.setStatus(seatDTO.getStatus());
//            seat.setBmsSeatMap(seatMap);
//            return seat;
//        }).collect(Collectors.toList());
//
//        // Set seat list for the seat map
//        seatMap.setSeats(seats);
//
//        // Save seat map and related seats
//        return seatMapRepository.save(seatMap);
//    }


//    public List<SeatMapDTO_v2> getSeatMapByCompanyId(Long companyId) {
//        // Truy vấn sơ đồ ghế từ repository
//        List<BmsSeatMap> seatMaps = seatMapRepository.findByCompanyId(companyId);
//
//        // Chuyển đổi từ Entity sang DTO
//        return seatMaps.stream().map(entity -> {
//            SeatMapDTO_v2 dto = new SeatMapDTO_v2();
//            dto.setId(entity.getId());
//            dto.setSeatMapName(entity.getSeatMapName());
//            dto.setRow(entity.getRow());
//            dto.setFloor(entity.getFloor());
//            dto.setColumn(entity.getSeatColumn());
//
//            dto.setSeats(entity.getSeat().stream().map(seatEntity -> {
//                SeatDTO_v2 seatDTO = new SeatDTO_v2();
//                seatDTO.setId(seatEntity.getId());
//                seatDTO.setFloor(seatEntity.getFloor());
//                seatDTO.setRow(seatEntity.getRow());
//                seatDTO.setColumn(seatEntity.getSeatColumn());
//                seatDTO.setName(seatEntity.getName());
//                seatDTO.setStatus(seatEntity.getStatus());
//                return seatDTO;
//            }).collect(Collectors.toList()));
//            return dto;
//        }).collect(Collectors.toList());
//    }

    public List<SeatMapDTO_v3> getSeatMapsNameByCompanyId(Long companyId) {
        List<BmsSeatMap> seatMaps = seatMapRepository.findByCompanyId(companyId);
        return seatMaps.stream().map( entity -> {
            SeatMapDTO_v3 dto = new SeatMapDTO_v3();
            dto.setId(entity.getId());
            dto.setSeatMapName(entity.getSeatMapName());
            return dto;
        }).collect(Collectors.toList());
    }

//    public SeatMapDTO_v2 getSeatMapByTripId(Long tripId) {
//        BmsTrip trip = tripRepository.findById(tripId).orElseThrow(() -> new EntityNotFoundException("Trip not found"));
//        System.out.println("Trip được chọn:" + trip);
//
//        Long seatMapId = trip.getSeatMapId();
//        System.out.println("seatMapId: " + seatMapId);
//
//
//
//
//
//
////        BmsSeatMap seatMap = seatMapRepository.findById(trip.getSeatMapId()).orElse(null);
////
////        if (seatMapId == null) {
////            return Collections.emptyList(); // Trả về danh sách rỗng
////        }
////        System.out.println("Sơ đồ ghế:" + seatMap);
//
//
//        return null;
//    }
    public Optional<Long> getSeatMapIdFromSelectedTrip(Long tripId) {
        if (tripId == null || tripId <= 0) {
            throw new IllegalArgumentException("Trip ID không hợp lệ.");
        }
        return tripRepository.findById(tripId).map(BmsTrip::getSeatMapId);
    }

//    public SeatMapDTO_v4 getSeatMapById(Long id) {
//        // Validate the ID
//        if (id == null || id <= 0) {
//            throw new IllegalArgumentException("ID must be greater than 0");
//        }
//
//        try {
//            Optional<BmsSeatMap> seatMap = seatMapRepository.findById(id);
//
//            // Check if the seat map exists
//            return seatMap.map(this::convertToDTO)
//                    .orElseThrow(() -> new EntityNotFoundException("Seat map not found with ID: " + id));
//        } catch (DataAccessException e) {
//            // Handle database access errors
//            throw new RuntimeException("Database error occurred while retrieving seat map", e);
//        }
//    }

//    private SeatMapDTO_v4 convertToDTO(BmsSeatMap bmsSeatMap) {
//        return null;
//    }

    public List<SeatMapDTO> getListSeatMapDetailByCompanyId(Long companyId) {
        if (companyId == null) {
            throw new IllegalArgumentException("companyId must not be null");
        }
        List<BmsSeatMap> seatMaps = seatMapRepository.findByCompanyId(companyId);
        if(seatMaps == null || seatMaps.isEmpty()) {
            return null;
        }
        return seatMaps.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private SeatMapDTO convertToDTO(BmsSeatMap seatMap) {
        SeatMapDTO seatMapDTO = new SeatMapDTO();
        seatMapDTO.setId(seatMap.getId());
        seatMapDTO.setSeatMapName(seatMap.getSeatMapName());
        seatMapDTO.setRow(seatMap.getRow());
        seatMapDTO.setFloor(seatMap.getFloor());
        seatMapDTO.setColumn(seatMap.getSeatColumn());
        seatMapDTO.setSeats(seatMap.getSeats().stream().map(this::convertToDTO_v2).collect(Collectors.toList()));

        return seatMapDTO;
    }

    private SeatDTO convertToDTO_v2(BmsSeat seat) {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setId(seat.getId());
        seatDTO.setColumn(seat.getSeatColumn());
        seatDTO.setName(seat.getName());
        seatDTO.setRow(seat.getRow());
        seatDTO.setFloor(seat.getFloor());
        seatDTO.setStatus(seat.getStatus());
        return seatDTO;
    }

    public BmsSeatMap createSeatMap(SeatMapDTO dto) {
        System.out.println(dto);
        Optional<BmsBusCompany> companyOpt = companyRepository.findById(dto.getCompanyId());
        if (companyOpt.isEmpty()) {
            throw new IllegalArgumentException("Company ID không hợp lệ.");
        }
        BmsSeatMap seatMap = new BmsSeatMap();
        seatMap.setSeatMapName(dto.getSeatMapName());
        seatMap.setFloor(dto.getFloor());
        seatMap.setRow(dto.getRow());
        seatMap.setSeatColumn(dto.getColumn());
        seatMap.setCompany(companyOpt.get());

        BmsSeatMap savedSeatMap = seatMapRepository.save(seatMap);
        if (dto.getSeats() != null && !dto.getSeats().isEmpty()) {
            List<BmsSeat> seats = dto.getSeats().stream().map(seatDto -> {
                BmsSeat seat = new BmsSeat();
                seat.setFloor(seatDto.getFloor());
                seat.setRow(seatDto.getRow());
                seat.setSeatColumn(seatDto.getColumn());
                seat.setName(seatDto.getName());
                seat.setStatus(seatDto.getStatus());
                seat.setBmsSeatMap(savedSeatMap);
                return seat;
            }).collect(Collectors.toList());

            seatRepository.saveAll(seats);
        }
        return savedSeatMap;
    }

    public BmsSeatMap updateSeatMap(Long id, SeatMapDTO dto) {
        Optional<BmsSeatMap> seatMapOptional = seatMapRepository.findById(id);
        if (seatMapOptional.isPresent()) {
            BmsSeatMap currentSeatMap = seatMapOptional.get();

            Optional<BmsBusCompany> companyOpt = companyRepository.findById(dto.getCompanyId());
            if (companyOpt.isEmpty()) {
                throw new IllegalArgumentException("Company ID không hợp lệ.");
            }

            if (!currentSeatMap.getSeatMapName().equals(dto.getSeatMapName())) {
                boolean seatMapNameExists = seatMapRepository.existsBySeatMapNameAndCompany_IdAndIdNot(
                        dto.getSeatMapName(), dto.getCompanyId(), currentSeatMap.getId()
                );
                if (seatMapNameExists) {
                    System.out.println("Sơ đồ: " + dto.getSeatMapName() + " đã tồn tại trong công ty: " + dto.getCompanyId());
                    return null;
                }
            }

            currentSeatMap.setSeatMapName(dto.getSeatMapName());
            currentSeatMap.setSeatColumn(dto.getColumn());
            currentSeatMap.setRow(dto.getRow());
            currentSeatMap.setFloor(dto.getFloor());

            BmsSeatMap updatedSeatMap = seatMapRepository.save(currentSeatMap);

            if (dto.getSeats() != null && !dto.getSeats().isEmpty()) {
                // Tìm ghế hiện tại
                List<BmsSeat> existingSeats = seatRepository.findByBmsSeatMap_Id(updatedSeatMap.getId());


                List<BmsSeat> updatedSeats = dto.getSeats().stream().map(seatDto -> {

                    Optional<BmsSeat> existingSeatOpt = existingSeats.stream()
                            .filter(seat -> seat.getFloor() == seatDto.getFloor()
                                    && seat.getRow() == seatDto.getRow()
                                    && seat.getSeatColumn() == seatDto.getColumn())
                            .findFirst();

                    BmsSeat seat;
                    if (existingSeatOpt.isPresent()) {
                        seat = existingSeatOpt.get();
                        seat.setName(seatDto.getName());
                        seat.setStatus(seatDto.getStatus());
                    } else {

                        seat = new BmsSeat();
                        seat.setFloor(seatDto.getFloor());
                        seat.setRow(seatDto.getRow());
                        seat.setSeatColumn(seatDto.getColumn());
                        seat.setName(seatDto.getName());
                        seat.setStatus(seatDto.getStatus());
                        seat.setBmsSeatMap(updatedSeatMap);
                    }
                    return seat;
                }).collect(Collectors.toList());

                seatRepository.saveAll(updatedSeats);
            }

            return updatedSeatMap;
        } else {
            throw new IllegalArgumentException("SeatMap ID không hợp lệ.");
        }
    }

    public void deleteSeatMap(Long id) throws Exception {
        if (seatMapRepository.existsById(id)) {
            List<BmsSeat> seats = seatRepository.findByBmsSeatMap_Id(id);
            if (!seats.isEmpty()) {
                seatRepository.deleteAll(seats);
            }
            seatMapRepository.deleteById(id);
        } else {
            throw new Exception("Level Agency not found");
        }
    }


//    public SeatMapDTO_v4 convertToDTO(BmsSeatMap seatMap) {
//        SeatMapDTO_v4 seatMapDTO = new SeatMapDTO_v4();
//
//        seatMapDTO.setId(seatMap.getId());
//        seatMapDTO.setSeatMapName(seatMap.getSeatMapName());
//        seatMapDTO.setFloor(seatMap.getFloor());
//        seatMapDTO.setRow(seatMap.getRow());
//        seatMapDTO.setColumn(seatMap.getSeatColumn());
//
//        // Chuyển đổi danh sách BmsSeat sang SeatDTO_v3
//        List<SeatDTO_v3> seatDTOList = seatMap.getSeat().stream().map(seat -> {
//            SeatDTO_v3 seatDTO = new SeatDTO_v3();
//
//            seatDTO.setId(seat.getId()); // ID của ghế
//            seatDTO.setFloor(seat.getFloor()); // Tầng của ghế
//            seatDTO.setRow(seat.getRow()); // Hàng của ghế
//            seatDTO.setSeatColumn(seat.getSeatColumn()); // Cột của ghế
//            seatDTO.setName(seat.getName()); // Tên ghế
//            seatDTO.setStatus(seat.getStatus()); // Trạng thái của ghế
//
//            return seatDTO;
//        }).collect(Collectors.toList());
//
//        // Gán danh sách SeatDTO vào SeatMapDTO
//        seatMapDTO.setSeats(seatDTOList);
//        return seatMapDTO;
//    }

//    public Long getSeatMapIdByTripId(Long tripId) {
//        BmsTrip trip = tripRepository.findById(tripId).orElseThrow(() -> new EntityNotFoundException("Trip not found"));
//        System.out.println("Trip được chọn:" + trip);
//        Long seatMapId = trip.getSeatMapId();
//        System.out.println("seatMapId: " + seatMapId);
//        return trip.getSeatMapId();
//    }
}
