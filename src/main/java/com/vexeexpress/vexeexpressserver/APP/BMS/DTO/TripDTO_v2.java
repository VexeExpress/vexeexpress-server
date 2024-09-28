package com.vexeexpress.vexeexpressserver.APP.BMS.DTO;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TripDTO_v2 {
    private Long id;
    private LocalTime valueTimeDeparture;
    private String valueChairDiagram;

    public TripDTO_v2(Long id, LocalTime valueTimeDeparture, String valueChairDiagram) {
        this.id = id;
        this.valueTimeDeparture = valueTimeDeparture;
        this.valueChairDiagram = valueChairDiagram;
    }
}
