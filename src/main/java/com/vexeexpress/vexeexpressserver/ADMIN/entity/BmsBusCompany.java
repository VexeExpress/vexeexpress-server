package com.vexeexpress.vexeexpressserver.ADMIN.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "bms_bus_company")
public class BmsBusCompany {
    @Id
    private String id;
    private String numberVehicle;
    private String companyName;
    private String idUser;
    private Boolean activateBusCompany;
}
