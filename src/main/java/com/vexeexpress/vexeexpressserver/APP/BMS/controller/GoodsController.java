package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import com.vexeexpress.vexeexpressserver.APP.BMS.service.GoodsService;
import com.vexeexpress.vexeexpressserver.entity.BmsGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bms/goods")
@CrossOrigin(origins = "http://localhost:5173")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @PostMapping("/add-goods")
    public BmsGoods addGoods(@RequestBody BmsGoods bmsGoods) {
        return goodsService.addGoods(bmsGoods);
    }
    @GetMapping("/get-goods/{tripId}")
    public List<BmsGoods> getGoodsByTripId(@PathVariable Long tripId) {
        return goodsService.getGoodsByTripId(tripId);
    }
}
