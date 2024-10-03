package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.SeatMap.SeatMapDTO_v3;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.request.SeatMapDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.response.SeatDTO_v2;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.response.SeatMapDTO_v2;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsSeat;
import com.vexeexpress.vexeexpressserver.entity.BmsSeatMap;
import com.vexeexpress.vexeexpressserver.repository.CompanyRepository;
import com.vexeexpress.vexeexpressserver.repository.SeatMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public BmsSeatMap createSeatMap(SeatMapDTO seatMapDTO) {
        // Create BmsSeatMap object from DTO
        BmsSeatMap seatMap = new BmsSeatMap();
        seatMap.setSeatMapName(seatMapDTO.getSeatMapName());
        seatMap.setSeatColumn(seatMapDTO.getColumn());
        seatMap.setRow(seatMapDTO.getRow());
        seatMap.setFloor(seatMapDTO.getFloor());

        // Get company info from database; ignore ResourceNotFoundException
        Optional<BmsBusCompany> optionalCompany = companyRepository.findById(seatMapDTO.getCompany().getId());
        if (optionalCompany.isPresent()) {
            seatMap.setCompany(optionalCompany.get());
        } else {
            seatMap.setCompany(null);
        }

        // Map seat list from DTO to entity
        List<BmsSeat> seats = seatMapDTO.getSeats().stream().map(seatDTO -> {
            BmsSeat seat = new BmsSeat();
            seat.setFloor(seatDTO.getFloor());
            seat.setRow(seatDTO.getRow());
            seat.setSeatColumn(seatDTO.getColumn());
            seat.setName(seatDTO.getName());
            seat.setStatus(seatDTO.getStatus());
            seat.setBmsSeatMap(seatMap);
            return seat;
        }).collect(Collectors.toList());

        // Set seat list for the seat map
        seatMap.setSeat(seats);

        // Save seat map and related seats
        return seatMapRepository.save(seatMap);
    }


    public List<SeatMapDTO_v2> getSeatMapByCompanyId(Long companyId) {
        // Truy vấn sơ đồ ghế từ repository
        List<BmsSeatMap> seatMaps = seatMapRepository.findByCompanyId(companyId);

        // Chuyển đổi từ Entity sang DTO
        return seatMaps.stream().map(entity -> {
            SeatMapDTO_v2 dto = new SeatMapDTO_v2();
            dto.setId(entity.getId());
            dto.setSeatMapName(entity.getSeatMapName());
            dto.setRow(entity.getRow());
            dto.setFloor(entity.getFloor());
            dto.setColumn(entity.getSeatColumn());

            dto.setSeats(entity.getSeat().stream().map(seatEntity -> {
                SeatDTO_v2 seatDTO = new SeatDTO_v2();
                seatDTO.setId(seatEntity.getId());
                seatDTO.setFloor(seatEntity.getFloor());
                seatDTO.setRow(seatEntity.getRow());
                seatDTO.setColumn(seatEntity.getSeatColumn());
                seatDTO.setName(seatEntity.getName());
                seatDTO.setStatus(seatEntity.getStatus());
                return seatDTO;
            }).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    public List<SeatMapDTO_v3> getSeatMapsNameByCompanyId(Long companyId) {
        List<BmsSeatMap> seatMaps = seatMapRepository.findByCompanyId(companyId);
        return seatMaps.stream().map( entity -> {
            SeatMapDTO_v3 dto = new SeatMapDTO_v3();
            dto.setId(entity.getId());
            dto.setSeatMapName(entity.getSeatMapName());
            return dto;
        }).collect(Collectors.toList());
    }
}
