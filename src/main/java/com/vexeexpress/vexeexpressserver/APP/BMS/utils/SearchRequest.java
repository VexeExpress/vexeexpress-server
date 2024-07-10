package com.vexeexpress.vexeexpressserver.APP.BMS.utils;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class SearchRequest {
    private String valueRouter;
    private LocalDate valueDayDeparture;
    private String companyId;
}
