package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.SeatMap.SeatMapDTO_v3;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.SeatMap.SeatMapDTO;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.SeatMap.SeatMapDTO_v4;
import com.vexeexpress.vexeexpressserver.APP.BMS.DTO.SeatMap.SeatMapDTO_v2;
import com.vexeexpress.vexeexpressserver.APP.BMS.service.SeatMapService;
import com.vexeexpress.vexeexpressserver.entity.BmsSeatMap;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//    @GetMapping("/seat-maps/{companyId}")
//    public ResponseEntity<List<SeatMapDTO_v2>> getSeatMapsByCompanyId(@PathVariable Long companyId) {
//        try {
//            List<SeatMapDTO_v2> seatMaps = seatMapService.getSeatMapByCompanyId(companyId);
//            return ResponseEntity.ok(seatMaps);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null); // Có thể trả về một thông điệp lỗi chi tiết hơn nếu cần
//        }
//    }
    @GetMapping("/seat-maps-name/{companyId}")
    public ResponseEntity<List<SeatMapDTO_v3>> getSeatMapsNameByCompanyId(@PathVariable Long companyId) {
        try {
            List<SeatMapDTO_v3> seatMap = seatMapService.getSeatMapsNameByCompanyId(companyId);
            return ResponseEntity.ok(seatMap);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/seat-map-id-by-trip-id/{tripId}")
    public ResponseEntity<Long> getSeatMapIdByTripId(@PathVariable Long tripId) {
        try {
            // Kiểm tra xem tripId có hợp lệ không
            if (tripId <= 0) {
                return ResponseEntity.badRequest().body(null); // Trả về 400 nếu ID không hợp lệ
            }

            Long seatMapId = seatMapService.getSeatMapIdByTripId(tripId);

            // Nếu không tìm thấy seatMapId, trả về 404
            if (seatMapId == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            return ResponseEntity.ok(seatMapId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Trả về 404 nếu không tìm thấy
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Trả về 500 cho lỗi khác
        }
    }

//    @GetMapping("/seat-map-by-id/{id}")
//    public ResponseEntity<SeatMapDTO_v4> getSeatMapById(@PathVariable Long id) {
//        try {
//            SeatMapDTO_v4 seatMapDTO = seatMapService.getSeatMapById(id);
//            return ResponseEntity.ok(seatMapDTO);
//        } catch (Exception e) {
//            if (e.getMessage().equals("Seat map not found")) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Trả về 404 nếu không tìm thấy
//            } else {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//            }
//        }
//    }






}
