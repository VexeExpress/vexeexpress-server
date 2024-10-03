package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.SeatMap.SeatMapDTO_v3;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.request.SeatMapDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.response.SeatMapDTO_v2;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.SeatMapService;
import com.vexeexpress.vexeexpressserver.entity.BmsBusCompany;
import com.vexeexpress.vexeexpressserver.entity.BmsSeat;
import com.vexeexpress.vexeexpressserver.entity.BmsSeatMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bms/seat-map")
@CrossOrigin(origins = "http://localhost:3000")
public class SeatMapController {
    @Autowired
    SeatMapService seatMapService;

    @PostMapping("/create-seat-map")
    public ResponseEntity<BmsSeatMap> createSeatMap(@RequestBody SeatMapDTO seatMapDTO) {
        try {
            System.out.println(seatMapDTO);
            BmsSeatMap newSeatMap = seatMapService.createSeatMap(seatMapDTO);
            return ResponseEntity.ok(newSeatMap);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/seat-maps/{companyId}")
    public ResponseEntity<List<SeatMapDTO_v2>> getSeatMapsByCompanyId(@PathVariable Long companyId) {
        try {
            List<SeatMapDTO_v2> seatMaps = seatMapService.getSeatMapByCompanyId(companyId);
            return ResponseEntity.ok(seatMaps);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Có thể trả về một thông điệp lỗi chi tiết hơn nếu cần
        }
    }
    @GetMapping("/seat-maps-name/{companyId}")
    public ResponseEntity<List<SeatMapDTO_v3>> getSeatMapsNameByCompanyId(@PathVariable Long companyId) {
        try {
            List<SeatMapDTO_v3> seatMap = seatMapService.getSeatMapsNameByCompanyId(companyId);
            return ResponseEntity.ok(seatMap);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
