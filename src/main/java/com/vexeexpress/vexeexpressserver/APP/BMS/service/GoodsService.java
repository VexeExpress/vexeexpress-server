package com.vexeexpress.vexeexpressserver.APP.BMS.service;

import com.vexeexpress.vexeexpressserver.entity.BmsGoods;
import com.vexeexpress.vexeexpressserver.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    GoodsRepository goodsRepository;
    public BmsGoods addGoods(BmsGoods bmsGoods) {
        System.out.println(bmsGoods);
        return goodsRepository.save(bmsGoods);
    }

    public List<BmsGoods> getGoodsByTripId(Long tripId) {
        return goodsRepository.findByTripId(tripId);
    }
}
